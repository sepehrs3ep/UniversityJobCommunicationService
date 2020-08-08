package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.component.user.UserManager;
import ir.khu.jaobshaar.service.dto.user.ChangePasswordDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class UserServiceTest {

	@Test
	public void resetPassword(){
		UserManager userManager= Mockito.mock(UserManager.class);
		PasswordEncoder passwordEncoder =Mockito.mock(PasswordEncoder.class);
		String pass = String.valueOf(new Random().nextLong());
		ChangePasswordDTO changePasswordDTO=new ChangePasswordDTO("123456",pass,pass);
		userManager.resetPassword(changePasswordDTO);
	}
}
