/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.puyixiaowo.rsnake.constants.Constants;

/**
 * @author weishaoqiang
 * @date 2016年12月11日 上午10:04:28
 */
public class Snake {

	private List<Block> body;
	private int maxX;
	private int maxY;

	// /
	private JPanel panel;// 蛇所在画布
	private Timer timer;
	private int direction = -1;
	private int gamerDirection = -1;
	private Block latestBlock = null;

	/**
	 * 
	 */
	public Snake() {
		initSnakeWord();
		this.born();
		this.draw();
	}

	/**
	 * @param panel
	 */
	public Snake(JPanel panel) {
		this.panel = panel;
		initSnakeWord();
		this.born();
		this.draw();
	}

	/**
	 * 绘制蛇身
	 */
	private void draw() {
		for (Block block : this.getBody()) {
			block.draw();
		}
	}

	public List<Block> getBody() {
		return body;
	}

	public void setBody(List<Block> body) {
		this.body = body;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}

	public int getGamerDirection() {
		return gamerDirection;
	}

	public void setGamerDirection(int gamerDirection) {
		this.gamerDirection = gamerDirection;
	}

	// //////////////////////////////////////

	/**
	 * 初始化游戏世界
	 */
	private void initSnakeWord() {
		this.maxX = this.panel.getWidth() - Constants.BLOCK_SIZE;
		this.maxY = this.panel.getHeight() - Constants.BLOCK_SIZE;
	}

	/**
	 * 获取蛇出生边界
	 * 
	 * @return
	 */
	private void born() {
		body = new ArrayList<Block>();
		for (int i = 0; i < Constants.SNAKE_LENGTH; i++) {
			this.nextBodyBlock();
		}
		direction = getSnakeRandomDirection();// 获取蛇初始运动方向
		System.out.println("snake born direction:" + direction);
		System.out.println("snake born,position:");
		for (int i = 0; i < body.size(); i++) {
			Block block = body.get(i);
			System.out.println("block[" + i + "]x=" + block.getX() + ",y="
					+ block.getY());
		}
	}

	/**
	 * 获取蛇初始运动方向
	 * 
	 * @return
	 */
	private int getSnakeRandomDirection() {
		if (this.direction < 0) {
			this.direction = getRandomDirection();
		} else {
			// 获取键盘方向
			this.direction = this.gamerDirection;
		}
		return direction;
	}

	/**
	 * 获取随机方向
	 * 
	 * @return
	 */
	private int getRandomDirection() {
		Random random = new Random();
		int direction = random.nextInt(4);
		// 不可以倒退
		if ((direction + this.direction) == 1
				|| (direction + this.direction == 5)) {
			getRandomDirection();
		}
		// 不可以是身体方向
		Block head = this.body.get(0);
		if (!head.moveDirection(this, direction, false)) {
			getRandomDirection();
		}
		return direction;
	}

	/**
	 * 获取蛇身下一个方块
	 * 
	 * @return
	 */
	private void nextBodyBlock() {
		if (this.body.size() == 0) {
			Block head = getRandomPos(true, false);
			this.body.add(head);
		} else {
			Block lastBlock = this.body.get(this.body.size() - 1);
			Block block = getNextNotBodyInBoundsBlock(lastBlock);
			this.body.add(block);
		}

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
			getNextNotBodyInBoundsBlock(lastBlock);
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

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// 移动一步
				moveOneStep();
			}
		};
		timer = new Timer(Constants.SNAKE_MOVE_INTERVAL_DEFAULT, taskPerformer);
		timer.start();
		while (true)
			;
	}

	/**
	 * 
	 */
	private void moveOneStep() {
		// 判断蛇是否死亡
		if (isDead(this)) {
			dead();
			return;
		}

		//
		for (int i = 0; i < this.body.size(); i++) {
			Block b = this.body.get(i);
			Block temp = new Block(b.getX(), b.getY(), this.panel);
			if (i == 0) {
				b.moveDirection(this, this.direction, true);
			} else {
				b.moveTo(b, latestBlock);
			}
			latestBlock = temp;
			this.body.set(i, b);
		}

		System.out.print(this.getBody().get(0).getX() + "-"
				+ this.getBody().get(0).getY() + ",");
		System.out.print(this.getBody().get(1).getX() + "-"
				+ this.getBody().get(1).getY() + ",");
		System.out.println(this.getBody().get(2).getX() + "-"
				+ this.getBody().get(2).getY());
	}

	/**
	 * 判断蛇是否死亡
	 * 
	 * @param snake
	 * @return
	 */
	public boolean isDead(Snake snake) {
		return !this.body.get(0).moveDirection(snake, direction, false);
	}

	/**
	 * 
	 */
	private void dead() {
		if (timer != null) {
			timer.stop();
			System.out.println("Snake dead!");
		}
	}

	/**
	 * 是否在游戏出生边界内
	 * 
	 * @param block
	 * @return
	 */
	public boolean isInBurnBounds(Block block) {

		return block.getX() >= Constants.BOUNDS_BLOCK_NUM * Constants.BLOCK_SIZE
				&& block.getX() <= (maxX - Constants.BOUNDS_BLOCK_NUM
						* Constants.BLOCK_SIZE)
				&& block.getY() >= Constants.BOUNDS_BLOCK_NUM
						* Constants.BLOCK_SIZE
				&& block.getY() <= (maxY - Constants.BOUNDS_BLOCK_NUM
						* Constants.BLOCK_SIZE);
	}

	/**
	 * 是否在游戏边界内
	 * 
	 * @param block
	 * @return
	 */
	public boolean isInBounds(Block block) {

		return block.getX() >= 0 && block.getX() <= maxX && block.getY() >= 0
				&& block.getY() <= maxY;
	}
	
	/**
	 * 块是否是身体
	 * 
	 * @return
	 */
	public boolean isBody(Block block) {

		return this.body.contains(block);
	}

	/**
	 * 获取游戏边界内随机点
	 * 
	 * @param bounds
	 *            是否在出生边界内
	 * @param apple
	 *            是否是苹果
	 * @return
	 */
	private Block getRandomPos(boolean burnBounds, boolean apple) {
		Random random = new Random();

		int randomX = 0;
		int randomY = 0;
		// 获取边界内的随机位置
		while (burnBounds
				&& !isInBurnBounds(new Block(randomX, randomY, this.panel))) {
			randomX = random.nextInt(Constants.BLOCK_NUM)
					* Constants.BLOCK_SIZE;
			randomY = random
					.nextInt(Constants.BLOCK_NUM * Constants.BLOCK_SIZE);
		}

		while (apple
				&& !isInBounds(new Block(randomX, randomY, this.panel))
				&& isBody(new Block(randomX, randomY, this.panel))) {
			randomX = random.nextInt(Constants.BLOCK_NUM)
					* Constants.BLOCK_SIZE;
			randomY = random
					.nextInt(Constants.BLOCK_NUM * Constants.BLOCK_SIZE);
		}
		return new Block(randomX, randomY, this.panel);
	}

}
