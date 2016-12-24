/**
 * 
 */
package com.puyixiaowo.rsnake.model;

/**
 * @author weishaoqiang
 * @date 2016年12月24日 下午12:58:21
 */
public class Score {
	private int userId;
	private String username;
	private int sc;// 分数

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSc() {
		return sc;
	}

	public void setSc(int sc) {
		this.sc = sc;
	}

}
