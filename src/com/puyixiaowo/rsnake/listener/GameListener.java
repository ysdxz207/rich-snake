/**
 * 
 */
package com.puyixiaowo.rsnake.listener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.puyixiaowo.rsnake.GameState;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.event.GameEvent;
import com.puyixiaowo.rsnake.util.ComponentUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月23日 下午9:55:29
 */
public class GameListener implements Listener{

	@Override
	public void gameEvent(GameEvent gameEvent) {
		
		try {
			JPanel panel = (JPanel) ComponentUtil.getComponentByName(Constants.NAME_PANEL_GAME);
			if(gameEvent.getGameState().equals(GameState.GAME_OVER)) {
				JOptionPane.showMessageDialog(panel, "游戏已结束！", "提示",JOptionPane.WARNING_MESSAGE);  
			}
			if(gameEvent.getGameState().equals(GameState.STOP_ERR)) {
				JOptionPane.showMessageDialog(panel, "游戏异常终止：" + gameEvent.getMessage(), "提示",JOptionPane.ERROR_MESSAGE);  
			}
			if(gameEvent.getGameState().equals(GameState.PAUSE)) {
				//游戏暂停
				
			}
			if(gameEvent.getGameState().equals(GameState.GAME_SCORE_CHANGE)) {
				//游戏分数改变
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
