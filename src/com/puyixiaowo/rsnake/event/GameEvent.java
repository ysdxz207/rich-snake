/**
 * 
 */
package com.puyixiaowo.rsnake.event;

import java.util.EventObject;

import com.puyixiaowo.rsnake.GameState;

/**
 * @author weishaoqiang
 * @date 2016年12月23日 下午9:46:26
 */
public class GameEvent extends EventObject {
	
	private GameState gameState;
	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param source
	 * @param gameState
	 */
	public GameEvent(Object source, GameState gameState) {
		super(source);
		this.gameState = gameState;
	}

	/**
	 * @param game
	 * @param stopErr
	 * @param message2
	 */
	public GameEvent(Object source, GameState gameState, String message) {
		super(source);
		this.gameState = gameState;
		this.message = message;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
