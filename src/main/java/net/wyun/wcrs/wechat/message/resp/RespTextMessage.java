package net.wyun.wcrs.wechat.message.resp;

/**
 * 文本消息
 * 
 * @author qikuo
 * @date 2017-2-28
 */
public class RespTextMessage extends RespBaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
