package ir.khu.jaobshaar.component.resume;

import ir.khu.jaobshaar.config.jwt.JwtUserDetailsService;
import ir.khu.jaobshaar.entity.enums.PersonRuleType;
import ir.khu.jaobshaar.entity.model.Employee;
import ir.khu.jaobshaar.entity.model.Job;
import ir.khu.jaobshaar.entity.model.Resume;
import ir.khu.jaobshaar.entity.model.User;
import ir.khu.jaobshaar.repository.EmployeeJobRepository;
import ir.khu.jaobshaar.repository.EmployeeRepository;
import ir.khu.jaobshaar.repository.EmployerRepository;
import ir.khu.jaobshaar.repository.ResumeRepository;
import ir.khu.jaobshaar.service.dto.ResumeDTO;
import ir.khu.jaobshaar.service.mapper.ResumeMapper;
import ir.khu.jaobshaar.utils.FileStorageService;
import ir.khu.jaobshaar.utils.ValidationUtils;
import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class ResumeManager {
    private final JwtUserDetailsService userDetailsService;
    private final ResumeRepository resumeRepository;
    private final EmployeeRepository employeeRepository;
    private final ResumeMapper resumeMapper;
    private final EmployeeJobRepository employeeJobRepository;
    private final EmployerRepository employerRepository;
    private final FileStorageService fileStorageService;

    public ResumeManager(JwtUserDetailsService userDetailsService, ResumeRepository resumeRepository,
                         EmployeeRepository employeeRepository, ResumeMapper resumeMapper,
                         EmployeeJobRepository employeeJobRepository, EmployerRepository employerRepository, FileStorageService fileStorageService) {
        this.userDetailsService = userDetailsService;
        this.resumeRepository = resumeRepository;
        this.employeeRepository = employeeRepository;
        this.resumeMapper = resumeMapper;
        this.employeeJobRepository = employeeJobRepository;
        this.employerRepository = employerRepository;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    public ResumeDTO addResume(ResumeDTO resumeDTO) {
        validateResume(resumeDTO);

        final Employee currentEmployee = employeeRepository.findByUsername(userDetailsService.getCurrentUser().getUsername());

        if (currentEmployee.getResume() != null) {
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_RESUME_ALREADY_EXIST, " This Employee Already has resume, if you want updating use PUT");
        }

        final Resume resume = resumeMapper.toEntity(resumeDTO);

        currentEmployee.setResume(resume);

        resume.setEmployee(currentEmployee);

        resumeRepository.save(resume);
        return resumeDTO;
    }

    private void validateResume(ResumeDTO resumeDTO) {
        if (
                resumeDTO == null ||
                        resumeDTO.getUrl() == null ||
                        resumeDTO.getUrl().isEmpty() ||
                        !ValidationUtils.isValidURL(resumeDTO.getUrl())
        ) {
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_INVALID_RESUME_URL, " ERROR_CODE_INVALID_RESUME_URL ");
        }

        final User currentUser = userDetailsService.getCurrentUser();

        if (currentUser.getRoleTypeIndex() == PersonRuleType.EMPLOYER) {
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED, " Employer can't add resume");
        }

        if (resumeRepository.findResumeByUrl(resumeDTO.getUrl()) != null) {
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_RESUME_URL_ALREADY_EXIST, " ERROR_CODE_RESUME_URL_ALREADY_EXIST");
        }
    }

    @Transactional
    public ResumeDTO updateResume(ResumeDTO resumeDTO) {
        validateResume(resumeDTO);

        final Employee currentEmployee = employeeRepository.findByUsername(userDetailsService.getCurrentUser().getUsername());

        final Resume existResume = currentEmployee.getResume();

        if (existResume == null) {
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, " This Employee doesn't has any resume, try using POST");
        }

        existResume.setUrl(resumeDTO.getUrl());
        resumeRepository.save(existResume);
        return resumeDTO;
    }

    @Transactional
    public void addResume(String fileName) {
        Employee employee = employeeRepository.findByUsername(userDetailsService.getCurrentUser().getUsername());

        if (employee == null) {
            fileStorageService.deleteFile(fileName);
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED, " Employer can't add resume");
        }
        if (employee.getResume().getUuid() != null) {
            fileStorageService.deleteFile(fileName);
            throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_ALREADY_EXIST, "you.have.resume");
        }
        Resume resume = new Resume();
        resume.setUuid(fileName);
        resume.setEmployee(employee);
        employee.setResume(resume);
        resumeRepository.save(resume);
    }

    @Transactional
    public void updateResume(String fileName) {
        Employee employee = employeeRepository.findByUsername(userDetailsService.getCurrentUser().getUsername());
        if (employee == null) {
            fileStorageService.deleteFile(fileName);
            throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED, " Employer can't add resume");
        }
        if (employee.getResume().getUuid() == null) {
            fileStorageService.deleteFile(fileName);
            throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, "you.have.noting.to.update");
        }
        Resume resume = employee.getResume();
        fileStorageService.deleteFile(resume.getUuid());
        resume.setUuid(fileName);
    }

    public String getResumeName() {
        Resume resume = employeeRepository.findByUsername(userDetailsService.getCurrentUser().getUsername()).getResume();
        if (resume != null && resume.getUuid() != null)
            return resume.getUuid();
        throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, "you.have.not.resume");
    }

    /**
     * kole job haye employer ro migire va age sahebe in resume hadeaqal bara yeki az on
     * job ha resume ferestade bashe onvaqt employer mitone resume ro bebine
     */
    public String getResumeName(long id) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        Resume resume;
        if (optionalResume.isEmpty())
            throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, "resume.not.found");
        resume = optionalResume.get();
        Set<Job> employerJobs = employerRepository.findByUsername(userDetailsService.getCurrentUser().getUsername()).getJobs();
        if (employeeJobRepository.findAllById_Employee(resume.getEmployee())
                .removeIf(employeeJobs -> employerJobs.contains(employeeJobs.getId().getJob())))
            if (resume.getUuid() != null)
                return resume.getUuid();
            else
                throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, "resume.file.not.found");
        throw new ResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED, "this.resume.was.not.sent.to.you");
    }
}
