/**
 * 
 */
package com.puyixiaowo.rsnake.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * @author weishaoqiang
 * @date 2016年12月14日 下午9:38:35
 */
public class TestListener extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		TestListener test = new TestListener();
		test.setTitle("mainFrame");
		test.setSize(300, 300);
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		test.setVisible(true);
		test.setFocusable(true);
		test.requestFocus();
		test.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("按键按键");
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("松开");
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("按下");
			}
		});
		
		
		
	}
	
}
