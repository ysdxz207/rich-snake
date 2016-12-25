/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import javax.swing.JDialog;

import com.puyixiaowo.rsnake.model.Screen;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 下午8:51:58
 */
public class DialogUtil {
	/**
	 * 对话框居中
	 */
	public static void center(JDialog dialog, int width, int height) {
		Screen screen = new Screen();
		int x = (screen.getWidth() - width) / 2;
		int y = (screen.getHeight() - height) / 2;
		dialog.setLocation(x, y);
	}
}
