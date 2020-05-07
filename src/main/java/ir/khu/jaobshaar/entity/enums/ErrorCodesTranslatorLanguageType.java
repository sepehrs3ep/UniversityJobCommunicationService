package ir.khu.jaobshaar.entity.enums;

import ir.khu.jaobshaar.utils.EnumProviderKey;
import ir.khu.jaobshaar.utils.EnumUtil;

public enum ErrorCodesTranslatorLanguageType implements EnumProviderKey<Integer> {
    PERSIAN(0),
    ENGLISH(1);

    private int key;

    ErrorCodesTranslatorLanguageType(int key) {
        this.key = key;
    }

    public static ErrorCodesTranslatorLanguageType fromKey(int key) {
        return EnumUtil.fromKey(ErrorCodesTranslatorLanguageType.class, key);
    }

    @Override
    public Integer toKey() {
        return key;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
