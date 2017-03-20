package net.wyun.wcrs.wechat.message.event;

/**
 * 自定义菜单事件
 * 
 * @author qikuo
 * @date 2017-3-1
 */
public class QRCodeEvent extends BaseEvent {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String sceneId) {
		EventKey = sceneId;
	}
}
