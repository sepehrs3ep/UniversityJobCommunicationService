package ir.khu.jaobshaar.service.domain;

import java.util.Map;

public class ErrorCodesDomain {
    private Map<Integer, String> errorCodesTranslator;

    public ErrorCodesDomain(Map<Integer, String> errorCodesTranslator) {
        this.errorCodesTranslator = errorCodesTranslator;
    }

    public Map<Integer, String> getErrorCodesTranslator() {
        return errorCodesTranslator;
    }

    public void setErrorCodesTranslator(Map<Integer, String> errorCodesTranslator) {
        this.errorCodesTranslator = errorCodesTranslator;
    }
}
