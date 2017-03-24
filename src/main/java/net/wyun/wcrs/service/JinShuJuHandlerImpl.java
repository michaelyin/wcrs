/**
 * 
 */
package net.wyun.wcrs.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.wcrs.controller.WechatController;
import net.wyun.wcrs.model.User;
import net.wyun.wcrs.model.UserRepository;
import net.wyun.wcrs.model.UserStatus;
import net.wyun.wcrs.wechat.AdvancedUtil;

/**
 * @author michael
 *
 */
@Component
public class JinShuJuHandlerImpl implements JinShuJuHandler{

	private static final Logger logger = LoggerFactory.getLogger(JinShuJuHandlerImpl.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SceneIdService sceneIdService;
	
	@Autowired
	TokenService tokenService;
	
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
		
	public class Handler implements Runnable{

	    protected String oid = null;

	    public Handler(String oid) {
	        this.oid = oid;
	    }

	    public void run() {
	        try {
	        	User u = userRepo.findByOpenID(oid);
	    		if(null == u){
	    			throw new RuntimeException("user not exist for oid: " + oid);
	    		}
	    		
	    		if( u.getSceneID() == 0){
	    			int s_id = sceneIdService.nextSceneID();
	    			u.setSceneID(s_id);
	    			
	    			//get QR code
	    			String accessToken = tokenService.getToken().getAccessToken();
	    			String ticket = AdvancedUtil.createPermanentQRCode(accessToken, s_id);
	    			
	    			u.setTicket(ticket);
	    		}
	    		
	    		u.setStatus(UserStatus.REGISTERED);
	    		u.setModify_t(new Date());
	    		userRepo.save(u);
	        	
	        } catch (Exception e) {
	            logger.error("", e);
	        }
	    }
	}

	@Override
	public void handle(String openId) {
		logger.info("process: " + openId);
		executor.execute(new Handler(openId));
	}

}
