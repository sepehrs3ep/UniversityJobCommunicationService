package ir.khu.jaobshaar;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Base test class
 */
public abstract class TestCase {
    public TestCase() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    abstract public void tearDown();

    @Before
    abstract public void setup();
}
