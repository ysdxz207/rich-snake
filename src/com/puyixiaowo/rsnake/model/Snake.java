/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.ColorEnum;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.thread.GameThread;
import com.puyixiaowo.rsnake.util.BlockUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 上午10:04:28
 */
public class Snake {

	// 使用volatile关键字保其可见性
	volatile private static Snake instance = null;

	private static List<Block> body;
	private static int maxX;
	private static int maxY;

	// /
	private JPanel panel;// 蛇所在画布
	private int direction = -1;
	private Block latestBlock = null;
	private int currentDirection;// 当前运动方向
	public static GameThread thread;
	
	public static Snake getInstance() {
		if (instance == null) {// 懒汉式
			System.err.println("无Snake实例");
		}
		return instance;
	}
	

	public static Snake getInstance(JPanel panel) {
		try {
			if (instance != null) {// 懒汉式

			} else {
				// 创建实例之前可能会有一些准备性的耗时工作
				Thread.sleep(300);
				synchronized (Snake.class) {
					if (instance == null) {// 二次检查
						instance = new Snake(panel);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * @param panel
	 */
	private Snake(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * 绘制蛇身
	 */
	void draw() {
		for (Block block : body) {
			block.draw(ColorEnum.COLOR_SNAKE.toColor());
		}
	}

	public int getMaxX() {
		return maxX;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}

	// //////////////////////////////////////
	
	
	

	/**
	 * 初始化游戏世界
	 */
	void initSnakeWord() {
		maxX = this.panel.getWidth() - Constants.BLOCK_SIZE;
		maxY = this.panel.getHeight() - Constants.BLOCK_SIZE;
	}

	/**
	 * 获取蛇出生边界
	 * 
	 * @return
	 */
	void born() {
		body = new ArrayList<Block>();
		for (int i = 0; i < Constants.SNAKE_LENGTH; i++) {
			this.nextBodyBlock();
		}
		direction = getSnakeRandomDirection();// 获取蛇初始运动方向
		//System.out.println("snake born direction:" + direction);
		//System.out.println("snake born,position:");
//		for (int i = 0; i < body.size(); i++) {
//			Block block = body.get(i);
//			System.out.println("block[" + i + "]x=" + block.getX() + ",y=" + block.getY());
//		}
	}

	/**
	 * 获取蛇初始运动方向
	 * 
	 * @return
	 */
	private int getSnakeRandomDirection() {
		this.direction = getRandomDirection();
		this.currentDirection = this.direction;
		return direction;
	}

	/**
	 * 获取随机方向
	 * 
	 * @return
	 */
	private int getRandomDirection() {
		Random random = new Random();
		int direction = random.nextInt(4) + 37;
		// 不可以倒退
		if ((direction + this.direction) == 78 || (direction + this.direction == 76)) {
			direction = getRandomDirection();
		}
		// 不可以是身体方向
		Block head = new Block(body.get(0).getX(), body.get(0).getY(), panel);
		if (!head.moveHeadDirection(this, direction, false)) {
			direction = getRandomDirection();
		}
		return direction;
	}

	/**
	 * 获取蛇身下一个方块
	 * 
	 * @return
	 */
	private void nextBodyBlock() {
		if (body.size() == 0) {
			Block head = BlockUtil.getRandomPos(true, false);
			body.add(head);
		} else {
			Block lastBlock = body.get(body.size() - 1);
			Block block = getNextNotBodyInBoundsBlock(lastBlock);
			body.add(block);
		}

	}

	/**
	 * 获取蛇身下一个带方向的方块
	 * 
	 * @return
	 */
	private void nextBodyDirectionBlock() {

		Block lastBlock = body.get(body.size() - 1);
		Block block = new Block(lastBlock.getX(), lastBlock.getY(), this.panel);
		body.add(block);
		block.draw(ColorEnum.COLOR_SNAKE.toColor());
	}

	/**
	 * 获取非身体，且在游戏出生边界内的方块
	 * 
	 * @param lastBlock
	 * @return
	 */
	private Block getNextNotBodyInBoundsBlock(Block lastBlock) {
		Block block = lastBlock.nextRandomBlock(this);
		if (isBody(block) || !isInBurnBounds(block)) {
			block = getNextNotBodyInBoundsBlock(lastBlock);
		}
		return block;
	}

	/**
	 * 蛇移动
	 */
	public void move() {
		moveForward();
	}

	/**
	 * 向前移动
	 * 
	 * @param snake
	 * @param direction
	 */
	private void moveForward() {

		thread = new GameThread("游戏主线程");
	}

	/**
	 * 
	 */
	public void moveOneStep() {
		// 不可与当前运动方向相反
		if (this.direction != this.currentDirection
				&& ((this.direction + this.currentDirection) == 76 || (this.direction + this.currentDirection) == 78)) {
			this.direction = this.currentDirection;
		}

		// 判断蛇是否死亡
		if (isDead(this)) {
			dead();
			return;
		}

		//
		for (int i = 0; i < body.size(); i++) {
			Block b = body.get(i);
			Block temp = new Block(b.getX(), b.getY(), this.panel);
			if (i == 0) {
				b.moveHeadDirection(this, this.direction, true);
			} else {
				b.moveTo(b, latestBlock);
			}
			latestBlock = temp;
			body.set(i, b);
		}
		currentDirection = direction;

//		System.out.print(this.getBody().get(0).getX() + "-" + this.getBody().get(0).getY() + ",");
//		System.out.print(this.getBody().get(1).getX() + "-" + this.getBody().get(1).getY() + ",");
//		System.out.println(this.getBody().get(2).getX() + "-" + this.getBody().get(2).getY());
	}

	/**
	 * 判断蛇是否死亡
	 * 
	 * @param snake
	 * @return
	 */
	public boolean isDead(Snake snake) {

		return !body.get(0).moveHeadDirection(snake, direction, false);
	}

	/**
	 * 
	 */
	private static void dead() {
		Game.gameOver();
	}

	/**
	 * 是否在游戏出生边界内
	 * 
	 * @param block
	 * @return
	 */
	public static boolean isInBurnBounds(Block block) {

		return block.getX() >= Constants.BOUNDS_BLOCK_NUM * Constants.BLOCK_SIZE
				&& block.getX() <= (maxX - Constants.BOUNDS_BLOCK_NUM * Constants.BLOCK_SIZE)
				&& block.getY() >= Constants.BOUNDS_BLOCK_NUM * Constants.BLOCK_SIZE
				&& block.getY() <= (maxY - Constants.BOUNDS_BLOCK_NUM * Constants.BLOCK_SIZE);
	}

	/**
	 * 是否在游戏边界内
	 * 
	 * @param block
	 * @return
	 */
	public static boolean isInBounds(Block block) {

		return block.getX() >= 0 && block.getX() <= maxX && block.getY() >= 0 && block.getY() <= maxY;
	}

	/**
	 * 块是否是身体
	 * 
	 * @return
	 */
	public static boolean isBody(Block block) {
		for (Block b : body) {
			if (b.getX() == block.getX() && b.getY() == block.getY()) {
				return true;
			}
		}
		return false;
	}

	

	/**
	 * 吃苹果
	 */
	public void eatApple() {
		this.nextBodyDirectionBlock();
		// 移除苹果
		JLabel apple = (JLabel) this.panel.getComponentAt(new Point(Constants.apple.getX(), Constants.apple.getY()));
		this.panel.remove(apple);
		this.panel.repaint();
		
		//计分
		Game.calculateScore();
		Game.giveApple();
	}
	
}
