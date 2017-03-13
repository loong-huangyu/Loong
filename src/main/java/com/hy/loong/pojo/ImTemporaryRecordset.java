package com.hy.loong.pojo;

import java.util.Date;

/**
 * 
 * @author Dell
 * 环信聊天临时会话表
 */
public class ImTemporaryRecordset {
	//编号
	private String imId;
	//发送人id
	private String fromId;
	//群组id
	private String groupId;
	//发送时间
	private Date sendTime;
	//发送内容
	private String content;
	
	public ImTemporaryRecordset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImTemporaryRecordset(String imId, String fromId, String groupId, Date sendTime, String content) {
		super();
		this.imId = imId;
		this.fromId = fromId;
		this.groupId = groupId;
		this.sendTime = sendTime;
		this.content = content;
	}
	
	public String getImId() {
		return imId;
	}
	public void setImId(String imId) {
		this.imId = imId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
