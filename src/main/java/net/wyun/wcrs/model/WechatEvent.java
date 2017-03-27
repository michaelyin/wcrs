/**
 * 
 */
package net.wyun.wcrs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Xuecheng
 *
 */
@Entity
@Table(name = "wc_event")
public class WechatEvent {
	
	public WechatEvent(String toUserName, String fromUserName, String msgType, String event, String eventKey) {
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.msgType = msgType;
		this.event = event;
		this.eventKey = trimEventKey(eventKey);;
		this.create_t = new Date();
	}

	public WechatEvent(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; 

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "to_user_name")
    private String toUserName; 
	
	@Column(name = "from_user_name")
    private String fromUserName; 
	
	@Column(name = "msg_type")
    private String msgType; 
	
	@Column(name = "create_t")
    private Date create_t; 
	
    private String event;
    
    @Column(name = "event_key", length=100)
    private String eventKey;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getCreate_t() {
		return create_t;
	}

	public void setCreate_t(Date create_t) {
		this.create_t = create_t;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}
	
	private String trimEventKey(String eventKey){
		try {
	        int size = getClass().getDeclaredField("eventKey").getAnnotation(Column.class).length();
	        int inLength = eventKey.length();
	        if (inLength>size)
	        {
	        	eventKey = eventKey.substring(0, size);
	        }
	    } catch (NoSuchFieldException ex) {
	    } catch (SecurityException ex) {
	    }
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		
		this.eventKey = trimEventKey(eventKey);;
	}
    
}
