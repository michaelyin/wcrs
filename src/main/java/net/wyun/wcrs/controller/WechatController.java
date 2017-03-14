/**
 * 
 */
package net.wyun.wcrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.jsj.JSJFormInfo;
import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.UserRepository;

/**
 * @author Xuecheng
 *
 */
@RequestMapping("/secure")
@RestController
public class WechatController {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value= "/wechat", method=RequestMethod.POST)
	String saveUser(@RequestBody String data){
		logger.info("wechat data: " + data);
		return data;
	}

}
