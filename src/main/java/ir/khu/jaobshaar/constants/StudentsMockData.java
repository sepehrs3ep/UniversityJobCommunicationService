package ir.khu.jaobshaar.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsMockData {
    private static Map<String, List<String>> studentMap = new HashMap<>();

    public static boolean isStudentExist(
            final String username,
            final String password
    ) {
        if (!studentMap.containsKey(username)) {
            return false;
        }

        final String existPassword = studentMap.get(username).get(0);

        return password.equalsIgnoreCase(existPassword);
    }

    public static Integer getGenderTypeIndex(String username) {
        return Integer.valueOf(studentMap.get(username).get(1));
    }

    public static void setStudentMap(Map<String, List<String>> studentMap) {
        StudentsMockData.studentMap = studentMap;
    }
}
