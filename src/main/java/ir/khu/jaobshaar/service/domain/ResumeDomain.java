package ir.khu.jaobshaar.service.domain;

public class ResumeDomain extends DomainBase {
    private String url;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
