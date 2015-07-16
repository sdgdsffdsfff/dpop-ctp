package com.baidu.dpop.ctp.base;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 
 * @author huhailiang
 * @date 2014-7-4下午4:21:27
 */
@SuppressWarnings("deprecation")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
	
    /**
     * 业务数据库
     */
    @Autowired
    @Qualifier(value = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }
	
    
    
	
}
