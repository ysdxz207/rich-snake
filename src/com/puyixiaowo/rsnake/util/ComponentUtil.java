/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import com.puyixiaowo.rsnake.model.Snake;

/**
 * @author weishaoqiang
 * @date 2016年12月23日 下午11:18:52
 */
public class ComponentUtil {

	public static Component findComponentByName(Container container, String componentName) {
		for (Component component : container.getComponents()) {
			if (componentName.equals(component.getName())) {
				return component;
			}
			if (component instanceof JRootPane) {
				// According to the JavaDoc for JRootPane, JRootPane is
				// "A lightweight container used behind the scenes by JFrame,
				// JDialog, JWindow, JApplet, and JInternalFrame.". The
				// reference
				// to the RootPane is set up by implementing the
				// RootPaneContainer
				// interface by the JFrame, JDialog, JWindow, JApplet and
				// JInternalFrame. See also the JavaDoc for RootPaneContainer.
				// When a JRootPane is found, recurse into it and continue
				// searching.
				JRootPane nestedJRootPane = (JRootPane) component;
				return findComponentByName(nestedJRootPane.getContentPane(), componentName);
			}
			if (component instanceof JPanel) {
				// JPanel found. Recursing into this panel.
				JPanel nestedJPanel = (JPanel) component;
				return findComponentByName(nestedJPanel, componentName);
			}
		}
		return null;
	}

	/**
	 * 根据控件名称获取控件
	 * 
	 * @param name
	 * @return
	 */
	public static Component getComponentByName(String name) {
		JFrame frame = null;
		try {
			frame = (JFrame) SwingUtilities.getRoot(Snake.getInstance().getPanel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return findComponentByName(frame, name);
	}

}
