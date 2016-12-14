/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.ColorEnum;
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
		int direction = random.nextInt(4);//四个方向
		switch (direction) {
		case 0:
			// 向上
			y -= size;
			break;
		case 1:
			// 向下
			y += size;
			break;
		case 2:
			// 向左
			x -= size;
			break;
		case 3:
			// 向右
			x += size;
			break;

		default:
			y -= size;
			break;
		}
		Block block = new Block(x, y, this.getPanel());
		if (snake.isBody(block)) {
			nextRandomBlock(snake);
		}
		return block;
	}
	
	/**
	 * 获取朝某放向移动一个格子
	 * 
	 * @param direction
	 * @return
	 */
	public boolean moveDirection(Snake snake, int direction, boolean isMove) {
		int x = this.getX();
		int y = this.getY();
		int size = Constants.BLOCK_SIZE;
		Block point = new Block(x, y, this.panel);
		switch (direction) {
		case 0:
			y -= size;
			break;
		case 1:
			y += size;
			break;
		case 2:
			x += size;
			break;
		case 3:
			x -= size;
			break;
		default:
			break;
		}
		Block to = new Block(x, y, this.panel);
		if (snake.isBody(to) || !snake.isInBounds(to)) {
			return false;
		}
		if (isMove){
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
	public void draw() {
		JLabel label = new JLabel();
		label.setSize(panel.getWidth() / Constants.BLOCK_NUM, panel.getHeight() / Constants.BLOCK_NUM);
		label.setOpaque(true);// 设置组件JLabel不透明，只有设置为不透明，设置背景色才有效
		label.setBorder(null);
		label.setBackground(ColorEnum.COLOR_SNAKE.toColor());
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
