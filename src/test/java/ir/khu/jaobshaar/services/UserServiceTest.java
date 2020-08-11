package ir.khu.jaobshaar.services;

import ir.khu.jaobshaar.TestCase;
import ir.khu.jaobshaar.component.user.UserManager;
import ir.khu.jaobshaar.service.dto.user.ChangePasswordDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class UserServiceTest extends TestCase {

    @Mock
    private UserManager userManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ChangePasswordDTO changePasswordDTO;


    @Test
    public void assertInjectableFieldsAreNonNull() {
        Assert.assertNotNull(userManager);
        Assert.assertNotNull(passwordEncoder);
        Assert.assertNotNull(changePasswordDTO);
    }

    @Test
    public void resetPassword() {
        String pass = String.valueOf(new Random().nextLong());
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("123456", pass, pass);
        userManager.resetPassword(changePasswordDTO);
    }

    @Override
    public void tearDown() {

    }

    @Override
    public void setup() {

    }
}
