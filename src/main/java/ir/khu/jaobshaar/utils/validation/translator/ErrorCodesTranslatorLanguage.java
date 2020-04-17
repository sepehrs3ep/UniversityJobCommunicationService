package ir.khu.jaobshaar.utils.validation.translator;

import ir.khu.jaobshaar.utils.EnumProviderKey;
import ir.khu.jaobshaar.utils.EnumUtil;

public enum ErrorCodesTranslatorLanguage implements EnumProviderKey<Integer> {
    PERSIAN(0),
    ENGLISH(1);

    private int key;

    ErrorCodesTranslatorLanguage(int key) {
        this.key = key;
    }

    public static ErrorCodesTranslatorLanguage fromKey(int key) {
        return EnumUtil.fromKey(ErrorCodesTranslatorLanguage.class, key);
    }

    @Override
    public Integer toKey() {
        return key;
    }
}