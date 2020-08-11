package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.TestCase;
import ir.khu.jaobshaar.component.authenticate.AuthenticationManager;
import ir.khu.jaobshaar.component.user.EmployeeManager;
import ir.khu.jaobshaar.config.jwt.JwtResponse;
import ir.khu.jaobshaar.entity.model.Job;
import ir.khu.jaobshaar.repository.JobRepository;
import ir.khu.jaobshaar.repository.UserRepository;
import ir.khu.jaobshaar.service.domain.JobDomain;
import ir.khu.jaobshaar.service.domain.ResumeDomain;
import ir.khu.jaobshaar.service.dto.user.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class EmployeeServiceTest extends TestCase {
    @Mock
    private EmployeeManager employeeManager;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JobRepository jobRepository;

    @Test
    public void assertInjectableFieldsAreNonNull() {
        Assert.assertNotNull(employeeManager);
        Assert.assertNotNull(authenticationManager);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(jobRepository);
    }

    @Test
    public void login() {
        UserDTO dto = new UserDTO("962023004", "123456", "fatemetorkzaban@gmail.com");
        employeeManager.login(dto);
        JwtResponse jwt = authenticationManager.authenticate(dto.getUsername(), dto.getPassword());
        Assert.assertNull(jwt);
        Assert.assertNull(userRepository.findByUsername(dto.getUsername()));
    }

    @Test
    public void register() {
        UserDTO dto = new UserDTO("962023004", "123456", "fatemetorkzaban@gmail.com");
        employeeManager.register(dto);

        Assert.assertNull(userRepository.findByUsername(dto.getUsername()));
    }

    @Test
    public void applyJob() {
        Long jobId = new Random().nextLong();
        employeeManager.applyJob(jobId);
        Optional<Job> job = jobRepository.findById(jobId);
        Assert.assertNotNull(job);
    }

    @Test
    public void getAppliedJobs() {
        List<JobDomain> jobDomains = employeeManager.getAppliedJobs();
        Assert.assertNotNull(jobDomains);
    }

    @Test
    public void getEmployeeResume() {
        ResumeDomain resumeDomain = employeeManager.getEmployeeResume();
        Assert.assertNull(resumeDomain);
    }

    @Test
    public void isApplied() {
        Long jobId = new Random().nextLong();
        Boolean result = employeeManager.isApplied(jobId);
        Assert.assertFalse(result);
    }

    @Override
    public void tearDown() {

    }

    @Override
    public void setup() {

    }
}
