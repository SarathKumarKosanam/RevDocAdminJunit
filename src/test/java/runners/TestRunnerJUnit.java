package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty,"
              + "html:target/cucumber-reports/cucumber.html,"
              + "json:target/cucumber-reports/cucumber.json,"
              + "junit:target/cucumber-reports/cucumber.xml,"
              + "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")
public class TestRunnerJUnit {}
