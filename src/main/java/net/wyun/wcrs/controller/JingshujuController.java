/**
 * 
 */
package net.wyun.wcrs.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import net.wyun.wcrs.jsj.JSJFormInfo;
import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.UserRepository;
import net.wyun.wcrs.model.UserStatus;

/**
 * @author Xuecheng
 *
 */

@RequestMapping("/secure")
@RestController
public class JingshujuController {
	
	private static final Logger logger = LoggerFactory.getLogger(JingshujuController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@CrossOrigin
	@RequestMapping(value= "/jsj", method=RequestMethod.POST)
	String saveUser(@RequestBody JSJFormInfo data){
		logger.info("jingshuju data: " + data.getFormName() + data.getEntry());
		
		String oid = data.getEntry().getXFieldWeixinOpenid();
		User u = userRepo.findByOpenID(oid);
		if(null == u){
			throw new RuntimeException("user not exist for oid: " + oid);
		}
		u.setStatus(UserStatus.REGISTERED);
		u.setModify_t(new Date());
		userRepo.save(u);
		return oid;
	}

}
