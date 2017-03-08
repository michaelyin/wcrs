package net.wyun.wcrs.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserTest {

	@Test
	public void user2JsonTest() throws JsonProcessingException {
		User o = new User();
		
		o.setOpenID("ff8081814da00e2b014da00f32260001");
		o.setSceneID(888);
		o.setParent(999);
		o.setNickName("test");
		o.setGender(Gender.MALE);
		o.setCity("北京");
		o.setProvince(Province.上海);
		o.setCountry("China");
		o.setHeadimgurl("/head/image/test");
		o.setCreatet(new Date());
		o.setTicket("test ticket 1121");
		
		ObjectMapper mapper = new ObjectMapper();
		String r_str = mapper.writeValueAsString(o);
		System.out.println(r_str);
		
	}

}
