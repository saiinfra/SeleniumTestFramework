package com.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
// @SuiteClasses({ SeleniumNewtoursTest.class, SalesforceTest.class,
// SampleTest.class, SeleniumCITest.class })
@SuiteClasses({ SeleniumNewtoursTest.class,
	SampleAddTest.class })
public class RunSalesForceTestSuite {
}
