/**
 * 
 */
package com.puyixiaowo.rsnake.test;

import com.puyixiaowo.rsnake.constants.Constants;
import com.puyixiaowo.rsnake.util.EncryptUtils;

/**
 * @author weishaoqiang
 * @date 2016年12月25日 上午12:31:47
 */
public class TestEncrypt {
	public static void main(String[] args) throws Exception {
		String str = "A097074E150D06E73610CFCE47945302295934632BA040B80BCC6C0E6BA6B1720BE25A68A2A72953F3F831013CD4D24B79851BB21DF0BF0792F5B1E74054BC57CB929090908D18D6C46CDCEC7D6EF6F1BB0F12D51F9165644E2EAC7439BF13B5B1A6CD63A4E8FEF82D134CD84948CFE4FBD3C933A2274C80AB80FE902C485803A2607E8B26F516B70F75756F1570A5186BEC38D73C0F6243C77D04ECDD07CBC1";
		String str2 = EncryptUtils.decryptByDES(Constants.DES_KEY, str);
		System.out.println(str2);
	}
}
