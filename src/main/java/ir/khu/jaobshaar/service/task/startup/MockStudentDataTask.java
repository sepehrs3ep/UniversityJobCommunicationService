package ir.khu.jaobshaar.service.task.startup;

import ir.khu.jaobshaar.constants.StudentsMockData;
import ir.khu.jaobshaar.service.excel.ReadExcelData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MockStudentDataTask implements StartUpTask {
    private static final String url = "C:\\Users\\fateme\\Desktop\\data.xlsx";

    @Override
    public void run(ApplicationArguments args) {

        // read  data from excel and setting in StudentsMockData.studentMap
        StudentsMockData.setStudentMap(getExcelData());
    }

    private Map<String, List<String>> getExcelData() {
        Map<String, List<String>> students = new HashMap<>();
        ReadExcelData.importExcelData(url).forEach(student -> {
            students.put(student.get(0), Arrays.asList("123456", student.get(1).equals("زن") ? "1" : "0"));
        });
        return students;
    }

    /** 4 bar tekrar mishe
     @EventListener(ApplicationEvent.class) public void doAppEvent(){
     System.err.println("app event");
     }*/

    /** ye bar method ro anjam mide
     @PostConstruct public void doMethodStartUp(){
     System.err.println("post construct");
     }*/
}
