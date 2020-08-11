package ir.khu.jaobshaar.service.dto;

public class JobDTO extends DTOBase {

    private Integer categoryTypeIndex;

    private Integer cooperationTypeIndex;

    private Integer requiredGenderTypeIndex;

    private String description;

    private String title;

    public JobDTO(Long  id , Integer categoryTypeIndex, Integer cooperationTypeIndex, Integer requiredGenderTypeIndex, String description, String title) {
        super.setId(id);
        this.categoryTypeIndex = categoryTypeIndex;
        this.cooperationTypeIndex = cooperationTypeIndex;
        this.requiredGenderTypeIndex = requiredGenderTypeIndex;
        this.description = description;
        this.title = title;
    }

    public JobDTO(Integer categoryTypeIndex, Integer cooperationTypeIndex, Integer requiredGenderTypeIndex, String description, String title) {
        this.categoryTypeIndex = categoryTypeIndex;
        this.cooperationTypeIndex = cooperationTypeIndex;
        this.requiredGenderTypeIndex = requiredGenderTypeIndex;
        this.description = description;
        this.title = title;
    }

    public JobDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryTypeIndex() {
        return categoryTypeIndex;
    }

    public void setCategoryTypeIndex(Integer categoryTypeIndex) {
        this.categoryTypeIndex = categoryTypeIndex;
    }

    public Integer getCooperationTypeIndex() {
        return cooperationTypeIndex;
    }

    public void setCooperationTypeIndex(Integer cooperationTypeIndex) {
        this.cooperationTypeIndex = cooperationTypeIndex;
    }

    public Integer getRequiredGenderTypeIndex() {
        return requiredGenderTypeIndex;
    }

    public void setRequiredGenderTypeIndex(Integer requiredGenderTypeIndex) {
        this.requiredGenderTypeIndex = requiredGenderTypeIndex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
