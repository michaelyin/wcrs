/**
 * 
 */
package net.wyun.wcrs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import net.wyun.wcrs.wechat.SignUtil;

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
	
	@RequestMapping(value= "/wechat", method=RequestMethod.GET)
	void handShake(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		logger.debug("signature, timestamp, echostr: " + signature + ", " + timestamp + ", " + echostr);

		PrintWriter out = response.getWriter();
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}else{
			logger.info("signature check fails!" + request.getRemoteHost());
		}
		out.close();
		out = null;

	}

}
