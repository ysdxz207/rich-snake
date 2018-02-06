/**
 * 
 */
package com.puyixiaowo.rsnake.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.enums.ImagesPath;
import com.puyixiaowo.rsnake.enums.Level;
import com.puyixiaowo.rsnake.model.Config;
import com.puyixiaowo.rsnake.model.Game;
import com.puyixiaowo.rsnake.model.Screen;
import com.puyixiaowo.rsnake.util.ResourceUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 上午10:07:02
 */
public class GameDialog extends JFrame {

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
	public GameDialog() throws HeadlessException {
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
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				// 保存分数
				Config.saveConf();
			}

		});
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
		// 分数显示
		JTextField textScore = new JTextField();

		textScore.setBackground(new Color(248, 243, 224));
		textScore.setBorder(null);
		textScore.setEditable(false);
		textScore.setColumns(30);
		textScore.setText(0 + "");
		textScore.setFont(new Font("宋体", Font.BOLD, 20));
		textScore.setName(Constants.NAME_TEXT_SCORE);

		panelScore.add(labelScoreText);
		panelScore.add(textScore);
		panelMain.add(panelScore, BorderLayout.PAGE_START);

		// 工具栏
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// 游戏
		JMenu menuNewGame = new JMenu("游戏");
		JMenuItem itemNewGame = new JMenuItem("新游戏");
		itemNewGame.addActionListener(e -> newGame());

		JMenu menuLevel = new JMenu("选择难度");
		// 选中样式icon
		ImageIcon iconSelected = new ImageIcon(ResourceUtil.getImage(ImagesPath.MENU_ITEM_SELECTED.getFileName()));
		for (Level level : Level.values()) {
			JMenuItem item = new JMenuItem(level.getDescription());
			if (level.name().equals("NORMAL")) {
				item.setIcon(iconSelected);
			}
			item.setName(level.name());
			item.addActionListener(e -> {
                // 选择难度
                item.setSelected(true);
                // 清空其他难度选项选中样式
                for (int i = 0; i < menuLevel.getItemCount(); i++) {
                    JMenuItem it = menuLevel.getItem(i);
                    it.setIcon(null);
                }
                // 设置当前难度选项选中样式
                item.setIcon(iconSelected);

                for (Level level1 : Level.values()) {
                    if (level1.name().equals(item.getName())) {
                        Constants.level = level1;
                    }
                }
            });
			menuLevel.add(item);
		}

		menuNewGame.add(itemNewGame);
		menuNewGame.add(menuLevel);
		// 分数
		JMenu menuScore = new JMenu("分数");
		JMenuItem itemScoreHighest = new JMenuItem("最高分");
		JMenuItem itemScoreList = new JMenuItem("游戏记录");
		itemScoreHighest.addActionListener(e -> showHighestScore());
		itemScoreList.addActionListener(e -> {
            new ScoreListDialog(frame).setVisible(true);
        });
		menuScore.add(itemScoreHighest);
		menuScore.add(itemScoreList);

		menuBar.add(menuNewGame);
		menuBar.add(menuScore);
	}

	/**
	 * 显示最高分
	 */
	public static void showHighestScore() {
		new HighestScoreDialog(frame).setVisible(true);
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

		//新游戏暂停
		Game.pauseOrContinueGame();
	}

}
