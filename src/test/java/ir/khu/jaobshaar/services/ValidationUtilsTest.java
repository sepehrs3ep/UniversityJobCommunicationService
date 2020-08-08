package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.entity.enums.RequiredGenderType;
import ir.khu.jaobshaar.entity.model.Employee;
import ir.khu.jaobshaar.entity.model.Employer;
import ir.khu.jaobshaar.entity.model.Job;
import ir.khu.jaobshaar.service.dto.JobDTO;
import ir.khu.jaobshaar.service.dto.employer.CompanyDTO;
import ir.khu.jaobshaar.service.dto.user.ChangePasswordDTO;
import ir.khu.jaobshaar.service.dto.user.UserDTO;
import ir.khu.jaobshaar.utils.ValidationUtils;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ValidationUtilsTest {

	private final PasswordEncoder passwordEncoder;

	public ValidationUtilsTest() {
		this.passwordEncoder = Mockito.spy(PasswordEncoder.class);
	}

	@Test
	public void isInvalidEmailAddress() {
		Random random = new Random();
		Assert.assertTrue(ValidationUtils.isInvalidEmailAddress("email"));
		Assert.assertTrue(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong()));
		Assert.assertTrue(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong() + "@"));
		Assert.assertFalse(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong() + "@gmail.com"));
		Assert.assertFalse(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong() + "@email.com"));
		Assert.assertFalse(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong() + "@yahoo.com"));
		Assert.assertFalse(ValidationUtils.isInvalidEmailAddress("email" + random.nextLong() + "@khu.ac.ir"));
	}

	@Test
	public void isInvalidPassword() {
		Random random = new Random();
		Assert.assertFalse(ValidationUtils.isInvalidPassword("lkklsdsk@@##$GG" + random.nextLong()));
	}

	@Test
	public void isInvalidUserName() {
		Random random = new Random();
		Assert.assertFalse(ValidationUtils.isInvalidUserName("dddd211111" + random.nextLong()));
	}

	@Test
	public void validateUser() {
		UserDTO userDTO = new UserDTO("962023004", "123456", "fatemetorkzaban@gmail.com");
		ValidationUtils.validateUser(userDTO);
		try {
			ValidationUtils.validateUser(null);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void isValidURL() {
		Assert.assertFalse(ValidationUtils.isValidURL("ddkjd/sjdsjf"));
		Assert.assertFalse(ValidationUtils.isValidURL(""));
		Assert.assertTrue(ValidationUtils.isValidURL("https://github.com/8fatemeT8/UniversityJobCommunicationService"));
	}

	@Test
	public void checkRequiredGender() {
		try {
			ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.FEMALE), new Employee(RequiredGenderType.MALE));
		} catch (ResponseException e) {
			Assert.assertTrue(true);
		}
		ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.FEMALE), new Employee(RequiredGenderType.FEMALE));
		ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.MALE), new Employee(RequiredGenderType.MALE));
		ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.DONT_CARE), new Employee(RequiredGenderType.MALE));
		ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.DONT_CARE), new Employee(RequiredGenderType.FEMALE));
		try {
			ValidationUtils.checkRequiredGender(new Job(RequiredGenderType.MALE), new Employee(RequiredGenderType.FEMALE));
		} catch (ResponseException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void accessToGetResume() {
		Set<Job> jobs = new HashSet<>();
		Job job1 = new Job();
		Job job2 = new Job();
		jobs.add(job1);
		Employer employer = new Employer(jobs);
		ValidationUtils.accessToGetResume(employer, job1);
		try {
			ValidationUtils.accessToGetResume(employer, job2);
		} catch (ResponseException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void validNewPasswords() {
		String pass = String.valueOf(new Random().nextLong());
		ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("123456", pass, pass);
		try{
			ValidationUtils.validNewPasswords(changePasswordDTO, passwordEncoder.encode("111"), passwordEncoder);
		}catch (ResponseException e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void validJobForUpdate() {
		Random random=new Random();
		JobDTO jobDTOTrue = new JobDTO(random.nextLong(),random.nextInt(18), random.nextInt(3),
				random.nextInt(2), "", "");
		JobDTO jobDTOFalse = new JobDTO(random.nextLong(),random.nextInt(20000), random.nextInt(),
				random.nextInt(200000), "", "");
		ValidationUtils.validJobForUpdate(jobDTOTrue);
		try {
			ValidationUtils.validJobForUpdate(jobDTOFalse);
		}catch (ResponseException e){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void validCompanyForUpdate() {
		Random random=new Random();
		CompanyDTO companyDTOTrue = new CompanyDTO(random.nextLong(),"company" + random.nextInt(),
				random.nextInt(10), "bio" + random.nextInt(),
				"addres" + random.nextInt(), "logo" + random.nextInt());
		CompanyDTO companyDTOFalse = new CompanyDTO(random.nextLong(),"company" + random.nextInt(),
				random.nextInt(100), "bio" + random.nextInt(),
				"addres" + random.nextInt(), "logo" + random.nextInt());
		ValidationUtils.validCompanyForUpdate(companyDTOTrue);
		try {
			ValidationUtils.validCompanyForUpdate(companyDTOFalse);
		}catch (ResponseException e){
			Assert.assertTrue(true);
		}
	}
}
