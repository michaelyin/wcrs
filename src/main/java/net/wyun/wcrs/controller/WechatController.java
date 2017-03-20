/**
 * 
 */
package net.wyun.wcrs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import net.wyun.wcrs.wechat.AdvancedUtil;
import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.MessageUtil;
import net.wyun.wcrs.wechat.SignUtil;
import net.wyun.wcrs.wechat.message.event.QRCodeEvent;
import net.wyun.wcrs.wechat.message.req.ReqTextMessage;
import net.wyun.wcrs.wechat.message.resp.RespTextMessage;
import net.wyun.wcrs.wechat.po.WeixinUserInfo;

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
	String saveUser(/*@RequestBody String data, */ HttpServletRequest request){
		logger.info("wechat data: ");
		
		// xml格式的消息数据
				String respXml = null;
				try {
					// 调用parseXml方法解析请求消息
					Map<String, String> requestMap = MessageUtil.parseXml(request);
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					// 开发者微信号
					String toUserName = requestMap.get("ToUserName");
					// 消息类型
					String msgType = requestMap.get("MsgType");
					//接收消息内容
					String content = requestMap.get("Content");
					//接收key值
					String eventKey = requestMap.get("EventKey");
					//事件类型
					String event = requestMap.get("Event");
					//扫描事件
					String scan = requestMap.get("scan");
					QRCodeEvent baseEvent = new QRCodeEvent();
					RespTextMessage textMessage = new RespTextMessage();
					ReqTextMessage textMessage2 = new ReqTextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setMsgType(msgType);
					baseEvent.setFromUserName(fromUserName);
					baseEvent.setEvent(event);
					baseEvent.setEventKey(eventKey);
					baseEvent.setMsgType(msgType);
					baseEvent.setToUserName(toUserName);
					textMessage2.setContent(content);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
					// 事件推送
					if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
						// 事件类型
						String eventType = requestMap.get("Event");
						// 订阅
						if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
							
							textMessage.setContent("🎉欢迎关注秦皇岛人才库，为您提供7日内最新招聘及求职信息🎉\n👉<a href = 'https://jinshuju.net/f/tl21JZ\'>点我进行招聘登记</a>\n👉<a href = 'http://shop13308654.ddkwxd.com/tag/231285\'>点我进入简历超市选择优秀人才</a>，我们每天从数以千计的求职者中为您筛选最新、最优质的求职信息，投放到这里，供您选择。\n👉<a href = 'https://jinshuju.net/f/j3iabB\'>点我进行求职登记</a>\n👉<a href = 'http://shop13308654.ddkwxd.com/tag/231300\'>点我进入招聘信息选择优秀企业</a>，我们每天从众多招聘企业中为您筛选最新、最最优质的求职信息，投放到这里，供您选择。\n👉<a href = 'http://zplsyx.iok.la/weixin3/JSP/tuiguang.jsp\'>推广加盟</a>不仅可以帮助您有需要的朋友快速找到优秀人才、满意工作，您还可以赚取收入。回复？可重复查看此重要信息");
							// 将消息对象转换成xml
							respXml = MessageUtil.messageToXml(textMessage);
//							Article article = new Article();
//							article.setTitle("秦皇岛人才库介绍");
//							article.setDescription("秦皇岛人才库作为秦皇岛信息港人才频道的微信服务窗口，以您在短时间内招聘到合适的人才、找到满意的工作为宗旨，以为您提供更加优质、便捷、高效的服务为第一要务。您有人，我们提供招聘信息，您有岗位，我们提供求职信息，合作就是这么简单。");
//							article.setPicUrl("http://zplsyx.iok.la/weixin3/img/home.jpeg");
//							//article.setUrl("http://www.baidu.com");
//							Article article1 = new Article();
//							article1.setTitle("招聘功能");
//							article1.setPicUrl("weixin3/img/home.png");
//							String reurl= "http://zplsyx.iok.la/weixin3/oa.do";
//							String reurls = CommonUtil.urlEncodeUTF8(reurl);
//							article1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx179e17d128a005d0&redirect_uri="+reurls+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirec");
//							List<Article> articleList = new ArrayList<Article>();
//							articleList.add(article);
//							articleList.add(article1);
////							// 创建图文消息
//							NewsMessage newsMessage = new NewsMessage();
//							newsMessage.setToUserName(fromUserName);
//							newsMessage.setFromUserName(toUserName);
//							newsMessage.setCreateTime(new Date().getTime());
//							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//							newsMessage.setArticleCount(articleList.size());
//							newsMessage.setArticles(articleList);
//							respXml = MessageUtil.messageToXml(newsMessage);
							
							String openid = baseEvent.getFromUserName();
							HttpSession session = request.getSession();
							session.setAttribute("openID", openid);
							//request.getRequestDispatcher("/insertServlet").forward(request, null);
							//System.out.println(session.getAttribute("openID"));
							logger.info(openid);
							//System.out.println("eventKey"+eventKey);
							logger.info(eventKey.substring(eventKey.length()-1));
							//获取用户openid等相关信息写入数据库
							//QRCodeEvent qrCodeEvent = DaoFactory.getPersonDaoInstance().insertByopenid(baseEvent);
//							QRCodeEvent qrCodeEvent = DaoFactory.getPersonDaoInstance().selectByopenid(baseEvent);
							String APPID = CommonUtil.APPID;
							String APPSECRET = CommonUtil.APPSECRET;
							String accessToken = CommonUtil.getToken("APPID", "APPSECRET").getAccessToken();
							WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, openid);
							//获取用户openid放入数据库进行判断，如果存在不执行操作，如果不存在，则将用户信息写入数据库
							if (openid != null) {
								//根据用户openid查询其他数据
								//查询openid
								logger.info("openid:"+user.getOpenId());
								//查询昵称
								logger.info("nickname:"+user.getNickname());
								//查询性别
								logger.info("sex:"+user.getSex());
								//查询语言
								logger.info("language:"+user.getLanguage());
								//查询城市
								logger.info("city:"+user.getCity());
								//查询省市
								logger.info("province:"+user.getProvince());
								//查询国家
								logger.info("country:"+user.getCountry());
								//查询头像
								logger.info("headimgurl:"+user.getHeadImgUrl());
								//System.out.println(DaoFactory.getPersonDaoInstance().selectByopenid(qrCodeEvent));
								//DaoFactory.getPersonDaoInstance().selectByopenid(fromUserName);
//								if (DaoFactory.getPersonDaoInstance().selectByopenid(openid)) {
//									//不执行操作
//									System.out.println("不执行操作");
//								}
//								else {
//									System.out.println("插入用户成功");
//									qrCodeEvent = DaoFactory.getPersonDaoInstance().insertByopenid(baseEvent);
//								}
//								if (openid.equals(DaoFactory.getPersonDaoInstance().selectByopenid(openid))) {
//									//暂不做处理
//								}else{  
//								qrCodeEvent = DaoFactory.getPersonDaoInstance().insertByopenid(baseEvent);
//								}
//								qrCodeEvent = DaoFactory.getPersonDaoInstance().selectByopenid(openid);
								//System.out.println(DaoFactory.getPersonDaoInstance().selectByopenid(fromUserName));
							}
						}
						// 取消订阅
						else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
							// TODO 暂不做处理
						}
						// 自定义菜单点击事件
						else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
							// 事件KEY值，与创建菜单时的key值对应
							//String eventKey = requestMap.get("EventKey");
							// 根据key值判断用户点击的按钮
							if(eventKey.equals("btn3")){
								
							}
						}
					}
					else if(eventKey.equals(MessageUtil.EVENT_TYPE_SCAN)){
						MessageUtil.EVENT_TYPE_SCAN.equals(scan);
						logger.info("key"+eventKey);
					}
					// 当用户发消息时
					else{
//						logger.info("获得的信息:"+textMessage2.getContent());
//						if ("你好".equals(textMessage2.getContent())||"您好".equals(textMessage2.getContent())||"在么".equals(textMessage2.getContent())) {
//							textMessage.setContent("请稍等，正在为您分配客服人员。。。");
//							respXml = MessageUtil.messageToXml(textMessage);	
//						}
						if (textMessage.getContent().equals("?")) {
							textMessage.setContent("欢迎关注秦皇岛人才库，为您提供7日内最新招聘及求职信息\n<a href = 'https://jinshuju.net/f/tl21JZ\'>点我进行招聘登记</a>\n<a href = 'http://shop13308654.ddkwxd.com/tag/231285\'>点我进入简历超市选择优秀人才</a>，我们每天从数以千计的求职者中为您筛选最新、最优质的求职信息，投放到这里，供您选择。\n<a href = 'https://jinshuju.net/f/j3iabB\'>点我进行求职登记</a>\n<a href = 'http://shop13308654.ddkwxd.com/tag/231300\'>点我进入招聘信息选择优秀企业</a>，我们每天从众多招聘企业中为您筛选最新、最最优质的求职信息，投放到这里，供您选择。\n<a href = 'http://zplsyx.iok.la/weixin3/JSP/tuiguang.jsp\'>推广加盟</a>不仅可以帮助您有需要的朋友快速找到优秀人才、满意工作，您还可以赚取收入。回复？可重复查看此重要信息");
							// 将消息对象转换成xml
							respXml = MessageUtil.messageToXml(textMessage);
						}
					}
				} catch (Exception e) {
					logger.error("wechat event handling", e);;
				}
				return respXml;
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
