package net.wyun.wcrs.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.wyun.wcrs.model.UserRepository;

@Component
public class SceneIdServiceImpl implements SceneIdService {
	
	private static final Logger logger = LoggerFactory.getLogger(SceneIdServiceImpl.class);
	
	@Autowired
	UserRepository userRepo;
	
	AtomicInteger s_id;

	/**
	 * need synchronize this call
	 * @return
	 */
	public int nextSceneID(){
		
		return s_id.incrementAndGet();
	}
	
	@PostConstruct
    public void init() {
		logger.info("SceneId service: initialising ...");
		int currentMax = userRepo.findMaxSceneID();
		s_id = new AtomicInteger(currentMax);
		logger.info("SceneId service current s_id: " + s_id);
	}
}
