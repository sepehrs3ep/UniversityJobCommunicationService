package ir.khu.jaobshaar.component.company;

import ir.khu.jaobshaar.config.jwt.JwtUserDetailsService;
import ir.khu.jaobshaar.entity.model.Company;
import ir.khu.jaobshaar.entity.model.Employer;
import ir.khu.jaobshaar.repository.CompanyRepository;
import ir.khu.jaobshaar.repository.EmployerRepository;
import ir.khu.jaobshaar.service.domain.CompanyDomain;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import ir.khu.jaobshaar.service.mapper.CompanyMapper;
import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyManager {

    private final CompanyMapper companyMapper;
    private EmployerRepository employerRepository;
    private CompanyRepository companyRepository;
    private JwtUserDetailsService userDetailsService;


    public CompanyManager(EmployerRepository employerRepository, CompanyRepository companyRepository, JwtUserDetailsService userDetailsService, CompanyMapper companyMapper) {
        this.employerRepository = employerRepository;
        this.companyRepository = companyRepository;
        this.userDetailsService = userDetailsService;
        this.companyMapper = companyMapper;
    }

    public void addCompany(final CompanyDTO companyDTO) {
        if (companyDTO == null) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_INVALID_COMPANY_FIELD, " CompanyDTO is null "
            );
        }

        final String companyName = companyDTO.getName();

        if (
                companyName == null ||
                        companyName.isEmpty()
        ) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_INVALID_COMPANY_FIELD, " Company Name  is empty "
            );
        }

        final Company existCompany = companyRepository.findByName(companyName);

        if (existCompany != null) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_COMPANY_ALREADY_EXIST, " ERROR_CODE_COMPANY_ALREADY_EXIST "
            );
        }

        final Employer currentEmployer = employerRepository.findByUsername(
                userDetailsService.getCurrentUser().getUsername()
        );

        if (currentEmployer.getCompany() != null) {
            throw ResponseException.newResponseException(
                    ErrorCodes.ERROR_CODE_EMPLOYER_ALREADY_HAS_COMPANY, "ERROR_CODE_EMPLOYER_ALREADY_HAS_COMPANY"
            );
        }

        final Company company = companyMapper.toEntity(companyDTO);

        currentEmployer.setCompany(company);

        company.setEmployer(currentEmployer);

        companyRepository.save(company);
    }

    @Transactional
    public void updateCompany(CompanyDTO companyDTO) {
        Employer employer = employerRepository.findByUsername(userDetailsService.getCurrentUser().getUsername());
        if (employer == null)
            throw new ResponseException(ErrorCodes.ERROR_CODE_NOT_ALLOW, "only.employer.can.update.company");

        if (employer.getCompany().getId() != (companyDTO.getId()))
            throw new ResponseException(ErrorCodes.ERROR_CODE_NOT_ALLOW, "you.cant.update.this.company");

        if (!companyDTO.getName().equals(employer.getCompany().getName()))
            throw new ResponseException(ErrorCodes.ERROR_CODE_NOT_ALLOW, "cant.change.company.name");

        companyRepository.save(companyMapper.toEntity(companyDTO));
    }

    public List<CompanyDomain> getCompanyList() {
        List<CompanyDomain> domains = new ArrayList<>();
        companyRepository.findAll().forEach(company -> {
            domains.add(companyMapper.toDomain(company));
        });
        return domains;
    }
}
