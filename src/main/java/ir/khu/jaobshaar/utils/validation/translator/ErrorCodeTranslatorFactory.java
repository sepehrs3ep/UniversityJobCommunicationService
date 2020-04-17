package ir.khu.jaobshaar.utils.validation.translator;

import java.util.Map;

public class ErrorCodeTranslatorFactory {

    public static Map<Integer, String> getTranslatorMap(ErrorCodesTranslatorLanguage language) {
        ErrorCodesTranslator errorCodesTranslator;
        switch (language) {
            case ENGLISH:
                errorCodesTranslator = new EnglishErrorCodesTranslator();
                break;
            case PERSIAN:
            default:
                errorCodesTranslator = new PersianErrorCodesTranslator();
        }

        return errorCodesTranslator.getDictionary();
    }

}
