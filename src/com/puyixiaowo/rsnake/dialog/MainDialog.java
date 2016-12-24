/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.model.Game;
import com.puyixiaowo.rsnake.model.Screen;

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
	private static JFrame frame;
	private static JPanel panelMain;
	private static JPanel panelGame;

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
		// int width = screen.getWidth() * 4 / 5;// 窗体宽度
		// int height = screen.getHeight() * 4 / 5;// 窗体高度

		int width = Constants.WIDTH_FRAME;
		int height = Constants.HEIGHT_FRAME;

		int x = (screen.getWidth() - width) / 2;
		int y = (screen.getHeight() - height) / 2;
		System.out.println("frame size:" + width + "x" + height);
		frame.setSize(width, height);
		frame.setBounds(x, y, width, height);
	}

	private void initFrame() {
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口退出虚拟机
		frame.setResizable(false);
		frame.setLayout(new GridLayout(1, 1));
		centerFrame(frame);
	}

	private void initPanelMain() {
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());

		GridBagConstraints gridAlert = new GridBagConstraints();
		gridAlert.gridx = 10;
		gridAlert.gridy = 0;
		gridAlert.gridwidth = 1;
		gridAlert.gridheight = 1;
		gridAlert.weightx = 0;
		gridAlert.weighty = 0;
		gridAlert.anchor = GridBagConstraints.NORTHWEST;
		gridAlert.fill = GridBagConstraints.NONE;
		gridAlert.insets = new Insets(0, 40, 0, 0);

		panelMain.setFont(new Font("宋体", Font.BOLD, 36));

		// 工具栏
		JMenuBar menuBarNewGame = new JMenuBar();
		frame.setJMenuBar(menuBarNewGame);

		JMenu menuNewGame = new JMenu("游戏");
		JMenuItem itemNewGame = new JMenuItem("新游戏");
		itemNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		menuNewGame.add(itemNewGame);
		menuBarNewGame.add(menuNewGame);

	}

	private void initPanelGame() {
		panelGame = new JPanel();
		panelGame.setBackground(ColorEnum.COLOR_BG.toColor());
		panelGame.setLayout(null);
		panelGame.setName(Constants.NAME_PANEL_GAME);

		int subtraction = Constants.WIDTH_PANEL_GAME % 64;

		System.out.println("减数=" + subtraction);

		int width = Constants.WIDTH_PANEL_GAME - subtraction;
		int height = Constants.HEIGHT_PANEL_GAME - subtraction;

		System.out.println("游戏区域：" + width + "x" + height);

		panelGame.setSize(width, height);

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
		gridBagConstraints.ipadx = width;
		gridBagConstraints.ipady = height;

		panelMain.add(panelGame, gridBagConstraints);
	}

	/**
	 * 
	 */
	private void initUI() {
		initFrame();// 初始化窗口

		initPanelMain();// 初始化画布

		initPanelGame();// 初始化游戏区域

		assemblyControl();// 组装控件

	}

	/**
	 * 
	 */
	private void assemblyControl() {
		frame.add(panelMain);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 初始化游戏
	 */
	private void newGame() {
		if (Game.isFirst()) {
			Game.newGame(panelGame);
		} else {
			Game.reStartGame();
		}
	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {

		}

		new MainDialog();

	}
}
