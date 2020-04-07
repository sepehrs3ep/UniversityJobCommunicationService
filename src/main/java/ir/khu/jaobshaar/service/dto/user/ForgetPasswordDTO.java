package ir.khu.jaobshaar.service.dto.user;

public class ForgetPasswordDTO {
    private String newPass;
    private String repeatNewPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRepeatNewPass() {
        return repeatNewPass;
    }

    public void setRepeatNewPass(String repeatNewPass) {
        this.repeatNewPass = repeatNewPass;
    }
}
