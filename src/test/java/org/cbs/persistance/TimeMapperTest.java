package org.cbs.persistance;

import org.cbs.mapper.TimeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class TimeMapperTest {
	
	@Autowired
	// 인터페이스만 만들었는데, 객체가 들어감
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info("timeMapper.getClass().getName() --> {}", timeMapper.getClass().getName());
		log.info("timeMapper.getTime() --> {} ", timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("timeMapper.getTime2() --> {} ", timeMapper.getTime2());
	}
}
