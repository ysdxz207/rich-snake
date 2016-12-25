/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.Container;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.puyixiaowo.rsnake.model.Config;
import com.puyixiaowo.rsnake.util.DialogUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 下午5:43:23
 */
public class ScoreListDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScoreListDialog(Frame owner) {
		// 实例化一个JDialog类对象，指定对话框的父窗体，窗体标题，和类型
		super(owner, "游戏记录", true);
		Container container = getContentPane();// 创建一个容器

		String[] titles = { "分数", "难度", "时间" };
		JTable table = new JTable(Config.getScoreTableList(), titles);
		JScrollPane tablePane = new JScrollPane(table);
		container.add(tablePane);
		setSize(540, 460);
		DialogUtil.center(this, 540, 460);
	}

}
