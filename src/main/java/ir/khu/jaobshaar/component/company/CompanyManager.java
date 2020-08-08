package ir.khu.jaobshaar.component.company;

import ir.khu.jaobshaar.config.jwt.JwtUserDetailsService;
import ir.khu.jaobshaar.entity.enums.PersonRuleType;
import ir.khu.jaobshaar.entity.model.Company;
import ir.khu.jaobshaar.entity.model.Employer;
import ir.khu.jaobshaar.entity.model.User;
import ir.khu.jaobshaar.repository.CompanyRepository;
import ir.khu.jaobshaar.repository.EmployerRepository;
import ir.khu.jaobshaar.service.domain.CompanyDomain;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import ir.khu.jaobshaar.service.mapper.CompanyMapper;
import ir.khu.jaobshaar.utils.ValidationUtils;
import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public CompanyDomain update(CompanyDTO companyDTO){
        ValidationUtils.validCompanyForUpdate(companyDTO);

        Optional<Company> company = companyRepository.findById(companyDTO.getId());

        if (company.isEmpty())
            throw new ResponseException(ErrorCodes.ERROR_CODE_COMPANY_NOT_FOUND,"the company not found");

        // add fixed columns
        Company toBeUpdate = companyMapper.toEntity(companyDTO);
        toBeUpdate.setEmployer(company.get().getEmployer());

        return companyMapper.toDomain(companyRepository.save(toBeUpdate));
    }

    public CompanyDomain getCurrentUserCompany(){
        User currentUser= userDetailsService.getCurrentUser();

        if (currentUser.getRoleTypeIndex().equals(PersonRuleType.EMPLOYEE))
            throw new ResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED,"you dont have employer rule");

        Employer currentEmployer = employerRepository.findByUsername(currentUser.getUsername());

        return companyMapper.toDomain(currentEmployer.getCompany());
    }

    public void deleteById(Long id){
        if (id == null)
            throw new ResponseException(ErrorCodes.ERROR_CODE_ID_MUST_NOT_BE_NULL,"id must not be null");

        User user = userDetailsService.getCurrentUser();
        if (user.getRoleTypeIndex().equals(PersonRuleType.EMPLOYEE))
            throw new ResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED,"you dont have employer rule");

        if (!employerRepository.findByUsername(user.getUsername()).getCompany().getId().equals(id))
            throw new ResponseException(ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED,"you are not owner this company");

        companyRepository.deleteById(id);
    }
}
