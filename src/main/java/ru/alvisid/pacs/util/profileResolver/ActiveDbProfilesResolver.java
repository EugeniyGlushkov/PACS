package ru.alvisid.pacs.util.profileResolver;

import org.springframework.test.context.ActiveProfilesResolver;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class for programmatically resolving active DB profile which should be used
 * when loading the spring context for the test class.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @see ActiveProfilesResolver
 */
public class ActiveDbProfilesResolver implements ActiveProfilesResolver {

    /**
     * Resolve the <em>bean definition profiles</em> to use when loading an
     * {@code ApplicationContext}.
     *
     * @param aClass the test class for which the profiles should be resolved.
     * @return the list of bean definition profiles to use when loading the
     * {@code ApplicationContext}.
     * @see ActiveProfilesResolver#resolve(Class)
     */
    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
