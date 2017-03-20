package net.wyun.wcrs.wechat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.wyun.wcrs.wechat.po.SNSUserInfo;
import net.wyun.wcrs.wechat.po.WeixinOauth2Token;
import net.wyun.wcrs.wechat.po.WeixinQRCode;
import net.wyun.wcrs.wechat.po.WeixinUserInfo;
import net.wyun.wcrs.wechat.po.WeixinUserList;

/**
 * �߼��ӿڹ�����
 * 
 * @author qikuo
 * @date 2017-2-28
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);
	/**
	 * ��װ�ı��ͷ���Ϣ
	 * 
	 * @param openId ��Ϣ���Ͷ���
	 * @param content �ı���Ϣ����
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// ����Ϣ�����е�˫���Ž���ת��
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		log.info("��Ϣ���ݣ�{}", jsonMsg);
		boolean result = false;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ���Ϳͷ���Ϣ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("�ͷ���Ϣ���ͳɹ� errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("�ͷ���Ϣ����ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}
	/**
	 * ������ʱ���ζ�ά��
	 * 
	 * @param accessToken �ӿڷ���ƾ֤
	 * @param expireSeconds ��ά����Чʱ�䣬��λΪ�룬��󲻳���1800
	 * @param sceneId ����ID
	 * @return WeixinQRCode
	 */
	public static WeixinQRCode createTemporaryQRCode(String accessToken, int expireSeconds, int sceneId) {
		WeixinQRCode weixinQRCode = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ��Ҫ�ύ��json����
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// ������ʱ���ζ�ά��
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
				//log.info("������ʱ���ζ�ά��ɹ� ticket:{} expire_seconds:{}", weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
			} catch (Exception e) {
				weixinQRCode = null;
//				int errorCode = jsonObject.getInt("errcode");
//				String errorMsg = jsonObject.getString("errmsg");
				//log.error("������ʱ���ζ�ά��ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinQRCode;
	}

	/**
	 * �������ô��ζ�ά��
	 * 
	 * @param accessToken �ӿڷ���ƾ֤
	 * @param sceneId ����ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId) {
		String ticket = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ��Ҫ�ύ��json����
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// �������ô��ζ�ά��
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));

		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
		
				log.info("�������ô��ζ�ά��ɹ� ticket:{}", ticket);
				
				
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("�������ô��ζ�ά��ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return ticket;
	}

	/**
	 * ����ticket��ȡ��ά��
	 * 
	 * @param ticket ��ά��ticket
	 * @param savePath ����·��
	 */
	public static String getQRCode(String ticket, String savePath) {
		String filePath = null;
		// ƴ�������ַ
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET", CommonUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// ��ticket��Ϊ�ļ���
			filePath = savePath + ticket + ".jpg";

			// ��΢�ŷ��������ص�������д���ļ�
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			log.info("����ticket��ȡ��ά��ɹ���filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			log.error("����ticket��ȡ��ά��ʧ�ܣ�{}", e);
		}
		return filePath;
	}
	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @param accessToken �ӿڷ���ƾ֤
	 * @param openId �û���ʶ
	 * @return WeixinUserInfo
	 */
	public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
		WeixinUserInfo weixinUserInfo = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// ��ȡ�û���Ϣ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				// �û��ı�ʶ
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// ��ע״̬��1�ǹ�ע��0��δ��ע����δ��עʱ��ȡ����������Ϣ
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// �û���עʱ��
				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				// �ǳ�
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// �û����Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// �û����ڹ���
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// �û�����ʡ��
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// �û����ڳ���
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// �û������ԣ���������Ϊzh_CN
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				// �û�ͷ��
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("�û�{}��ȡ����ע", weixinUserInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("��ȡ�û���Ϣʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}

	/**
	 * ��ȡ��ע���б�
	 * 
	 * @param accessToken ���ýӿ�ƾ֤
	 * @param nextOpenId ��һ����ȡ��openId������Ĭ�ϴ�ͷ��ʼ��ȡ
	 * @return WeixinUserList
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static WeixinUserList getUserList(String accessToken, String nextOpenId) {
		WeixinUserList weixinUserList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
		// ��ȡ��ע���б�
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("��ȡ��ע���б�ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinUserList;
	}
	//��ӿͷ��˺�
	public static boolean setCustomerid(String accessToken, String kf_account,String nickname,String password) {
		boolean result = false;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
		String jsonMsg = "{\"kf_account\":\"%s\",\"nickname\":\"%s\",\"password\":\"%s\"}";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ���Ϳͷ���Ϣ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, kf_account,nickname,password));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("�ͷ���Ϣ�����ɹ� errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("�ͷ���Ϣ����ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}
	/**
	 * ��ȡ��ҳ��Ȩƾ֤
	 * 
	 * @param appId �����˺ŵ�Ψһ��ʶ
	 * @param appSecret �����˺ŵ���Կ
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// ��ȡ��ҳ��Ȩƾ֤
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("��ȡ��ҳ��Ȩƾ֤ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}

	/**
	 * ˢ����ҳ��Ȩƾ֤
	 * 
	 * @param appId �����˺ŵ�Ψһ��ʶ
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId, String refreshToken) {
		WeixinOauth2Token wat = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// ˢ����ҳ��Ȩƾ֤
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("ˢ����ҳ��Ȩƾ֤ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}
	/**
	 * ͨ����ҳ��Ȩ��ȡ�û���Ϣ
	 * 
	 * @param accessToken ��ҳ��Ȩ�ӿڵ���ƾ֤
	 * @param openId �û���ʶ
	 * @return SNSUserInfo
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// ͨ����ҳ��Ȩ��ȡ�û���Ϣ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// �û��ı�ʶ
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// �ǳ�
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// �Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// �û����ڹ���
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// �û�����ʡ��
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// �û����ڳ���
				snsUserInfo.setCity(jsonObject.getString("city"));
				// �û�ͷ��
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// �û���Ȩ��Ϣ
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("��ȡ�û���Ϣʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String APPID = CommonUtil.APPID;
		String APPSECRET = CommonUtil.APPSECRET;
		String accessToken = CommonUtil.getToken("APPID", "APPSECRET").getAccessToken();
		WeixinUserList weixinUserList = getUserList(accessToken, "");
		WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
		
		String openid = weixinUserList.getNextOpenId();

//		QRCodeEvent qrCodeEvent = new QRCodeEvent();
//		qrCodeEvent.getEventKey();
		System.out.println("openid:"+openid);
		System.out.println("APPID:"+APPID);
		System.out.println("APPSECRET:"+APPSECRET);
		System.out.println("accessToken:"+accessToken);
		WeixinUserInfo user = AdvancedUtil.getUserInfo(accessToken, openid);
//		/**
//		 * ��ȡ��ע���б�
//		 */
//		System.out.println("�ܹ�ע�û�����" + weixinUserList.getTotal());
//		System.out.println("���λ�ȡ�û�����" + weixinUserList.getCount());
//		System.out.println("OpenID�б�" + weixinUserList.getOpenIdList().toString());
		System.out.println("next_openid��" + weixinUserList.getNextOpenId());
//		
//		/**
//		 * ��ȡ�û���Ϣ
//		 */
		System.out.println("OpenID��" + user.getOpenId());
//		System.out.println("��ע״̬��" + user.getSubscribe());
//		System.out.println("��עʱ�䣺" + user.getSubscribeTime());
//		System.out.println("�ǳƣ�" + user.getNickname());
//		System.out.println("�Ա�" + user.getSex());
//		System.out.println("���ң�" + user.getCountry());
//		System.out.println("ʡ�ݣ�" + user.getProvince());
//		System.out.println("���У�" + user.getCity());
//		System.out.println("���ԣ�" + user.getLanguage());
//		System.out.println("ͷ��" + user.getHeadImgUrl());
		
		// ��ȡ�ӿڷ���ƾ֤
		/**
		* ���Ϳͷ���Ϣ���ı���Ϣ��
		*/
//		// ��װ�ı��ͷ���Ϣ
//		Scanner scanner = new Scanner(System.in);
//		
//		String jsonTextMsg = makeTextCustomMessage("ovNlUwMpA79l2-uDu2MtDuhGr9co", scanner.next());
//		// ���Ϳͷ���Ϣ
//		sendCustomMessage(accessToken, jsonTextMsg);
		/**
		 * ������ʱ��ά��
		 */
//		WeixinCustomer weixinCustomer = new WeixinCustomer();
//		AdvancedUtil.setCustomerid(accessToken, "zhou504171@gh_d698ab97cae6", "С��", "sssssmd5");
//		System.out.println("�ͷ�������"+weixinCustomer.getKf_account());
		
//		WeixinQRCode weixinQRCode = createTemporaryQRCode(accessToken, 900, 111111);
//		// ��ʱ��ά���ticket
//		System.out.println("ticket:"+weixinQRCode.getTicket());
//		// ��ʱ��ά�����Чʱ��
//		System.out.println("expireSeconds:"+weixinQRCode.getExpireSeconds());
		//String Customer1 = AdvancedUtil.setweixincustomer(accessToken, aaa@gh_d698ab97cae6, aaa, aa);
//		/**
//		 * ����ticket��ȡ��ά��
//		 */
//		String ticket = weixinQRCode.getTicket();
//		String savePath = "C:/Users/Administrator/Desktop";
//		// ����ticket��ȡ��ά��
//		getQRCode(ticket, savePath);
		/**
		 * �������ö�ά��**/
		
		String ticket = AdvancedUtil.createPermanentQRCode(accessToken,1);
		System.out.println("ticket:"+ticket);
//		System.out.println("sceneId"+);
		String savePath = "C:/Users/Administrator/Desktop";
		getQRCode(ticket, savePath);

		
		/**
		 * �����ͷ�*/
//		WeixinCustomer weixinCustomer = AdvancedUtil.setweixincustomer(accessToken, "zhou50@gh_d698ab97cae6", "С��", "dddd");
//		System.out.println("getKf_account"+weixinCustomer.getKf_account());
	}
}
