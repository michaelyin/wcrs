package net.wyun.wcrs.wechat.po;

/**
 * ��ҳ��Ȩ��Ϣ
 * 
 * @author qikuo
 * @date 2017-2-28
 */
public class WeixinOauth2Token {
	// ��ҳ��Ȩ�ӿڵ���ƾ֤
	private String accessToken;
	// ƾ֤��Чʱ��
	private int expiresIn;
	// ����ˢ��ƾ֤
	private String refreshToken;
	// �û���ʶ
	private String openId;
	// �û���Ȩ������
	private String scope;

	private String scene_id;
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	
}
