package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.OnboardingTests;
import tests.SearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        OnboardingTests.class,
        SearchTests.class
})
public class TestSuite {
}
