package net.wyun.wcrs.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.wyun.wcrs.BaseSpringTestRunner;
import net.wyun.wcrs.model.UserRepository;
import net.wyun.wcrs.model.UserStatus;
import net.wyun.wcrs.model.Gender;
import net.wyun.wcrs.model.Province;
import net.wyun.wcrs.model.User;


public class UserRepositoryTest extends BaseSpringTestRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void saveOCLG(){
		User o = new User();
		o.setOpenID("ff8081814da00e2b014da00f32260002");
		o.setSceneID(0);
		o.setParent(1);
		o.setNickName("test");
		o.setGender(Gender.MALE);
		o.setLanguage("en");
		o.setCity("北京");
		o.setProvince("上海");
		o.setCountry("China");
		o.setHeadimgurl("/head/image/test");
		o.setCreatet(new Date());
		o.setTicket("test ticket 1121");
		o.setStatus(UserStatus.REGISTERED);
		userRepository.save(o);
		
	}
	
	@Test
	public void findAll() {
		assertThat(userRepository.findAll()).isNotEmpty();
		Iterable<User> oclgs = userRepository.findAll();
		for(User o:oclgs){
			System.out.println(o.toString());
		}
	}
	
}
