package ir.khu.jaobshaar.component.user;

import ir.khu.jaobshaar.config.jwt.JwtTokenUtil;
import ir.khu.jaobshaar.config.jwt.JwtUserDetailsService;
import ir.khu.jaobshaar.entity.model.User;
import ir.khu.jaobshaar.repository.UserRepository;
import ir.khu.jaobshaar.service.domain.UserDomain;
import ir.khu.jaobshaar.service.dto.user.ChangePasswordDTO;
import ir.khu.jaobshaar.service.mapper.UserMapper;
import ir.khu.jaobshaar.utils.EmailService;
import ir.khu.jaobshaar.utils.ThreadUtil;
import ir.khu.jaobshaar.utils.ValidationUtils;
import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class UserManager {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private JwtUserDetailsService jwtUserDetailsService;

    public UserManager(JwtUserDetailsService jwtUserDetailsService, UserMapper userMapper,
                       UserRepository userRepository, EmailService emailService, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder
    ) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDomain getCurrentUser() {
        return userMapper.toDomain(jwtUserDetailsService.getCurrentUser());
    }

    public void forgetPassWord(String username) {
        final String forgetPasswordPageUrl = "Http://188.40.195.134:8081/account/reset-password?key=";
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new ResponseException(ErrorCodes.ERROR_CODE_EMAIL_NOT_EXIST, "email.not.exist");
        final String token = jwtTokenUtil.generateToken(user);
        ThreadUtil.createThreadAndStart(() -> {
            try {
                emailService.sendEmailWithLink(user.getEmail(), "change password",
                        "dear " + user.getUsername() + "<br>" + " please check this url out to change password",
                        forgetPasswordPageUrl + token);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void resetPassword(ChangePasswordDTO changePasswordDTO) {
        User currentUser = jwtUserDetailsService.getCurrentUser();
        ValidationUtils.validNewPasswords(changePasswordDTO, currentUser.getPassword(), passwordEncoder);
        currentUser.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPass()));
        userRepository.save(currentUser);
    }
}
