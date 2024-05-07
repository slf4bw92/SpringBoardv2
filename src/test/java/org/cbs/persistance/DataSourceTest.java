package org.cbs.persistance;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

// 해당 테스트에서 Spring ApplicationContext 사용 해야할 때 적용 (DataSourceTests가 빈으로 관리됨)
@ExtendWith(SpringExtension.class)
// Spring ApplicationContext 구성할때 해당경로 파일 참조하여 구성 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class DataSourceTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	@DisplayName("MyBatis 연동 테스트")
	public void testMyBatis() {
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		try {
			log.info("{}",session);
			log.info("{}",conn);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	@DisplayName("커넥션 풀 테스트")
	public void testConnection() {
		
		try ( Connection conn = dataSource.getConnection()) {
			log.info("{}", conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	

}
