/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 下午10:02:10
 */
public class ResourceUtil {
	/**
	 * 通过文件名获取图片资源
	 * @param fileName
	 * @return
	 */
	public static Image getImage(String fileName){
		InputStream input = ResourceUtil.class.getResourceAsStream("/images/" + fileName);
		try {
			return ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
