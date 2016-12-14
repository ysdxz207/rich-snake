package com.puyixiaowo.rsnake.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainFrame extends JFrame {
	private KeyboardPanel keyboardPanel = new KeyboardPanel();

	public mainFrame() {
		add(keyboardPanel);
		keyboardPanel.setFocusable(true);

	}

	public static void main(String[] args) {
		mainFrame frame = new mainFrame();
		frame.setTitle("mainFrame");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	static class KeyboardPanel extends JPanel implements KeyListener {
		private int x = 100;
		private int y = 100;
		private char keyChar = 'A';

		public KeyboardPanel() {
			setBackground(Color.cyan);
			addKeyListener(this);
			/*
			 * addKeyListener(new KeyAdapter(){ public void keyPressed(KeyEvent
			 * e){ switch (e.getKeyCode()){ case KeyEvent.VK_DOWN:
			 * 
			 * y+=10; break; case KeyEvent.VK_UP: y-=10; break; case
			 * KeyEvent.VK_LEFT: x-=10; break; case KeyEvent.VK_RIGHT: x+=10;
			 * break; default: keyChar=e.getKeyChar(); } //repaint(); }
			 * 
			 * 
			 * });
			 */
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString(String.valueOf(keyChar), x, y);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:

				y += 10;
				break;
			case KeyEvent.VK_UP:
				y -= 10;
				break;
			case KeyEvent.VK_LEFT:
				x -= 10;
				break;
			case KeyEvent.VK_RIGHT:
				x += 10;
				break;
			default:
				keyChar = e.getKeyChar();
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
}