package ir.khu.jaobshaar.component.configs;

import ir.khu.jaobshaar.service.domain.ErrorCodesDomain;
import ir.khu.jaobshaar.service.dto.ErrorCodesTranslatorDTO;
import ir.khu.jaobshaar.utils.validation.translator.ErrorCodeTranslatorFactory;
import ir.khu.jaobshaar.utils.validation.translator.ErrorCodesTranslatorLanguage;
import org.springframework.stereotype.Service;

@Service
public class ConfigsManager {

    public ErrorCodesDomain getErrorCodesTranslator(final ErrorCodesTranslatorDTO errorCodesTranslatorDTO) {
        return new ErrorCodesDomain(
                ErrorCodeTranslatorFactory.getTranslatorMap(
                        ErrorCodesTranslatorLanguage.fromKey(errorCodesTranslatorDTO.getLanguage())
                )
        );
    }

}
