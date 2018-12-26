package util;

import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Rules to test time monitoring.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class TimingRules {
    /**
     * Logger.
     */
    public static Logger log = getLogger("result");

    /**
     * The executing tests results.
     */
    private static StringBuilder results = new StringBuilder();

    /**
     * Rule - after every test method commits test method executing time to the {@code results} field.
     */
    public static final Stopwatch STOPWATCH = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            log.info(result + " ms\n");
        }
    };

    /**
     * Rule - for a test class.
     * Before reset {@code results} field.
     * After print test results.
     */
    public static final ExternalResource SUMMARY = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            results.setLength(0);
        }

        @Override
        protected void after() {
            log.info("\n-------------------------------------------------------------------------------------------------------" +
                    "\nTest                                                                                       Duration, ms" +
                    "\n-------------------------------------------------------------------------------------------------------\n" +
                    results +
                    "-------------------------------------------------------------------------------------------------------\n");
        }
    };
}
