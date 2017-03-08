/**
 * 
 */
package net.wyun.wcrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/secure")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value= "/user", method=RequestMethod.POST)
	User saveUser(@RequestBody User user){
		
		return userRepo.save(user);
	}

}
