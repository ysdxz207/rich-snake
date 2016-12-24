/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.model.Config;
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

		int x = (screen.getWidth() - Constants.WIDTH_PANEL_GAME) / 2;
		int y = 0;
		frame.setLocation(x, y);
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
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));

		// 计分板
		JPanel panelScore = new JPanel();
		panelScore.setSize(100, 40);
		JLabel labelScoreText = new JLabel();
		labelScoreText.setText("分数：");
		labelScoreText.setFont(new Font("宋体", Font.BOLD, 22));
		JLabel labelScore = new JLabel();
		labelScore.setText(0 + "");
		labelScore.setFont(new Font("宋体", Font.BOLD, 20));
		labelScore.setName(Constants.NAME_LABEL_SCORE);

		panelScore.add(labelScoreText);
		panelScore.add(labelScore);
		panelMain.add(panelScore, BorderLayout.PAGE_START);

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
		panelGame.setName(Constants.NAME_PANEL_GAME);
		int subtraction = Constants.WIDTH_PANEL_GAME % 64;

		System.out.println("减数=" + subtraction);

		int width = Constants.WIDTH_PANEL_GAME - subtraction;
		int height = Constants.HEIGHT_PANEL_GAME - subtraction;

		System.out.println("游戏区域：" + width + "x" + height);

		panelGame.setBorder(new EmptyBorder(width / 2, width / 2, width / 2, width / 2));
		panelGame.setSize(width, height);
		panelMain.add(panelGame, BorderLayout.PAGE_END);
	}

	/**
	 * 
	 */
	private void initUI() {
		Config.initConf();

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
			// 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);//关闭设置按钮
		} catch (Exception e) {

		}

		new MainDialog();

	}
}
