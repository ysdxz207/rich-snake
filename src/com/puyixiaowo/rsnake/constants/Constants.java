/**
 * 
 */
package com.puyixiaowo.rsnake.constants;

import javax.swing.JPanel;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 下午2:01:05
 */
public class Constants {
	
	public static final int SNAKE_LENGTH = 3;
	/**
	 * 默认蛇移动时间间隔
	 */
	public static final int SNAKE_MOVE_INTERVAL_DEFAULT = 100;
	/**
	 * 游戏区域蛇方块份数
	 */
	public static int BLOCK_NUM = 64;
	/**
	 * 蛇出生边界，距离游戏边界两个格子
	 */
	public static int BOUNDS_BLOCK_NUM = 2;
	
	/**
	 * 游戏画布
	 */
	public static JPanel panel;
	/**
	 * 方块大小
	 */
	public static int BLOCK_SIZE;
}
