package net.wyun.wcrs.service;

import org.springframework.stereotype.Service;

import net.wyun.wcrs.wechat.po.Token;

@Service
public interface TokenService {

	Token getToken();
}
