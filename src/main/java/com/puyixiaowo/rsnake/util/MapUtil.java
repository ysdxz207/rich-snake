/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 上午12:52:43
 */
public class MapUtil {
	/**
	 * 求Map<K,V>中Key(键)的最大值
	 * 
	 * @param map
	 * @return
	 */
	public static Object getMaxKey(Map<Integer, Integer> map) {
		if (map == null)
			return null;
		Set<Integer> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		return obj[obj.length - 1];
	}

	/**
	 * 求Map<K,V>中Value(值)的最大值
	 * 
	 * @param map
	 * @return
	 */
	public static Object getMaxValue(Map<String, Integer> map) {
		if (map == null)
			return null;
		Collection<Integer> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		return obj[obj.length - 1];
	}

}
