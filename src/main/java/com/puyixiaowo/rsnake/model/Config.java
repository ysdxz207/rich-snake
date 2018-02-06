/**
 * 
 */
package com.puyixiaowo.rsnake.model;

import java.io.File;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.enums.Level;
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
	private static String rootPath;
	private static String rootconfigPath;
	private static String configFilePath;

	public String getConfigFilePath() {
		return configFilePath;
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
		rootPath = getConfigDir();
		rootconfigPath = getRootConfigPaht();
		configFilePath = rootconfigPath + "conf/";
	}

	/**
	 * 获取配置文件根路径
	 * 
	 * @return
	 */
	private String getRootConfigPaht() {

		return System.getProperty("user.dir") + "/";
	}

	/**
	 * 获取图片资源目录
	 * 
	 * @return
	 */
	public static String getImagesResourcePath() {
		return Config.rootPath + "images/";
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
		StringBuffer buffer = FileUtil.readToBuffer(configFilePath + Constants.CONFIG_FILE_NAME, Constants.ENCODE);
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
		Level level = Game.level == null ? Constants.level : Game.level;
		JSONObject config = new JSONObject();

		JSONObject score = new JSONObject();
		score.put("time", DateUtil.getNowStr());
		score.put("sc", sc);
		score.put("level", level.getDescription());

		if (isUserConfExists(username)) {
			readUserConf(username).put("score", score);
		} else {
			// 创建新的用户配置
			JSONArray configs = new JSONArray();

			JSONObject userConf = new JSONObject();
			JSONArray scores = new JSONArray();
			
			if (sc > 0) {
				scores.add(score);
			}

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
		String str = "";
		try {
			str = EncryptUtils.encryptToDES(Constants.DES_KEY, Constants.CONFIG.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (new File(configFilePath + Constants.CONFIG_FILE_NAME).exists()) {

			FileUtil.saveFile(configFilePath + Constants.CONFIG_FILE_NAME, str);
		} else {
			FileUtil.writeFile(str, configFilePath, Constants.CONFIG_FILE_NAME, Constants.ENCODE);
		}
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
		score.put("level", Game.level.getDescription());
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
		return new File(configFilePath + Constants.CONFIG_FILE_NAME).exists();
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

	public static String[][] getScoreTableList() {
		JSONObject userConfig = Config.readUserConf(Config.getCurrentUsername());
		JSONArray scores = userConfig.getJSONArray("scores");
		String[][] data = new String[scores.size()][3];
		for (int i = 0; i < scores.size(); i++) {
			String[] scoreData = new String[3];
			Object object = scores.get(i);
			JSONObject score = null;
			if (object instanceof JSONObject) {
				score = (JSONObject) object;
				int sc = score.getIntValue("sc");
				scoreData[0] = sc + "";
				scoreData[1] = score.getString("level");
				scoreData[2] = score.getString("time");
				if (sc > 0) {
					data[scores.size() - 1 - i] = scoreData;
				}
			}
		}

		return data;
	}

}
