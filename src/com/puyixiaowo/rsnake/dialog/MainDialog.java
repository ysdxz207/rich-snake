/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.enums.DirectionEnum;
import com.puyixiaowo.rsnake.model.Screen;
import com.puyixiaowo.rsnake.model.Snake;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 上午10:07:02
 */
public class MainDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "贪吃蛇";
	private static final Screen screen = new Screen();

	/**
	 * @throws HeadlessException
	 */
	public MainDialog() throws HeadlessException {
		initUI();
	}

	/**
	 * 窗体置为居中
	 * 
	 * @param frame
	 */
	private void centerFrame(JFrame frame) {
		int width = screen.getWidth() * 4 / 5;// 窗体宽度
		int height = screen.getHeight() * 4 / 5;// 窗体高度

		int x = (screen.getWidth() - width) / 2;
		int y = (screen.getHeight() - height) / 2;

		frame.setSize(width, height);
		frame.setBounds(x, y, width, height);
	}

	/**
	 * 
	 */
	private void initUI() {
		JFrame frame = new JFrame(TITLE);
		JPanel panelMain = new JPanel();
		JPanel panel = new JPanel();
		centerFrame(frame);
		frame.addWindowListener(new WindowAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.
			 * WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}

		});

		Container contentPane = frame.getContentPane();
		frame.setVisible(true);
		// 注意只有窗口显示后getLocationOnScreen才可以调用，否则出错
		// Point contentPos = contentPane.getLocationOnScreen();// 在屏幕的坐标
		Dimension size = contentPane.getSize(); // 可视区域的大小

		panelMain.setSize(size);

		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setLayout(new GridBagLayout());
		int subtraction = panelMain.getHeight() % 64;
		int widthPanel = panelMain.getHeight() - subtraction;
		System.out.println("减数：" + subtraction + ",游戏画布长宽：" + widthPanel);

		panel.setSize(widthPanel, widthPanel);
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
		gridBagConstraints.ipadx = widthPanel;
		gridBagConstraints.ipady = widthPanel;

		panel.setBackground(ColorEnum.COLOR_BG.toColor());
		panel.setLayout(null);
		panelMain.add(panel, gridBagConstraints);

		frame.add(panelMain);
		initSnake(frame, panel, panelMain);
		frame.setFocusable(true);// 不设置则无法触发listener
		frame.requestFocus();
		setFocusTraversalKeysEnabled(false);
	}

	private void initSnake(JFrame frame, JPanel panel, JPanel panelMain) {
		Constants.BLOCK_SIZE = panel.getWidth() / Constants.BLOCK_NUM;// 设置方块大小
		// ///
		Snake snake = new Snake(panel);
		snake.move();
		// 监控玩家操控方向
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					snake.setDirection(DirectionEnum.UP.code);
					System.out.println("上");
					break;
				case KeyEvent.VK_DOWN:
					snake.setDirection(DirectionEnum.DOWN.code);
					System.out.println("下");
					break;
				case KeyEvent.VK_LEFT:
					snake.setDirection(DirectionEnum.LEFT.code);
					System.out.println("左");
					break;
				case KeyEvent.VK_RIGHT:
					snake.setDirection(DirectionEnum.RIGHT.code);
					System.out.println("右");
					break;

				default:
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {

		}

		MainDialog mainDialog = new MainDialog();

	}
}
