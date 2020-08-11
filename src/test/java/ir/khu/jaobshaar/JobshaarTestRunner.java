package ir.khu.jaobshaar;

import ir.khu.jaobshaar.services.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CompanyServiceTest.class,
        EmployeeServiceTest.class,
        EmployerServiceTest.class,
        FileStorageServiceTest.class,
        JobServiceTest.class,
        UserServiceTest.class,
        ValidationUtilsTest.class
})
public final class JobshaarTestRunner {
}
