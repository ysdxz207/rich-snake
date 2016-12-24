/**
 * 
 */
package com.puyixiaowo.rsnake.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author weishaoqiang
 * @date 2016年12月18日 下午11:51:59
 */
public class TestSize extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) {
		TestSize test = new TestSize();
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口退出虚拟机
		test.setSize(500, 500);
		
		test.setLayout(new GridLayout(1, 1));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		
		
		
		test.add(panel);
		
		test.setVisible(true);
		System.out.println(panel.getWidth() + "*" + panel.getHeight());
	}
}
