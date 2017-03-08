package net.wyun.wcrs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_info")
//@JsonIgnoreProperties({"id", "password", "factor", "sent_to_server", "enabled", "macAccounts"})
public class User {
	
	//字段	
	@Id
	String openID;
	
	@Column(name = "scene_id")
	Integer sceneID; //（1-100000）		
	Integer parent; //parent's scene_id
	
	@Column(name = "nick_name")
	String nickName;
	
	Gender gender; //sex		
	String city;		
	Province province;
	String country;
	
	@Column(name = "head_img_url", nullable = false)
	String headimgurl;
	
	@Column(name = "create_t", nullable = false)
    private Date createt; //        //subscribe_time, 
	
    private Date modify_t; //         datetime 
	//subscribe_time		
	String ticket;
	
	
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public Integer getSceneID() {
		return sceneID;
	}
	public void setSceneID(Integer sceneID) {
		this.sceneID = sceneID;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public Date getCreatet() {
		return createt;
	}
	public void setCreatet(Date createt) {
		this.createt = createt;
	}
	public Date getModify_t() {
		return modify_t;
	}
	public void setModify_t(Date modify_t) {
		this.modify_t = modify_t;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	

}
