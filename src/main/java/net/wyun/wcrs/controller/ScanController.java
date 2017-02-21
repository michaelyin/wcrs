/**
 * 
 */
package net.wyun.wcrs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.model.ScanInfo;

/**
 * @author michael
 *
 */
@RestController
public class ScanController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScanController.class);
	
	@RequestMapping(value="/barcode", method=RequestMethod.POST)
	ScanInfo scan(@RequestBody ScanInfo si){
		Long pid = si.getPublicId();
		Long uid = si.getUserId();
		logger.debug("public id " + pid + ", user id: " + uid);
		//send to notice to parent id of uid
		
		//get barcode for this uid: content
		si.setContent("my 2-d barcode here");
		
		return si;
	}

}
