/**
 * 
 */
package net.wyun.wcrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.UserRepository;

/**
 * @author Xuecheng
 *
 */
@CrossOrigin
@RequestMapping("/secure")
@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value= "/user", method=RequestMethod.POST)
	User saveUser(@RequestBody User user){
		
		return userRepo.save(user);
	}
	
	@RequestMapping(value= "/user/{oid}", method=RequestMethod.GET)
	User getUser(@PathVariable("oid") String openId){
		return userRepo.findByOpenID(openId);
	}
	
	@RequestMapping(value= "/bonus/{oid}", method=RequestMethod.GET)
	String getUserBonus(@PathVariable("oid") String openId){
		return "$25.88";
	}
	
	

}
