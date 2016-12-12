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
	
	///
	private JPanel panel;//蛇所在画布
	private Timer timer;

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
	////////////////////////////////////////

	/**
	 * 初始化游戏世界
	 */
	private void initSnakeWord() {
		this.maxX = this.panel.getWidth();
		this.maxY = this.panel.getHeight();
	}

	/**
	 * 获取蛇出生边界
	 * 
	 * @return
	 */
	private void born() {
		body = new ArrayList<Block>();
		Block head = getRandomPos(true, false);

		Block body1 = head.nextBlock();
		Block body2 = body1.nextBlock();
		Block body3 = body2.nextBlock();

		body.add(head);
		body.add(body1);
		body.add(body2);
		body.add(body3);

	}

	/**
	 * 蛇移动
	 */
	public void move() {
		moveForward(this);
	}

	/**
	 * @return
	 */
	private int getDirection() {
		Random random = new Random();
		int direction = random.nextInt(4);
		return direction;
	}

	/**
	 * 向前移动
	 * 
	 * @param snake
	 * @param direction
	 */
	private void moveForward(Snake snake) {
		int delay = Constants.SNAKE_MOVE_INTERVAL_DEFAULT; // milliseconds

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// 移动一步
				moveOneStep();
				System.out.print(snake.getBody().get(0).getX() + "-" + snake.getBody().get(0).getY() + ",");
				System.out.print(snake.getBody().get(1).getX() + "-" + snake.getBody().get(0).getY() + ",");
				System.out.print(snake.getBody().get(2).getX() + "-" + snake.getBody().get(0).getY() + ",");
				System.out.println(snake.getBody().get(3).getX() + "-" + snake.getBody().get(0).getY());
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
		while (true)
			;
	}

	/**
	 * 
	 */
	private void moveOneStep() {
		int direction = getDirection();
		//判断蛇是否死亡
		Block head = this.getBody().get(0);
		Block headTemp = new Block(head.getX(), head.getY(), this.panel);
		headTemp.moveDirection(direction);
		if (isBody(headTemp) || !isInBounds(headTemp)) {
			dead();
			return;
		}
		List<Block> bodyTemp = new ArrayList<Block>();
		//
		for (int i = 0; i < this.body.size(); i++) {
			Block b = this.body.get(i);
			b.moveDirection(direction);
			bodyTemp.add(b);
		}
		this.body = bodyTemp;
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
	 * 是否在游戏边界内
	 * 
	 * @param block
	 * @return
	 */
	private boolean isInBounds(Block block) {

		return block.getX() > Constants.BOUNDS_BLOCK_NUM && block.getX() < (maxX - Constants.BOUNDS_BLOCK_NUM)
				&& block.getY() > Constants.BOUNDS_BLOCK_NUM && block.getY() < (maxY - Constants.BOUNDS_BLOCK_NUM);
	}

	/**
	 * 块是否是身体
	 * 
	 * @return
	 */
	private boolean isBody(Block block) {

		return this.getBody().contains(block);
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
	private Block getRandomPos(boolean bounds, boolean apple) {
		Random random = new Random();

		int randomX = random.nextInt(maxX);
		int randomY = random.nextInt(maxY);

		if (!bounds) {
			return new Block(randomX, randomY, this.panel);
		}

		if (apple) {
			Block block = new Block(randomX, randomY, this.panel);
			// 不可是蛇本身
			for (Block b : body) {
				if (block.equals(b)) {
					
				}

			}
		}

		// 获取边界内的随机位置
		while (!(randomX > Constants.BOUNDS_BLOCK_NUM && randomX < (maxX - Constants.BOUNDS_BLOCK_NUM)
				&& randomY > Constants.BOUNDS_BLOCK_NUM && randomY < (maxY - Constants.BOUNDS_BLOCK_NUM))) {
			randomX = random.nextInt(maxX);
			randomY = random.nextInt(maxY);
		}

		return new Block(randomX, randomY, this.panel);
	}

	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.move();
	}
}
