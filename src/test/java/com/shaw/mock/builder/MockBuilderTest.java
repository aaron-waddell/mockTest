package com.shaw.mock.builder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shaw.mock.object.BeanObject;
import com.shaw.mock.object.DatabaseObject;
import com.shaw.mock.object.NestedObject;
import com.shaw.mock.object.ParentObject;

class MockBuilderTest {

    protected static final Logger logger = LogManager.getLogger();

    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreate() {
		MockBuilder mockBuilder = new MockBuilder();
		NestedObject result = mockBuilder.build(NestedObject.class);
		logger.info(result.toString());
		assertNotEquals("",result.getStr1());
		assertNotNull(result.getIo());
		assertNotEquals("",result.getIo().getIstr1());		
		assertNotEquals("",((ParentObject)result).getPs1());		
		assertNotNull(result.getList1());
		assertNotEquals("",result.getList1().get(0).getLs1());		
	}

	@Test
	void testBean() {
		MockBuilder mockBuilder = new MockBuilder();
		BeanObject result = mockBuilder.build(BeanObject.class);
		logger.info(result.toString());
		assertNotEquals("",result.getBstr1());
	}
	
	@Test
	void testCreateMapByColumn() {
		MockBuilder mockBuilder = new MockBuilder();
		DatabaseObject databaseObject = mockBuilder.build(DatabaseObject.class);
		logger.info(databaseObject.toString());
		Map<String, Object> result = mockBuilder.buildMapOfColumns(databaseObject );
		logger.info(result.toString());
		assertNull(result.get("dbstr1"));
		assertNotNull(result.get("db_str_1"));
	}
	
	@Test
	void testCreateMapByName() {
		MockBuilder mockBuilder = new MockBuilder();
		DatabaseObject databaseObject = mockBuilder.build(DatabaseObject.class);
		logger.info(databaseObject.toString());
		Map<String, Object> result = mockBuilder.buildMapByFieldname(databaseObject );
		logger.info(result.toString());
		assertNull(result.get("db_str_1"));
		assertNotNull(result.get("dbstr1"));
	}

}
