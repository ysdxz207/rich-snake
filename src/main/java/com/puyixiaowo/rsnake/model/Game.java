/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.enums.GameState;
import com.puyixiaowo.rsnake.enums.Level;
import com.puyixiaowo.rsnake.event.GameEvent;
import com.puyixiaowo.rsnake.listener.GameListener;
import com.puyixiaowo.rsnake.listener.PauseOrContinueListener;
import com.puyixiaowo.rsnake.util.BlockUtil;
import com.puyixiaowo.rsnake.util.ComponentUtil;
import com.puyixiaowo.rsnake.util.MapUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月18日 下午10:53:50
 */
public class Game {

	// 使用volatile关键字保其可见性
	volatile private static Game instance = null;

	public static int score = 0;
	public static Level level;

	private Collection<GameListener> listeners;

	public static Game getInstance() {
		// if (instance == null) {// 懒汉式
		// System.err.println("无Game实例");
		// }
		return instance;
	}

	public static Game getInstance(JPanel panel) {
		try {
			if (instance != null) {// 懒汉式

			} else {
				// 创建实例之前可能会有一些准备性的耗时工作
				Thread.sleep(300);
				synchronized (Game.class) {
					if (instance == null) {// 二次检查
						instance = new Game(panel);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instance.addGameListener(new GameListener());
		return instance;
	}

	/**
	 * @param panelGame
	 * 
	 */
	private Game(JPanel panelGame) {
		
	}

	/**
	 * 
	 */
	public static void newGame(JPanel panelGame) {
		Game.level = Constants.level;//设置游戏难度
		Snake snake = null;
		if (Game.isFirst()) {
			// panelGame == null
			// 监控玩家操控方向
			panelGame.addKeyListener(new PauseOrContinueListener());
		} else {
			// panelGame != null
			setScore(0);// 设置分数为0
			snake = Snake.getInstance();
			panelGame = snake.getPanel();
		}

		panelGame.removeAll();// 清空游戏区域
		panelGame.repaint();
		initSnake(panelGame);
		initGame(panelGame);
		Game.getInstance().fireGameRuning();// 触发游戏运行中
		Game.run();
	}

	/**
	 * 设置分数
	 * 
	 * @param sc
	 */
	public static void setScore(int sc) {
		Game.score = sc;
		JTextField label = (JTextField) ComponentUtil.getComponentByName(Constants.NAME_TEXT_SCORE);
		label.setText(Game.score + "");
	}

	/**
	 * 运行游戏
	 */
	private static void run() {
		Snake.thread.start();
	}

	public static void reStartGame() {

		try {
			Game.stopGame();// 停止游戏
			newGame(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stopGame() {
		Snake.thread.stopThread();
		// 添加分数
		Config.addScore(Game.score);
		Game.getInstance().fireGameStop();// 触发游戏停止
	}

	public static void pauseGame() {
		Snake.thread.pauseThread();
		Game.getInstance().fireGamePause();// 触发游戏暂停
	}

	public static void continueGame() {
		Snake.thread.continueThread();
		Game.getInstance().fireGameContinue();// 触发游戏继续
	}

	public static void gameOver() {
		Snake.thread.stopThread();
		// 添加分数
		Config.addScore(Game.score);
		Game.getInstance().fireGameOver();// 触发游戏结束
		System.out.println("游戏结束！");
	}

	/**
	 * 
	 */
	public static void pauseOrContinueGame() {
		if (Game.isPause()) {
			Game.continueGame();
		} else {
			Game.pauseGame();
		}
	}

	/**
	 * 游戏是否第一次运行
	 * 
	 * @return
	 */
	public static boolean isFirst() {
		Game game = null;
		try {
			game = Game.getInstance();
		} catch (Exception e) {
			return true;
		}
		return game == null;
	}

	/**
	 * 游戏是否在运行
	 * 
	 * @return
	 */
	public static boolean isRunning() {

		return Snake.thread != null && Snake.thread.isAlive();
	}

	/**
	 * 游戏是否已暂停
	 * 
	 * @return
	 */
	public static boolean isPause() {
		return Snake.thread.isPause();
	}

	/**
	 * 添加事件
	 * 
	 * @param listener
	 *            GameListener
	 */
	public void addGameListener(GameListener listener) {
		if (listeners == null) {
			listeners = new HashSet<GameListener>();
		}
		listeners.add(listener);
	}

	/**
	 * 移除事件
	 * 
	 * @param listener
	 *            GameListener
	 */
	public void removeGameListener(GameListener listener) {
		if (listeners == null)
			return;
		listeners.remove(listener);
	}

	/**
	 * 触发游戏进行事件
	 */
	public void fireGameRuning() {

		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.RUNING);
		notifyListeners(event);
	}

	/**
	 * 触发游戏暂停事件
	 */
	public void fireGamePause() {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.PAUSE);
		notifyListeners(event);
	}

	/**
	 * 触发游戏继续事件
	 */
	public void fireGameContinue() {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.CONTINUE);
		notifyListeners(event);
	}

	/**
	 * 触发游戏停止事件
	 */
	public void fireGameStop() {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.STOP);
		notifyListeners(event);
	}

	/**
	 * 触发游戏异常停止事件
	 */
	public void fireGameStopErr(String message) {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.STOP_ERR, message);
		notifyListeners(event);
	}

	/**
	 * 触发游戏结束事件
	 */
	public void fireGameOver() {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.GAME_OVER);
		notifyListeners(event);
	}

	/**
	 * 触发游戏分数改变事件
	 */
	public void fireGameScoreChange() {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, GameState.GAME_SCORE_CHANGE);
		notifyListeners(event);
	}
	/**
	 * 触发游戏难度改变事件
	 * @param to 
	 * @param from 
	 * 
	 */
	public void fireGameLevelChange(Level from, Level to) {
		if (listeners == null)
			return;
		GameEvent event = new GameEvent(this, from, to);
		notifyListeners(event);
	}

	/**
	 * 通知所有的GameListener
	 */
	private void notifyListeners(GameEvent event) {
		Iterator<GameListener> iter = listeners.iterator();
		while (iter.hasNext()) {
			GameListener listener = (GameListener) iter.next();
			listener.gameEvent(event);
		}
	}

	/**
	 * 
	 */
	public static void initGame(JPanel panel) {
		if (Game.isFirst()) {
			Game.getInstance(panel);
		}
	}

	/**
	 * @param frame
	 * @param panelGame
	 * @param panelMain
	 */
	public static void initSnake(JPanel panelGame) {

		panelGame.requestFocus();
		panelGame.setFocusable(true);
		Constants.BLOCK_SIZE = panelGame.getWidth() / Constants.BLOCK_NUM;// 设置方块大小
		System.out.println("方块大小：" + Constants.BLOCK_SIZE);
		// ///
		Snake snake = Snake.getInstance(panelGame);

		snake.initSnakeWord();
		snake.born();
		snake.draw();
		snake.move();
		giveApple();
	}

	/**
	 * 创建苹果
	 */
	public static void giveApple() {
		Block block = BlockUtil.getRandomPos(false, true);
		System.out.println("apple position:" + block.getX() + "," + block.getY());
		Constants.apple = block;
		block.draw(ColorEnum.COLOR_APPLE.toColor());
		Snake.getInstance().getPanel().repaint();
	}

	/**
	 * 计分
	 */
	public static void calculateScore() {
		int score = Game.score;
		score += Constants.PER_SCORE;
		setScore(score);
		Game.getInstance().fireGameScoreChange();
	}

	/**
	 * 获取当前用户最高分
	 * @return
	 */
	public static String getCurrentUserHighestScore() {
		int score = 0;
		Map<String, Integer> map =  new HashMap<String, Integer>();
		JSONObject userConfig = Config.readUserConf(Config.getCurrentUsername());
		JSONArray scores = userConfig.getJSONArray("scores");
		if (scores.size() > 0) {
			for (Object object : scores) {
				JSONObject scoreObj = null;
				if (object instanceof JSONObject) {
					scoreObj = (JSONObject) object;
				}
				map.put(scoreObj.getString("time"), scoreObj.getInteger("sc"));
			}
			score = (int) MapUtil.getMaxValue(map);
		}
		
		return score + "";
	}

}
