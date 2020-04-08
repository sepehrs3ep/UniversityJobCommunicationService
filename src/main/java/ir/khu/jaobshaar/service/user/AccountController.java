package ir.khu.jaobshaar.service.user;

import ir.khu.jaobshaar.component.user.UserManager;
import ir.khu.jaobshaar.service.dto.user.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/account")
public class AccountController {

    private final UserManager userManager;

    public AccountController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentAccount() {
        return ResponseEntity.ok(userManager.getCurrentUser());
    }

    @PostMapping
    public ResponseEntity<?> forgetPassword(String email){
        userManager.forgetPassWord(email);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(ChangePasswordDTO changePasswordDTO){
        userManager.resetPassword(changePasswordDTO);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(ChangePasswordDTO changePasswordDTO){
        userManager.resetPassword(changePasswordDTO);
        return ResponseEntity.ok("ok");
    }
}
