/**
 * 
 */
package com.puyixiaowo.rsnake.main;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.puyixiaowo.rsnake.dialog.GameDialog;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 下午5:33:49
 */
public class Main {
	public static void main(String[] args) {
		try {
			// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);// 关闭设置按钮
		} catch (Exception e) {

		}

		new GameDialog();

	}
}
