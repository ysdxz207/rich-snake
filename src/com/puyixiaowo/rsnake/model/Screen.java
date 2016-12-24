/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 屏幕对象
 * @author weishaoqiang
 * @date 2016年12月11日 上午10:22:46
 */
public class Screen {

	private int width;
	private int height;
	
	
	

	/**
	 * 
	 */
	public Screen() {
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		this.width = (int)screensize.getWidth();//屏幕宽度
		this.height = (int)screensize.getHeight();//屏幕高度
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
