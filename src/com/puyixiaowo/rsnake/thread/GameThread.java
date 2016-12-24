package com.puyixiaowo.rsnake.thread;

import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.model.Game;
import com.puyixiaowo.rsnake.model.Snake;

public class GameThread extends Thread {

		private final int STOP = -1;
		private final int SUSPEND = 0;
		private final int RUNNING = 1;
		private int status = 1;

		public GameThread(String name) {
			super(name);
		}

		@Override
		public synchronized void run() {

			try {
				// 判断是否停止
				while (status != STOP) {
					// 判断是否挂起
					if (status == SUSPEND) {
						// 若线程挂起则阻塞自己
						wait();
					} else {
						Snake.getInstance().moveOneStep();
						Thread.sleep(Constants.SNAKE_MOVE_INTERVAL_DEFAULT);
					}
				}
			} catch (Exception e) {
				System.out.println("线程异常终止..." + e.getMessage());
				Game.getInstance().fireGameStopErr();
			}
		}

		/**
		 * 恢复
		 */
		public synchronized void continueThread() {
			// 修改状态
			status = RUNNING;
			// 唤醒
			notifyAll();
		}

		/**
		 * 挂起
		 */
		public void pauseThread() {
			// 修改状态
			status = SUSPEND;
		}

		/**
		 * 停止
		 */
		public void stopThread() {
			// 修改状态
			status = STOP;
		}
		/**
		 * 是否已暂停
		 */
		public boolean isPause(){
			return status == SUSPEND;
		}

	}