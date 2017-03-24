package net.wyun.wcrs.service;

import org.springframework.stereotype.Service;

@Service
public interface JinShuJuHandler {

	/**
	 * 
	 * @param openId
	 * @return  ticket, String
	 */
	void handle(String openId);
}
