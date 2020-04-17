package ir.khu.jaobshaar.utils.validation.translator;

import ir.khu.jaobshaar.utils.validation.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

public class PersianErrorCodesTranslator implements ErrorCodesTranslator {

    private final static Map<Integer, String> errorCodesMap = new HashMap<>();

    static {
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_USER_NAME,
                "نام کاربری اشتباه است."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_PASSWORD,
                "حداقل تعداد گذرواژه 5 رقم میباشد. "
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_EMAIL,
                "ایمیل وارد شده قابل قبول نمیباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_USER_ALREADY_EXIST,
                "نام کاربری در سیستم موجود میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_USER_NOT_FOUND,
                "نام کاربری پیدا نشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_COMPANY_ALREADY_EXIST,
                "شرکت مورد نظر موجود میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_COMPANY_FIELD,
                "اطلاعات نام شرکت خالی میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_STUDENT_NOT_FOUND,
                "دانشجو یافت نشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMPLOYER_ALREADY_HAS_COMPANY,
                "شرکت قبلا ثبت شده است."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_JOB_FIELD,
                "اطلاعات شغل خالی میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED,
                "انجام فرایند امکان پذیر نمیباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMAIL_ALREADY_EXIST,
                "ایمیل وارد شده در سیستم موجود میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_RESUME_URL,
                "لینک وارد شده قابل قبول نمیباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_ALREADY_EXIST,
                "رزومه در سیستم موجود میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST,
                "رزومه ای یافت نشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_URL_ALREADY_EXIST,
                "رزومه در سیستم موجود میباشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_JOB_NOT_FOUND,
                "شغل یافت نشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_REQUIRED_GENDER_NOT_ACCESS,
                "شرایط جنسیت مورد نظر رعایت نشده است."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_MALFORMED_URL_EXCEPTION,
                "خطایی رخ داده است لطفا لحظاتی بعد تلاش کنید."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_CANT_CREATE_DIRECTORY,
                "خطایی رخ داده است لطفا لحظاتی بعد تلاش کنید."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMAIL_NOT_EXIST,
                "ایمیل یافت نشد."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_TAKEN_PASSWORD_BEFORE,
                "گذرواژه قبلا استفاده شده است."
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INPUT_PASSWORDS_NOT_MATCH,
                "عدم مطابقت گذرواژه ها"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_OLD_PASS,
                "عدم مطابقت گذرواژه قبلی."
        );
    }

    @Override
    public Map<Integer, String> getDictionary() {
        return errorCodesMap;
    }
}
