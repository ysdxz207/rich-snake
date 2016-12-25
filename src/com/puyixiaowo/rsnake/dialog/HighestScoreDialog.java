/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.model.Game;
import com.puyixiaowo.rsnake.util.DialogUtil;

/**
 * 最高分
 * 
 * @author weishaoqiang
 * @date 2016年12月25日 上午12:42:36
 */
public class HighestScoreDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param owner
	 */
	public HighestScoreDialog(Frame owner) {
		// 实例化一个JDialog类对象，指定对话框的父窗体，窗体标题，和类型
		super(owner, "最高分", true);
		Container container = getContentPane();// 创建一个容器
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("最高分：");
		JLabel score = new JLabel();
		label.setFont(new Font("宋体", Font.BOLD, 28));
		score.setFont(new Font("宋体", Font.BOLD, 28));
		score.setText(Game.getCurrentUserHighestScore());
		
		panel.add(label);
		panel.add(score);
		panel.setBackground(ColorEnum.COLOR_BG.toColor());
		container.add(panel, BorderLayout.PAGE_END);
		setSize(400, 260);
		DialogUtil.center(this, 400, 260);
	}

}
