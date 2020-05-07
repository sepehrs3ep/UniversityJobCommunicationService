package ir.khu.jaobshaar.utils.validation.translator;

import ir.khu.jaobshaar.utils.validation.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

public class EnglishErrorCodesTranslator implements ErrorCodesTranslator {

    private final static Map<Integer, String> errorCodesMap = new HashMap<>();

    static {
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_USER_NAME,
                "Invalid User Name"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_PASSWORD,
                "Minimum characters of password is 5"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_EMAIL,
                "Invalid email"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_USER_ALREADY_EXIST,
                "Username already exist in the system"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_USER_NOT_FOUND,
                "Username not found"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_COMPANY_ALREADY_EXIST,
                "Company already exist"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_COMPANY_FIELD,
                "Company field is empty"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_STUDENT_NOT_FOUND,
                "Student not found"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMPLOYER_ALREADY_HAS_COMPANY,
                "Company already exist"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_JOB_FIELD,
                "Job fields are empty"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_ACCESS_NOT_PERMITTED,
                "Access Denied"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMAIL_ALREADY_EXIST,
                "Email address already exist"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_RESUME_URL,
                "Invalid url"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_ALREADY_EXIST,
                "Resume already exist in the system"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST,
                "Resume not found"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_RESUME_URL_ALREADY_EXIST,
                "Resume already exist"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_JOB_NOT_FOUND,
                "Job not found"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_REQUIRED_GENDER_NOT_ACCESS,
                "Required gender condition is not conformity"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_MALFORMED_URL_EXCEPTION,
                "Error happen with your request please try again later"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_CANT_CREATE_DIRECTORY,
                "Error happen with your request please try again later"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_EMAIL_NOT_EXIST,
                "Email not found"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_TAKEN_PASSWORD_BEFORE,
                "Password is already taken before"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INPUT_PASSWORDS_NOT_MATCH,
                "Passwords is not match"
        );
        errorCodesMap.put(
                ErrorCodes.ERROR_CODE_INVALID_OLD_PASS,
                "Invalid old password used"
        );
    }

    @Override
    public Map<Integer, String> getDictionary() {
        return errorCodesMap;
    }
}
