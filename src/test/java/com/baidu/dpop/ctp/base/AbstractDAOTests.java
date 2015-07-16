package com.baidu.dpop.ctp.base;

import org.junit.runner.RunWith;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath*:conf/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManagerTest")
public abstract class AbstractDAOTests extends AbstractTest {
	
	private static final String sqlScriptEncoding = "UTF-8";
	
	public static final String BASE_TABLE_PATH ="classpath:mockDB/table/";
	
	public static final String BASE_DATA_PATH ="classpath:mockDB/data/";
	
	
	protected void executeTables(String tableSqlName, boolean continueOnError) throws DataAccessException {
		Resource resource = this.applicationContext.getResource(String.format("%s%s", BASE_TABLE_PATH,tableSqlName));
		SimpleJdbcTestUtils.executeSqlScript(this.simpleJdbcTemplate, new EncodedResource(resource,
			sqlScriptEncoding), continueOnError);
	}
	
	protected void executeDatas(String tableSqlName, boolean continueOnError) throws DataAccessException {
		Resource resource = this.applicationContext.getResource(String.format("%s%s", BASE_DATA_PATH,tableSqlName));
		SimpleJdbcTestUtils.executeSqlScript(this.simpleJdbcTemplate, new EncodedResource(resource,
			sqlScriptEncoding), continueOnError);
	}
	
	protected void executeDatas(String tableSqlName) throws DataAccessException {
        this.executeDatas(tableSqlName, false);
    }
	
}
