package net.wyun.wcrs.wechat.po;

/**
 * ��ʱ��ά����Ϣ
 * 
 * @author qikuo
 * @date 2017-2-28
 */
public class WeixinQRCode {
	// ��ȡ�Ķ�ά��ticket
	private String ticket;
	// ��ά�����Чʱ�䣬��λΪ�룬��󲻳���1800
	private int expireSeconds;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
