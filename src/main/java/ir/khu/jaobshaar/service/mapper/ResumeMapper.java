package ir.khu.jaobshaar.service.mapper;

import ir.khu.jaobshaar.entity.model.Resume;
import ir.khu.jaobshaar.service.domain.ResumeDomain;
import ir.khu.jaobshaar.service.dto.ResumeDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {EmployeeMapper.class})
public abstract class ResumeMapper implements EntityMapperBase<ResumeDTO, ResumeDomain, Resume> {
    @Override
    public Resume createNew() {
        return new Resume();
    }

    @AfterMapping
    void setEmail (@MappingTarget ResumeDomain domain,Resume resume){
        domain.setEmail(resume.getEmployee().getEmail());
    }
}
