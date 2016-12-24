/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.Constants;

/**
 * 蛇身方块
 * 
 * @author weishaoqiang
 * @date 2016年12月11日 下午1:56:55
 */
public class Block {
	private int x;
	private int y;
	private JPanel panel;

	public int getX() {
		return x;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Block(int x, int y, JPanel panel) {
		super();
		this.x = x;
		this.y = y;
		this.panel = panel;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	//////////////////////
	/**
	 * 根据当前身体块获取下一个身体块
	 * 
	 * @return
	 */
	public Block nextRandomBlock(Snake snake) {
		int x = this.getX();
		int y = this.getY();
		int size = Constants.BLOCK_SIZE;

		Random random = new Random();
		int direction = random.nextInt(4) + 37;//四个方向
		switch (direction) {
		case KeyEvent.VK_UP:
			// 向上
			y -= size;
			break;
		case KeyEvent.VK_DOWN:
			// 向下
			y += size;
			break;
		case KeyEvent.VK_LEFT:
			// 向左
			x -= size;
			break;
		case KeyEvent.VK_RIGHT:
			// 向右
			x += size;
			break;

		default:
			y -= size;
			break;
		}
		Block block = new Block(x, y, this.getPanel());
		if (Snake.isBody(block)) {
			nextRandomBlock(snake);
		}
		return block;
	}
	
	/**
	 * 头部朝某放向移动一个格子
	 * 
	 * @param direction
	 * @return
	 */
	public boolean moveHeadDirection(Snake snake, int direction, boolean isMove) {
		
		int x = this.getX();
		int y = this.getY();
		int size = Constants.BLOCK_SIZE;
		Block point = new Block(x, y, this.panel);
		switch (direction) {
		case KeyEvent.VK_UP:
			y -= size;
			break;
		case KeyEvent.VK_DOWN:
			y += size;
			break;
		case KeyEvent.VK_LEFT:
			x -= size;
			break;
		case KeyEvent.VK_RIGHT:
			x += size;
			break;
		default:
			break;
		}
		Block to = new Block(x, y, this.panel);
		if (Snake.isBody(to) || !Snake.isInBounds(to)) {
			return false;
		}
		if (isMove){
			//若有苹果则吃掉
			if (Constants.apple.getX() == to.getX() && Constants.apple.getY() == to.getY()) {
				snake.eatApple();
			}
			moveTo(point, to);
		}
		return true;
	}

	/**
	 * 绘制蛇身方块
	 * 
	 * @param panel
	 * @param block
	 */
	public void draw(Color color) {
		JLabel label = new JLabel();
		label.setSize(Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
		label.setOpaque(true);// 设置组件JLabel不透明，只有设置为不透明，设置背景色才有效
		label.setBorder(null);
		label.setBackground(color);
		label.setLocation(this.getX(), this.getY());
		panel.add(label);
	}

	/**
	 * 方块移动
	 * 
	 * @param from
	 * @param to
	 */
	public void moveTo(Block from, Block to) {
		JLabel label = (JLabel) panel.getComponentAt(from.getX(), from.getY());
		label.setLocation(to.getX(), to.getY());
		this.setX(to.getX());
		this.setY(to.getY());
	}

}
