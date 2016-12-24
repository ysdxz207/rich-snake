/**
 * 
 */
package com.puyixiaowo.rsnake.listener;

import java.util.EventListener;

import com.puyixiaowo.rsnake.event.GameEvent;

/**
 * @author weishaoqiang
 * @date 2016年12月23日 下午9:34:32
 */
public interface Listener extends EventListener{
	/**
	 * 游戏事件
	 * @param gameEvent
	 */
	public void gameEvent(GameEvent gameEvent);
}
