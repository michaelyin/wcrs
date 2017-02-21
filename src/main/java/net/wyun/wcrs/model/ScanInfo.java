package net.wyun.wcrs.model;

public class ScanInfo {

    private long publicId; //gong zhong hao ID
    private long userId;
    private String content;
    
    public ScanInfo(){}

    public ScanInfo(long id, long uid, String content) {
        this.publicId = id;
        this.content = content;
        this.userId = uid;
    }

	public long getPublicId() {
		return publicId;
	}

	public void setPublicId(long publicId) {
		this.publicId = publicId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
