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
import net.wyun.wcrs.service.JinShuJuHandler;
import net.wyun.wcrs.service.SceneIdService;

/**
 * @author Xuecheng
 *
 */

@RequestMapping("/secure")
@RestController
public class JingshujuController {
	
	private static final Logger logger = LoggerFactory.getLogger(JingshujuController.class);
	
	@Autowired
	JinShuJuHandler handler;
	
	@CrossOrigin
	@RequestMapping(value= "/jsj", method=RequestMethod.POST)
	String regiesterUser(@RequestBody JSJFormInfo data){
		logger.info("jingshuju data: " + data.getFormName() + data.getEntry());
		
		String oid = data.getEntry().getXFieldWeixinOpenid();
		handler.handle(oid);
		
		return oid;
	}
	
	
	

}
