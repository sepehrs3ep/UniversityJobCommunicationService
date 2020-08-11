package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.TestCase;
import ir.khu.jaobshaar.component.company.CompanyManager;
import ir.khu.jaobshaar.entity.model.Company;
import ir.khu.jaobshaar.repository.CompanyRepository;
import ir.khu.jaobshaar.service.domain.CompanyDomain;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.Random;

public class CompanyServiceTest extends TestCase {

    @Mock
    private CompanyManager companyManager;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void assertInjectableFieldsAreNonNull() {
        Assert.assertNotNull(companyManager);
        Assert.assertNotNull(companyRepository);
    }

    @Test
    public void addCompany() {
        Random random = new Random();
        CompanyDTO companyDTO = new CompanyDTO("company" + random.nextInt(),
                random.nextInt(10), "bio" + random.nextInt(),
                "address" + random.nextInt(), "logo" + random.nextInt());
        companyManager.addCompany(companyDTO);
        Company company = companyRepository.findByName(companyDTO.getName());
        Assert.assertNull(company);
    }

    @Test
    public void update() {
        Random random = new Random();
        CompanyDTO companyDTO = new CompanyDTO(random.nextLong(), "company" + random.nextInt(),
                random.nextInt(10), "bio" + random.nextInt(),
                "addres" + random.nextInt(), "logo" + random.nextInt());
        CompanyDomain companyDomain = companyManager.update(companyDTO);
        Assert.assertNull(companyDomain);
    }

    @Test
    public void getCurrentUserCompany() {
        CompanyDomain companyDomain = companyManager.getCurrentUserCompany();
        Assert.assertNull(companyDomain);
    }

    @Test
    public void deleteById() {
        Long companyId = new Random().nextLong();
        companyManager.deleteById(companyId);
        Optional<Company> company = companyRepository.findById(companyId);
        Assert.assertNotNull(company);
    }

    @Override
    public void tearDown() {

    }

    @Override
    public void setup() {

    }
}
