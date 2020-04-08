package ir.khu.jaobshaar.service.dto.user;

public class ChangePasswordDTO {
    private String oldPass;
    private String newPass;
    private String repeatNewPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

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
