package ir.khu.jaobshaar.service.dto.employer;

import ir.khu.jaobshaar.service.dto.DTOBase;

public class CompanyDTO extends DTOBase {

    private String name;

    private Integer categoryTypeIndex;

    private String bio;

    private String address;

    private String logoDataUrl;

    public CompanyDTO(Long id ,String name, Integer categoryTypeIndex, String bio, String address, String logoDataUrl) {
        super.setId(id);
        this.name = name;
        this.categoryTypeIndex = categoryTypeIndex;
        this.bio = bio;
        this.address = address;
        this.logoDataUrl = logoDataUrl;
    }

    public CompanyDTO(String name, Integer categoryTypeIndex, String bio, String address, String logoDataUrl) {
        this.name = name;
        this.categoryTypeIndex = categoryTypeIndex;
        this.bio = bio;
        this.address = address;
        this.logoDataUrl = logoDataUrl;
    }

    public CompanyDTO() {
    }

    public String getLogoDataUrl() {
        return logoDataUrl;
    }

    public void setLogoDataUrl(String logoDataUrl) {
        this.logoDataUrl = logoDataUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryTypeIndex() {
        return categoryTypeIndex;
    }

    public void setCategoryTypeIndex(Integer categoryTypeIndex) {
        this.categoryTypeIndex = categoryTypeIndex;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
