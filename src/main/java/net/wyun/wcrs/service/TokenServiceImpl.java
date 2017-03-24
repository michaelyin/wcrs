/**
 * 
 */
package net.wyun.wcrs.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.wyun.wcrs.config.WechatProperties;
import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.po.Token;

/**
 * @author michael
 *
 */
@Component
@EnableScheduling
public class TokenServiceImpl implements TokenService {

	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	private WechatProperties wcrsProperties;
	
	private Token token;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	
	@Scheduled(fixedDelayString = "${wechat.token.update.interval}")
	public void updateToken() {
		
		logger.info("### [Token Svc] on " + dateFormat.format(new Date()));
		token = CommonUtil.getToken("", "");
		
	}
	
	private static long ONE_MINUTE = 60 * 1000;
	
	@PostConstruct
    public void init() {
		logger.info("token service: initialising ...");
		CommonUtil.APPID = wcrsProperties.getAppId();
		CommonUtil.APPSECRET = wcrsProperties.getAppSecret();
		this.updateToken();
		logger.info("token service appid: " + CommonUtil.APPID);
	}

	@Override
	public Token getToken() {
		return token;
	}

	
}