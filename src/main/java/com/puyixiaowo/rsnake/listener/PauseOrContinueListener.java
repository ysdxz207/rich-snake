/**
 * 
 */
package com.puyixiaowo.rsnake.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.puyixiaowo.rsnake.model.Game;
import com.puyixiaowo.rsnake.model.Snake;

/**
 * 暂停游戏或继续游戏监听器
 * @author weishaoqiang
 * @date 2016年12月24日 上午11:11:04
 */
public class PauseOrContinueListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// 暂停或继续游戏
			Game.pauseOrContinueGame();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Snake snake = Snake.getInstance();
		// 不可以倒退
		if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40 && (e.getKeyCode() + snake.getDirection()) != 78
				&& (e.getKeyCode() + snake.getDirection() != 76)) {
			snake.setDirection(e.getKeyCode());
		}
	}
}
