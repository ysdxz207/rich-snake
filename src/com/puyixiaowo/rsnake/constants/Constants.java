/**
 * 
 */
package com.puyixiaowo.rsnake.constants;

import javax.swing.JPanel;

import com.alibaba.fastjson.JSONObject;
import com.puyixiaowo.rsnake.enums.Level;
import com.puyixiaowo.rsnake.model.Block;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 下午2:01:05
 */
public class Constants {
	/**
	 * 窗体宽高
	 */
//	public static final int WIDTH_FRAME = 820;
//	public static final int HEIGHT_FRAME = 640;
	/**
	 * 游戏区域宽高
	 */
	public static final int WIDTH_PANEL_GAME = 616;
	public static final int HEIGHT_PANEL_GAME = 616;
	
	
	public static final int SNAKE_LENGTH = 3;
	/**
	 * 游戏区域名称
	 */
	public static final String NAME_PANEL_GAME = "panelGame";
	/**
	 * 游戏配置文件名称
	 */
	public static final String CONFIG_FILE_NAME = "conf.rs";
	/**
	 * 字符编码
	 */
	public static final String ENCODE = "UTF-8";
	/**
	 * 吃一个苹果得4分
	 */
	public static final int PER_SCORE = 4;
	/**
	 * 计分板textfield名称
	 */
	public static final String NAME_TEXT_SCORE = "labelScore";
	/**
	 * 游戏配置
	 */
	public static JSONObject CONFIG = null;
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
	/**
	 * des加密key，需要是8位
	 */
	public static String DES_KEY = "hupubao0";
	/**
	 * 游戏难度，默认普通
	 */
	public static Level level = Level.NORMAL;
}
