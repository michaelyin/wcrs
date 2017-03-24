/**
 * 
 */
package net.wyun.wcrs.wechat;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.wyun.wcrs.wechat.po.WeixinUserInfo;
import net.wyun.wcrs.wechat.po.WeixinUserList;

/**
 * @author michael
 *
 */
public class AdvancedUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
			// TODO Auto-generated method stub
			String APPID = CommonUtil.APPID;
			String APPSECRET = CommonUtil.APPSECRET;
			String accessToken = CommonUtil.getToken("APPID", "APPSECRET").getAccessToken();
			WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken, "");
			WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
			
			String openid = weixinUserList.getNextOpenId();

//			QRCodeEvent qrCodeEvent = new QRCodeEvent();
//			qrCodeEvent.getEventKey();
			System.out.println("openid:"+openid);
			System.out.println("APPID:"+APPID);
			System.out.println("APPSECRET:"+APPSECRET);
			System.out.println("accessToken:"+accessToken);
			WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, openid);
//			/**
//			 * 获取关注者列表
//			 */
//			System.out.println("总关注用户数：" + weixinUserList.getTotal());
//			System.out.println("本次获取用户数：" + weixinUserList.getCount());
//			System.out.println("OpenID列表：" + weixinUserList.getOpenIdList().toString());
			System.out.println("next_openid：" + weixinUserList.getNextOpenId());
//			
//			/**
//			 * 获取用户信息
//			 */
			System.out.println("OpenID：" + user.getOpenId());
//			System.out.println("关注状态：" + user.getSubscribe());
//			System.out.println("关注时间：" + user.getSubscribeTime());
//			System.out.println("昵称：" + user.getNickname());
//			System.out.println("性别：" + user.getSex());
//			System.out.println("国家：" + user.getCountry());
//			System.out.println("省份：" + user.getProvince());
//			System.out.println("城市：" + user.getCity());
//			System.out.println("语言：" + user.getLanguage());
//			System.out.println("头像：" + user.getHeadImgUrl());
			
			// 获取接口访问凭证
			/**
			* 发送客服消息（文本消息）
			*/
//			// 组装文本客服消息
//			Scanner scanner = new Scanner(System.in);
//			
//			String jsonTextMsg = makeTextCustomMessage("ovNlUwMpA79l2-uDu2MtDuhGr9co", scanner.next());
//			// 发送客服消息
//			sendCustomMessage(accessToken, jsonTextMsg);
			/**
			 * 创建临时二维码
			 */
//			WeixinCustomer weixinCustomer = new WeixinCustomer();
//			AdvancedUtil.setCustomerid(accessToken, "zhou504171@gh_d698ab97cae6", "小周", "sssssmd5");
//			System.out.println("客服姓名："+weixinCustomer.getKf_account());
			
//			WeixinQRCode weixinQRCode = createTemporaryQRCode(accessToken, 900, 111111);
//			// 临时二维码的ticket
//			System.out.println("ticket:"+weixinQRCode.getTicket());
//			// 临时二维码的有效时间
//			System.out.println("expireSeconds:"+weixinQRCode.getExpireSeconds());
			//String Customer1 = AdvancedUtil.setweixincustomer(accessToken, aaa@gh_d698ab97cae6, aaa, aa);
//			/**
//			 * 根据ticket换取二维码
//			 */
//			String ticket = weixinQRCode.getTicket();
//			String savePath = "C:/Users/Administrator/Desktop";
//			// 根据ticket换取二维码
//			getQRCode(ticket, savePath);
			/**
			 * 创建永久二维码**/
			
			//String ticket = AdvancedUtil.createPermanentQRCode(accessToken,1);
			String ticket = "gQEA8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyeDRJdXhlaGlkQWgxMDAwME0wM0EAAgS5PdRYAwQAAAAA";
			//System.out.println("ticket:"+ticket);
//			System.out.println("sceneId"+);
			String savePath = "/home/michael/";
			AdvancedUtil.getQRCode(ticket, savePath);

			
			/**
			 * 创建客服*/
//			WeixinCustomer weixinCustomer = AdvancedUtil.setweixincustomer(accessToken, "zhou50@gh_d698ab97cae6", "小周", "dddd");
//			System.out.println("getKf_account"+weixinCustomer.getKf_account());
		
	}

}
