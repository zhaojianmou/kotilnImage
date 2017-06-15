package com.kele.androidstudio.kotlinimage.utils.constant;



import java.io.UnsupportedEncodingException;

import android.util.Base64;

public class Base64Utils {
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String setEncrypt(String str) {
		String temp = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
		if (temp != null && temp.trim().length() >= 4) {
			temp = temp.replace("\n", "");
			String bf = temp.substring(0, 2);
			String ct = temp.substring(2, temp.length() - 2);
			String bh = temp.substring(temp.length() - 2);

			temp = bh + ct + bf;

			String hash = "";
			char[] arr = temp.toCharArray();
			for (int i = 0; i < arr.length; i++)
				hash = hash + (char) (arr[i] << 1);
			return hash;
		}
		return temp;
	}

	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public static String setDecrypt(String str) {
		if (str != null && str.trim().length() >= 4) {
			String hash = "";
			char[] arg = str.toCharArray();
			for (int i = 0; i < arg.length; i++)
				hash = hash + (char) (arg[i] >> 1);
			String bf = hash.substring(0, 2);
			String ct = hash.substring(2, hash.length() - 2);
			String bh = hash.substring(hash.length() - 2);

			hash = bh + ct + bf;

			byte[] arr = Base64.decode(hash, Base64.DEFAULT);
			String temp = null;
			try {
				temp = new String(arr, "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return temp;
		}
		return "";
	}
}
