
package net.wyun.wcrs.jsj;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serial_number",
    "field_1",
    "field_2",
    "x_field_weixin_nickname",
    "x_field_weixin_gender",
    "x_field_weixin_country",
    "x_field_weixin_province_city",
    "x_field_weixin_openid",
    "x_field_weixin_headimgurl",
    "creator_name",
    "created_at",
    "updated_at",
    "info_remote_ip"
})
public class Entry {

    @JsonProperty("serial_number")
    private Integer serialNumber;
    @JsonProperty("field_1")
    private String field1;
    @JsonProperty("field_2")
    private String field2;
    @JsonProperty("x_field_weixin_nickname")
    private String xFieldWeixinNickname;
    @JsonProperty("x_field_weixin_gender")
    private String xFieldWeixinGender;
    @JsonProperty("x_field_weixin_country")
    private String xFieldWeixinCountry;
    @JsonProperty("x_field_weixin_province_city")
    private XFieldWeixinProvinceCity xFieldWeixinProvinceCity;
    @JsonProperty("x_field_weixin_openid")
    private String xFieldWeixinOpenid;
    @JsonProperty("x_field_weixin_headimgurl")
    private String xFieldWeixinHeadimgurl;
    @JsonProperty("creator_name")
    private String creatorName;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("info_remote_ip")
    private String infoRemoteIp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serial_number")
    public Integer getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serial_number")
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("field_1")
    public String getField1() {
        return field1;
    }

    @JsonProperty("field_1")
    public void setField1(String field1) {
        this.field1 = field1;
    }

    @JsonProperty("field_2")
    public String getField2() {
        return field2;
    }

    @JsonProperty("field_2")
    public void setField2(String field2) {
        this.field2 = field2;
    }

    @JsonProperty("x_field_weixin_nickname")
    public String getXFieldWeixinNickname() {
        return xFieldWeixinNickname;
    }

    @JsonProperty("x_field_weixin_nickname")
    public void setXFieldWeixinNickname(String xFieldWeixinNickname) {
        this.xFieldWeixinNickname = xFieldWeixinNickname;
    }

    @JsonProperty("x_field_weixin_gender")
    public String getXFieldWeixinGender() {
        return xFieldWeixinGender;
    }

    @JsonProperty("x_field_weixin_gender")
    public void setXFieldWeixinGender(String xFieldWeixinGender) {
        this.xFieldWeixinGender = xFieldWeixinGender;
    }

    @JsonProperty("x_field_weixin_country")
    public String getXFieldWeixinCountry() {
        return xFieldWeixinCountry;
    }

    @JsonProperty("x_field_weixin_country")
    public void setXFieldWeixinCountry(String xFieldWeixinCountry) {
        this.xFieldWeixinCountry = xFieldWeixinCountry;
    }

    @JsonProperty("x_field_weixin_province_city")
    public XFieldWeixinProvinceCity getXFieldWeixinProvinceCity() {
        return xFieldWeixinProvinceCity;
    }

    @JsonProperty("x_field_weixin_province_city")
    public void setXFieldWeixinProvinceCity(XFieldWeixinProvinceCity xFieldWeixinProvinceCity) {
        this.xFieldWeixinProvinceCity = xFieldWeixinProvinceCity;
    }

    @JsonProperty("x_field_weixin_openid")
    public String getXFieldWeixinOpenid() {
        return xFieldWeixinOpenid;
    }

    @JsonProperty("x_field_weixin_openid")
    public void setXFieldWeixinOpenid(String xFieldWeixinOpenid) {
        this.xFieldWeixinOpenid = xFieldWeixinOpenid;
    }

    @JsonProperty("x_field_weixin_headimgurl")
    public String getXFieldWeixinHeadimgurl() {
        return xFieldWeixinHeadimgurl;
    }

    @JsonProperty("x_field_weixin_headimgurl")
    public void setXFieldWeixinHeadimgurl(String xFieldWeixinHeadimgurl) {
        this.xFieldWeixinHeadimgurl = xFieldWeixinHeadimgurl;
    }

    @JsonProperty("creator_name")
    public String getCreatorName() {
        return creatorName;
    }

    @JsonProperty("creator_name")
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("info_remote_ip")
    public String getInfoRemoteIp() {
        return infoRemoteIp;
    }

    @JsonProperty("info_remote_ip")
    public void setInfoRemoteIp(String infoRemoteIp) {
        this.infoRemoteIp = infoRemoteIp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
