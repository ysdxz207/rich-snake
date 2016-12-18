/**
 * 
 */
package com.puyixiaowo.rsnake.model;

/**
 * @author weishaoqiang
 * @date 2016年12月18日 下午10:53:50
 */
public class Game {
	
	public static void start() {
		Snake.timer.start();
	}
	
	public static void pause() {
		Snake.timer.stop();
	}

	public static void restart() {
		
	}

	/**
	 * 
	 */
	public static void pauseOrContinue() {
		if (Snake.timer.isRunning()) {
			Snake.timer.stop();
		} else {
			Snake.timer.start();
		}
	}

}
