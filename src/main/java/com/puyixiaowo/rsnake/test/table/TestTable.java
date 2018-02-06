package com.puyixiaowo.rsnake.test.table;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.puyixiaowo.rsnake.model.Config;

public class TestTable {
	static JFrame jf = new JFrame();

	public static void main(String[] args) {
		Config.initConf();

		String[] titles = { "分数", "时间" };
		JTable table = new JTable(Config.getScoreTableList(), titles);
		JScrollPane tablePane = new JScrollPane(table);
		jf.add(tablePane);
		jf.setTitle("游戏记录");
		jf.setBounds(300, 300, 300, 300);
		jf.setVisible(true);

	}

}