/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.io.File;

import javax.crypto.SecretKey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.util.DateUtil;
import com.puyixiaowo.rsnake.util.EncryptUtils;
import com.puyixiaowo.rsnake.util.FileUtil;

/**
 * @author weishaoqiang
 * @date 2016年12月24日 下午3:33:17
 */
public class Config {

	// 使用volatile关键字保其可见性
	volatile private static Config instance = null;
	private static String filePath;

	public String getFilePath() {
		return filePath;
	}

	public static Config getInstance() {
		try {
			if (instance != null) {// 懒汉式

			} else {
				// 创建实例之前可能会有一些准备性的耗时工作
				Thread.sleep(300);
				synchronized (Config.class) {
					if (instance == null) {// 二次检查
						instance = new Config();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return instance;
	}

	//////////////////////////////

	/**
	 * 
	 */
	public Config() {
		filePath = getConfigDir() + "conf/";
	}

	/**
	 * 
	 * @return
	 */
	private static String getConfigDir() {
		return Config.class.getResource("/").getPath();
	}

	/**
	 * 初始化配置文件
	 */
	public static void initConf() {

		Config.getInstance();

		if (confExists()) {
			return;
		}

		SecretKey key = EncryptUtils.createSecretDESKey();
		Constants.DES_KEY = key;
		
		addUserConf("大帅比", -1);//添加默认用户配置
		saveConf();
	}

	/**
	 * @return
	 */
	private static JSONArray JSONArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 读取配置文件
	 */
	public static JSONArray readConf() {
		if (Constants.CONFIG == null) {
			refreshConf();
		}
		return Constants.CONFIG;
	}

	/**
	 * 根据用户名获取用户配置
	 * 
	 * @param username
	 * @return
	 */
	public static JSONObject readUserConf(String username) {
		JSONArray configs = readConf();
		if (configs != null) {
			for (Object confObj : configs) {
				JSONObject conf = null;
				if (confObj instanceof JSONObject) {
					conf = (JSONObject) confObj;
				}
				if (conf.containsKey(username)) {
					return conf;
				}
			}
		}
		
		return null;
	}

	/**
	 * 刷新配置
	 */
	private static void refreshConf() {
		StringBuffer buffer = FileUtil.readToBuffer(filePath + Constants.CONFIG_FILE_NAME, Constants.ENCODE);
		if (buffer == null) {
			return;
		}
		String str = buffer.toString();
		String configsStr = EncryptUtils.decryptByDES(Constants.DES_KEY, str);
		Constants.CONFIG = JSONArray.parseArray(configsStr);
	}

	/**
	 * 添加用户配置
	 * @param username
	 * @param sc
	 * 			分数
	 */
	public static void addUserConf(String username, int sc) {
		JSONObject score = new JSONObject();
		score.put("time", DateUtil.getNowStr());
		score.put("sc", sc);
		
		if (isUserConfExists(username)) {
			readUserConf(username).put("score", score);
		} else {
			//创建新的用户配置
			JSONArray configs = new JSONArray();

			JSONObject userConf = new JSONObject();
			JSONArray scores = new JSONArray();
			scores.add(score);
			
			userConf.put("username", username);
			userConf.put("scores", scores);
			configs.add(userConf);

			Constants.CONFIG = configs;
		}
		
	}
	/**
	 * 是否已有用户配置
	 * @param username
	 * @return
	 */
	private static boolean isUserConfExists(String username) {
		
		return readUserConf(username) != null;
	}

	/**
	 * 保存配置到文件
	 */
	public static void saveConf(){
		String str = EncryptUtils.encryptToDES(Constants.DES_KEY, Constants.CONFIG.toJSONString());

		FileUtil.writeFile(str, filePath, Constants.CONFIG_FILE_NAME, Constants.ENCODE);
	}

	public static void saveScore(String username, int sc) {
		JSONArray configs = readConf();
		JSONObject userConf = readUserConf(username);
		JSONArray scores = userConf.getJSONArray("scores");

		refreshConf();// 刷新配置
	}

	/**
	 * @return
	 */
	private static boolean confExists() {
		return new File(filePath + Constants.CONFIG_FILE_NAME).exists();
	}

}
