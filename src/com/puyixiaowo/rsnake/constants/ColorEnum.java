/**
 * 
 */
package com.puyixiaowo.rsnake.constants;

import java.awt.Color;

import com.puyixiaowo.rsnake.util.ColorUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月12日 下午10:34:26
 */
public enum ColorEnum {
	COLOR_BG("#a8f9e7"),
	COLOR_SNAKE("#f37b1d"),
	COLOR_APPLE("#da0c01");
	
	ColorEnum(String color){
		this.color = color;
	}
	String color;
	
	/**
	 * 十六进制转为Color
	 * @return
	 */
	public Color toColor(){
		return ColorUtil.string2Color(this.color);
	}
}
