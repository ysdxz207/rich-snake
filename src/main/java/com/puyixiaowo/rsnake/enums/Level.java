/**
 * 
 */
package com.puyixiaowo.rsnake.enums;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 上午11:23:18
 */
public enum Level {
	EASY(300, "简单"),
	NORMAL(100, "一般"),
	HARD(40, "难"),
	CRAZY(10, "疯狂");
	
	
	
	/**
	 * @param name
	 * @param ordinal
	 */
	Level(int interval, String description) {
		this.setInterval(interval);
		this.description = description;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	private int interval;
	private String description;
	
}
