/**
 * 
 */
package com.puyixiaowo.rsnake.constants;

import javax.swing.JPanel;

import com.puyixiaowo.rsnake.model.Block;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 下午2:01:05
 */
public class Constants {
	/**
	 * 窗体宽高
	 */
	public static final int WIDTH_FRAME = 820;
	public static final int HEIGHT_FRAME = 640;
	/**
	 * 游戏区域宽高
	 */
	public static final int WIDTH_PANEL_GAME = 616;
	public static final int HEIGHT_PANEL_GAME = 616;
	
	
	public static final int SNAKE_LENGTH = 3;
	/**
	 * 默认蛇移动时间间隔
	 */
	public static final long SNAKE_MOVE_INTERVAL_DEFAULT = 100;
	/**
	 * 游戏区域名称
	 */
	public static final String NAME_PANEL_GAME = "panelGame";
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
	/**
	 * 苹果
	 */
	public static Block apple;
}
