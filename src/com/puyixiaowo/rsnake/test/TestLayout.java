/**
 * 
 */
package com.puyixiaowo.rsnake.test;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author weishaoqiang
 * @date 2016年12月18日 下午11:51:59
 */
public class TestLayout extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) {
		TestLayout test = new TestLayout();
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口退出虚拟机
		test.setSize(500, 500);
		
		test.setLayout(new GridBagLayout());
		
		
		//panel
		JPanel panel = new JPanel();
		panel.setSize(300, 500);
		panel.setBackground(new Color(0,0,0));
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0;
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.fill = GridBagConstraints.NONE;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.ipadx = 300;
		gridBagConstraints.ipady = 500;
		
		test.add(panel, gridBagConstraints);
		
		test.pack();
		test.setVisible(true);
	}
}
