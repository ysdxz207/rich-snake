/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import java.util.Random;

import javax.swing.JPanel;

import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.model.Block;
import com.puyixiaowo.rsnake.model.Snake;

/**
 * @author weishaoqiang
 * @date 2016年12月24日 上午12:35:57
 */
public class BlockUtil {
	/**
	 * 获取游戏边界内随机点
	 * 
	 * @param bounds
	 *            是否在出生边界内
	 * @param apple
	 *            是否是苹果
	 * @return
	 */
	public static Block getRandomPos(boolean burnBounds, boolean apple) {
		JPanel panel = Snake.getInstance().getPanel();
		Random random = new Random();

		int randomX = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
		int randomY = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
		// 获取边界内的随机位置
		while (burnBounds && !Snake.isInBurnBounds(new Block(randomX, randomY, panel))) {
			randomX = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
			randomY = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
		}

		while (apple && !Snake.isInBounds(new Block(randomX, randomY, panel))
				&& Snake.isBody(new Block(randomX, randomY, panel))) {
			randomX = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
			randomY = random.nextInt(Constants.BLOCK_NUM) * Constants.BLOCK_SIZE;
		}
		System.out.println("***********" + Constants.BLOCK_SIZE + "===" + Constants.BLOCK_NUM);
		System.out.println("randomX:" + randomX + ",randomY:" + randomY);
		return new Block(randomX, randomY, panel);
	}
}
