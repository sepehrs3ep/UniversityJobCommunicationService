package ir.khu.jaobshaar.component.configs;

import ir.khu.jaobshaar.service.domain.ErrorCodesDomain;
import ir.khu.jaobshaar.service.dto.ErrorCodesTranslatorDTO;
import ir.khu.jaobshaar.service.dto.SubEnumDTO;
import ir.khu.jaobshaar.utils.EnumProviderKey;
import ir.khu.jaobshaar.utils.PackageClassesUtil;
import ir.khu.jaobshaar.utils.validation.translator.ErrorCodeTranslatorFactory;
import ir.khu.jaobshaar.entity.enums.ErrorCodesTranslatorLanguageType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigsManager {

    public ErrorCodesDomain getErrorCodesTranslator(final ErrorCodesTranslatorDTO errorCodesTranslatorDTO) {
        return new ErrorCodesDomain(
                ErrorCodeTranslatorFactory.getTranslatorMap(
                        ErrorCodesTranslatorLanguageType.fromKey(errorCodesTranslatorDTO.getLanguage())
                )
        );
    }

    public Map<String, List<SubEnumDTO>> getEnums() {
        Map<String, List<SubEnumDTO>> enumsDTOMap = new HashMap<>();
        List<Class> classes = PackageClassesUtil.getClasses("ir.khu.jaobshaar.entity.enums");
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isEnum())
                enumsDTOMap.put(classes.get(i).getSimpleName(),
                        toSubEnumDTOList((EnumProviderKey[]) classes.get(i).asSubclass(EnumProviderKey.class).getEnumConstants()));
        }
        return enumsDTOMap;
    }

    public List<SubEnumDTO> toSubEnumDTOList(EnumProviderKey[] enums) {
        List<SubEnumDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < enums.length; i++) {
            dtoList.add(toSubEnumDTO(String.valueOf(enums[i].toKey()), enums[i].getName()));
        }
        return dtoList;
    }

    public SubEnumDTO toSubEnumDTO(String key, String value) {
        SubEnumDTO subEnumDTO = new SubEnumDTO();
        subEnumDTO.setKey(key);
        subEnumDTO.setValue(value);
        return subEnumDTO;
    }
}
