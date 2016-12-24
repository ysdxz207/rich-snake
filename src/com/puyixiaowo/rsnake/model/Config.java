/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.io.File;

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

		if (isConfExists()) {
			readConf();
			return;
		}

		addUserConf("大帅比", -1);// 添加默认用户配置
		saveConf();
	}

	/**
	 * 读取配置文件到全局变量
	 */
	public static JSONObject readConf() {
		if (Constants.CONFIG != null) {
			return Constants.CONFIG;
		}
		StringBuffer buffer = FileUtil.readToBuffer(filePath + Constants.CONFIG_FILE_NAME, Constants.ENCODE);
		if (buffer == null) {
			return null;
		}
		String str = buffer.toString();
		String configsStr = null;
		try {
			configsStr = EncryptUtils.decryptByDES(Constants.DES_KEY, str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Constants.CONFIG = JSONObject.parseObject(configsStr);
		return Constants.CONFIG;
	}

	/**
	 * 根据用户名获取用户配置
	 * 
	 * @param username
	 * @return
	 */
	public static JSONObject readUserConf(String username) {
		JSONObject config = readConf();
		if (config != null) {
			JSONArray configs = config.getJSONArray("configs");
			for (Object confObj : configs) {
				JSONObject conf = null;
				if (confObj instanceof JSONObject) {
					conf = (JSONObject) confObj;
				}
				if (conf.containsValue(username)) {
					return conf;
				}
			}
		}

		return null;
	}

	/**
	 * 刷新全局变量配置
	 */
	private static void refreshConf(JSONObject config) {

		Constants.CONFIG = config;
	}

	/**
	 * 添加用户配置
	 * 
	 * @param username
	 * @param sc
	 *            分数
	 */
	public static void addUserConf(String username, int sc) {
		JSONObject score = new JSONObject();
		score.put("time", DateUtil.getNowStr());
		score.put("sc", sc);

		if (isUserConfExists(username)) {
			readUserConf(username).put("score", score);
		} else {
			// 创建新的用户配置
			JSONObject config = new JSONObject();
			JSONArray configs = new JSONArray();

			JSONObject userConf = new JSONObject();
			JSONArray scores = new JSONArray();
			scores.add(score);

			userConf.put("username", username);
			userConf.put("scores", scores);
			configs.add(userConf);

			config.put("currentUser", username);
			config.put("configs", configs);

			Constants.CONFIG = config;
		}

	}

	/**
	 * 是否已有用户配置
	 * 
	 * @param username
	 * @return
	 */
	private static boolean isUserConfExists(String username) {

		return readUserConf(username) != null;
	}

	/**
	 * 保存配置到文件
	 */
	public static void saveConf() {
		String str = null;
		try {
			str = EncryptUtils.encryptToDES(Constants.DES_KEY, Constants.CONFIG.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileUtil.saveFile(filePath + Constants.CONFIG_FILE_NAME, str);
	}

	public static void addScore(int sc) {
		if (sc == 0) {
			return;
		}
		JSONObject config = readConf();
		JSONArray configs = config.getJSONArray("configs");
		String currentUser = getCurrentUsername();

		JSONObject userConf = readUserConf(currentUser);
		configs.remove(userConf);

		JSONArray scores = userConf.getJSONArray("scores");
		JSONObject score = new JSONObject();
		score.put("sc", sc);
		score.put("time", DateUtil.getNowStr());
		scores.add(score);
		userConf.put("scores", scores);
		configs.add(userConf);
		config.put("configs", configs);
		System.out.println(config);
		refreshConf(config);// 刷新配置
	}

	/**
	 * 配置文件是否存在
	 * 
	 * @return
	 */
	private static boolean isConfExists() {
		return new File(filePath + Constants.CONFIG_FILE_NAME).exists();
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public static String getCurrentUsername() {
		JSONObject conf = readConf();
		return conf.getString("currentUser");
	}

}
