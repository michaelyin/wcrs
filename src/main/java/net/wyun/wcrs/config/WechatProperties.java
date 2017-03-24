/**
 * 
 */
package net.wyun.wcrs.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author michael
 *
 */
@Component
@ConfigurationProperties("wcrs")
public class WechatProperties {
	
	@Value("${wcrs.wechat.appid}")
	private String appId;

	@Value("${wcrs.wechat.appsecret}")
	private String appSecret;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

		

	
}
