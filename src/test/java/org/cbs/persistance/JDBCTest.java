package org.cbs.persistance;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;


import lombok.extern.log4j.Log4j2;


@Log4j2
public class JDBCTest {
		
		static {
			try {
				// Class.forName() : 동적 클래스 로딩메서드 -> 오라클 JDBC 드라이버 클래스 로딩됨   
				// Class.forName("oracle.jdbc.driver.OracleDriver"); 오래된 드라이버 클래스권장 X
				Class.forName("oracle.jdbc.pool.OracleDataSource");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void testConnection() {
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "book_ex","book_ex")) {
				log.info(con);
				
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
}

