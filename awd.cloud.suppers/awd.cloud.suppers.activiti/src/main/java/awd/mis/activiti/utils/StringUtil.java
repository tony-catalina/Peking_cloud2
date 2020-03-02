/*
 * Copyright (c) 2006-2007 by SuyPower
 * All rights reserved.
 */
package awd.mis.activiti.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 描述： 字符串经常使用和操作方法实现的封装类
 * 
 * @author dhchen
 * @version 1.0
 */
public class StringUtil {
	public static final String SLASH = getSlash();
	public static final String BACKSLASH = getBackslash();
	public static final String URL_SLASH = getURLSlash();
	public static final String BASE64_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	private static final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
			5, 8, 4, 2, 1 };
	private static final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
	private static int[] ai = new int[18];

	public StringUtil() {
	}

	/**
	 * @return 返回“/”
	 */
	public static String getSlash() {
		return "/";
	}

	/**
	 * @return 返回“\\”
	 */
	public static String getBackslash() {
		return "\\";
	}

	/**
	 * @return 返回“//”
	 */
	public static String getURLSlash() {
		return "//";
	}

	/**
	 * @return 返回网页的空符号“&nbsp;”
	 */
	public static String getHTMLBlank() {
		return "&nbsp;";
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param string 设置字符串
	 * @return boolean 返回是否为空
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	/**
	 * 判断两个字符串是否值相等
	 * 
	 * @param a 设置第一个字符串
	 * @param b 设置第二个字符串
	 * @return boolean 返回比较的结果
	 */
	public static boolean compare(String a, String b) {
		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equals(b);
		else
			return false;
	}

	/**
	 * 判断两个字符串是否值相等，忽略大小写
	 * 
	 * @param a 设置第一个字符串
	 * @param b 设置第二个字符串
	 * @return boolean 返回比较的结果
	 */
	public static boolean compareIgnoreCase(String a, String b) {
		if (isEmpty(a) && isEmpty(b))
			return true;
		if (!isEmpty(a) && !isEmpty(b))
			return a.equalsIgnoreCase(b);
		else
			return false;
		
		
	}

	/**
	 * 复制字符串中从开始到指定的位置
	 * 
	 * @param src 设置字符串
	 * @param len 指定复制到某个位置
	 * @return String 返回结果
	 */
	public static String copy(String src, int len) {
		if (src == null)
			return null;
		if (src.length() > len)
			return len <= 0 ? null : src.substring(0, len);
		else
			return src;
	}

	/**
	 * 删除字符串中指定的一段字符串内容
	 * 
	 * @param src 设置原字符串
	 * @param delStr 设置需要删除的字符串
	 * @return String 返回结果
	 */
	public static String delete(String src, String delStr) {
		if (isEmpty(src) || isEmpty(delStr))
			return src;
		return src.replace(delStr, "");
		//StringBuffer buffer = new StringBuffer(src);
		//for (int index = src.length(); (index = src.lastIndexOf(delStr, index)) >= 0; index -= delStr
		//		.length())
		//	buffer.delete(index, index + delStr.length());
		//
		//return buffer.toString();
	}

	/**
	 * 将指定的字符和位置插入到原字符串中
	 * 
	 * @param src 设置原字符串
	 * @param anotherStr 设置需要插入的字符串
	 * @param offset 位置
	 * @return String 返回结果
	 */
	public static String insert(String src, String anotherStr, int offset) {
		if (isEmpty(src) || isEmpty(anotherStr))
			return src;
		StringBuffer buffer = new StringBuffer(src);
		if (offset >= 0 && offset <= src.length())
			buffer.insert(offset, anotherStr);
		return buffer.toString();
	}

	/**
	 * 追加指定的字符串到原字符串中
	 * 
	 * @param src 设置原字符串
	 * @param appendStr 设置需要追加的字符串
	 * @return String 返回结果
	 */
	public static String append(String src, String appendStr) {
		if (isEmpty(src) || isEmpty(appendStr)) {
			return src;
		} else {
			StringBuffer buffer = new StringBuffer(src);
			buffer.append(appendStr);
			return buffer.toString();
		}
	}

	/**
	 * 根据参数替换字符串内容功能
	 * 
	 * @param src 设置原字符串
	 * @param oldStr 指定替换字符串
	 * @param newStr 将要替换的内容
	 * @param isCaseSensitive 是否区分大小写
	 * @return String 返回结果
	 */
	public static String replace(String src, String oldStr, String newStr,
			boolean isCaseSensitive) {
		if (isEmpty(src) || isEmpty(oldStr) || newStr == null)
			return src;
		String s = isCaseSensitive ? src : src.toLowerCase();
		String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		for (int index = s.length(); (index = s.lastIndexOf(o, index)) >= 0; index -= o
				.length())
			buffer.replace(index, index + o.length(), newStr);

		return buffer.toString();
	}

	/**
	 * 根据参数替换字符串内容功能，可指定位置
	 * 
	 * @param src 设置原字符串
	 * @param oldStr 指定替换字符串
	 * @param newStr 将要替换的内容
	 * @param isCaseSensitive 是否区分大小写
	 * @param index 指定位置
	 * @return String 返回结果
	 */
	public static String replace(String src, String oldStr, String newStr,
			boolean isCaseSensitive, int index) {
		if (src == null || src.length() == 0 || oldStr == null
				|| oldStr.length() == 0 || index <= 0)
			return src;
		if (newStr == null)
			newStr = "";
		String s = isCaseSensitive ? src : src.toLowerCase();
		String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		int length = o.length();
		int pos;
		for (pos = s.indexOf(o, 0); pos >= 0; pos = s.indexOf(o, pos + length))
			if (--index == 0)
				break;

		if (pos >= 0 && index == 0)
			buffer.replace(pos, pos + length, newStr);
		return buffer.toString();
	}

	/**
	 * 获取指定符号分割的字符串数组
	 * 
	 * @param str 设置原字符串
	 * @param delimiter 设置分割符号
	 * @return String[] 返回字符串数组
	 */
	public static String[] split(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0;
		String result[] = new String[0];
		if (isEmpty(str))
			return result;
		do {
			index = str.indexOf(delimiter, begin);
			if (index == begin) {
				if (index >= 0)
					array.add("");
				begin += delimiter.length();
				continue;
			}
			if (index <= begin)
				break;
			int end = index;
			array.add(str.substring(begin, end));
			begin = index + delimiter.length();
		} while (true);
		if (begin >= 0 && begin < str.length())
			array.add(str.substring(begin));
		if (str.endsWith(delimiter))
			array.add("");
		if (array.size() > 0) {
			result = new String[array.size()];
			array.toArray(result);
		}
		return result;
	}

	/**
	 * 获取指定符号分割的字符串数组，非空
	 * 
	 * @param str 设置原字符串
	 * @param delimiter 设置分割符号
	 * @return String[] 返回字符串数组
	 */
	public static String[] splitWithoutEmpty(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0;
		String result[] = new String[0];
		if (isEmpty(str))
			return new String[0];
		do {
			index = str.indexOf(delimiter, begin);
			if (index == begin) {
				if (index > 0)
					array.add("");
				begin += delimiter.length();
				continue;
			}
			if (index <= begin)
				break;
			int end = index;
			array.add(str.substring(begin, end));
			begin = index + delimiter.length();
		} while (true);
		if (begin >= 0 && begin < str.length())
			array.add(str.substring(begin));
		if (array.size() > 0) {
			result = new String[array.size()];
			array.toArray(result);
		}
		return result;
	}

	/**
	 * 将指定字符串内容反转
	 * 
	 * @param str 设置原字符串
	 * @return String 返回字符串
	 */
	public static String reverse(String str) {
		if (isEmpty(str)) {
			return str;
		} else {
			StringBuffer buffer = new StringBuffer(str);
			buffer.reverse();
			return buffer.toString();
		}
	}

	/**
	 * 给传入的字符串前后加双引号
	 * 
	 * @param str 设置原字符串
	 * @return String 返回结果
	 */
	public static String quote(String str) {
		if (isEmpty(str))
			return "\"\"";
		StringBuffer buffer = new StringBuffer(str);
		if (!str.startsWith("\""))
			buffer.insert(0, "\"");
		if (!str.endsWith("\""))
			buffer.append("\"");
		return buffer.toString();
	}

	/**
	 * 去除字符串中的双引号
	 * 
	 * @param str 设置原字符串
	 * @return String 返回结果
	 */
	public static String extractQuotedStr(String str) {
		if (isEmpty(str))
			return str;
		StringBuffer buffer = new StringBuffer(str);
		int index = str.length();
		while (buffer.charAt(buffer.length() - 1) == '"') {
			buffer.deleteCharAt(buffer.length() - 1);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		if (buffer.length() == 0)
			return "";
		while (buffer.charAt(0) == '"') {
			buffer.deleteCharAt(0);
			index = buffer.length();
			if (index <= 0)
				break;
		}
		return buffer.toString();
	}

	/**
	 * 截取字符串中到指定的字符的内容，从左边开始
	 * 
	 * @param str 设置原字符串
	 * @param c 设置指定字符
	 * @return String 返回结果
	 */
	public static String strChar(String str, char c) {
		if (str == null || str.length() == 0)
			return null;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == c)
				return str.substring(i);

		return null;
	}

	/**
	 * 截取字符串中到指定的字符的内容，从右边开始
	 * 
	 * @param str
	 *            设置原字符串
	 * @param c
	 *            设置指定字符
	 * @return String 返回结果
	 */
	public static String strRChar(String str, char c) {
		if (str == null || str.length() == 0)
			return null;
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.charAt(i) == c)
				return str.substring(i);

		return null;
	}

	/**
	 * 将Object对象数组转成字符串数组
	 * 
	 * @param array
	 *            设置Object对象数组
	 * @return String[] 返回结果
	 */
	public static String[] toArray(Object array[]) {
		if (array == null || array.length == 0)
			return null;
		String result[] = new String[array.length];
		for (int i = 0; i < array.length; i++)
			if (array[i] != null)
				result[i] = array[i].toString();

		return result;
	}

	/**
	 * 将Vector对象数组元素转成字符串数组
	 * 
	 * @param list 设置Vector对象数组
	 * @return String[] 返回结果
	 */
	public static String[] toArray(Vector list) {
		if (list == null || list.size() == 0)
			return null;
		String result[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (obj != null)
				result[i] = list.get(i).toString();
		}

		return result;
	}

	/**
	 * 将字符串数组复制到LIST中
	 * 
	 * @param array 设置字符串数组
	 * @param list 设置LIST集合对象
	 * @param index 设置复制到LIST位置
	 * @return List 返回结果
	 */
	public static List copyToList(String array[], List list, int index) {
		if (array == null || array.length == 0)
			return list;
		if (list == null || index < 0)
			return list;
		for (int i = 0; i < array.length; i++)
			if (list.size() <= i + index)
				list.add(index + i, array[i]);
			else
				list.set(index + i, array[i]);

		return list;
	}

	/**
	 * 判断字符串数组是否包含指定字符串
	 * 
	 * @param array 设置字符串数组
	 * @param str 设置批量字符串
	 * @return boolean 返回结果
	 */
	public static boolean contains(Object array[], String str) {
		if (array == null || array.length == 0)
			return false;
		if (str == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			Object obj = array[i];
			if (obj != null && str.equals(obj.toString()))
				return true;
		}

		return false;
	}

	/**
	 * 获取字符串数组包含指定字符串的位置
	 * 
	 * @param array 设置字符串数组
	 * @param str 设置批量字符串
	 * @return int 返回结果
	 */
	public static int indexOf(Object array[], String str) {
		if (array == null || array.length == 0)
			return -1;
		if (str == null)
			return -1;
		for (int i = 0; i < array.length; i++) {
			Object obj = array[i];
			if (obj != null && str.equals(obj.toString()))
				return i;
		}

		return -1;
	}

	/**
	 * 验证是否为电子邮件格式
	 * 
	 * @param theEmail 设置电子邮件地址字符串
	 * @return boolean 返回是否合法
	 */
	public static boolean isValidEmail(String theEmail) {
		boolean cbool = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(theEmail);
			boolean isMatched = matcher.matches();
			if (isMatched) {
				cbool = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return cbool;
		}
		return cbool;
	}

	/**
	 * 去除字符串左边空格
	 * 
	 * @param str 设置原字符串
	 * @return String 返回结果
	 */
	public static String trimLeft(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		return str.replaceAll("^[ ]+", "");
		//StringBuffer buffer = new StringBuffer(str);
		//int index;
		//for (index = 0; index < length && buffer.charAt(index) == ' '; index++)
		//	  ;
		//if (index == length)
		//	  return "";
		//else
		//	  return buffer.substring(index);
	}

	/**
	 * 去除字符串右边空格
	 * 
	 * @param str 设置原字符串
	 * @return String 返回结果
	 */
	public static String trimRight(String str) {
		if (str == null)
			return null;
		int length = str.length();
		if (length == 0)
			return "";
		return str.replaceAll("[ ]+$", "");
		//StringBuffer buffer = new StringBuffer(str);
		//int index;
		//for (index = length - 1; index >= 0 && buffer.charAt(index) == ' '; index--)
		//	  ;
		//if (index < 0 && buffer.charAt(0) == ' ')
		//	  return "";
		//else
		//	  return buffer.substring(0, index + 1);
	}

	/**
	 * 去除字符串两边空格
	 * 
	 * @param str 设置原字符串
	 * @return String 返回结果
	 */
	public static String trimAll(String str) {
		return str.trim();
	}

	/**
	 * 去除字符串NULL
	 * 
	 * @param str 设置原字符串
	 * @return String 如果为NULL返回空字符串，否则返回原字符串
	 */
	public static String removeNull(String str) {
		return str != null ? str : "";
	}
	
	/**
	 * 去除对象NULL
	 * 
	 * @param str 设置原字符串
	 * @return String 如果为NULL返回空字符串，否则返回原字符串
	 */
	public static String removeNull(Object str) {
		return str != null ? str.toString() : "";
	}

	/**
	 * 实现判断字符串是否在数组中存在
	 * 
	 * @param strs
	 *            设置字符串数组
	 * @param str
	 *            设置要查找的字符串
	 * @param caseSensitive
	 *            设置是否区分大小写
	 * @return boolean 返回结果
	 */
	public static boolean strInArray(String strs[], String str,
			boolean caseSensitive) {
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				if (caseSensitive) {
					if (strs[i].equals(str))
						return true;
				} else {
					if (strs[i].equalsIgnoreCase(str))
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * 验证身份证的合法性
	 * 
	 * @param idcard
	 *            设置身份证字符串
	 * @return boolean 返回结果
	 */
	public static boolean idCardVerify(String idcard) {
		if (idcard.length() == 15) {
			idcard = idCardUptoeighteen(idcard);
		}
		if (idcard.length() != 18) {
			return false;
		}
		String verify = idcard.substring(17, 18);
		if (verify.equals(getIdCardVerify(idcard))) {
			return true;
		}
		return false;
	}

	/**
	 * 获得身份证的合法性
	 * 
	 * @param eightcardid
	 *            设置身份证字符串
	 * @return String 返回结果
	 */
	public static String getIdCardVerify(String eightcardid) {
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}

	/**
	 * 获得身份证15转18位
	 * 
	 * @param fifteencardid
	 *            设置身份证字符串
	 * @return String 返回结果
	 */
	public static String idCardUptoeighteen(String fifteencardid) {
		if (fifteencardid.length() != 15)
			return null;
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6, 15);
		eightcardid = eightcardid + getIdCardVerify(eightcardid);
		return eightcardid;
	}

	/**
	 * 验证电话号码合法格式，格式为02584555112
	 * 
	 * @param phoneCode
	 *            设置电话号码字符串
	 * @return boolean 返回结果
	 */
	public static boolean isPhoneNum(String phoneCode) {
		Pattern p = Pattern.compile("[0][1-9]{2,3}[1-9]{6,8}");
		Matcher m = p.matcher(phoneCode);
		boolean b = m.matches();
		return b;
	}
	
	/**
	 * 字符数组转换为字符串,用逗号隔开
	 * @author 提出人：沈军 2008年5月9日
	 * @param str
	 * @return
	 */
	public static String arrayToString(final String[] str) {
		if (str == null)
			return "";
		StringBuffer rStr = new StringBuffer("");
		for (int i = 0; i < str.length; i++) {
			rStr.append(str[i]);
			rStr.append(",");
		}
		// 截取逗号
		if (rStr.toString().length() > 0) {
			rStr.setLength(rStr.length() - 1);
		}
		return rStr.toString();
	}
	
	/**
	 * 判定一个对象是否为null or empty
	 * @author 提出人：沈军 2008年5月10日
	 * @param o
	 *            对象
	 * @return true or false
	 */
	public static boolean isNullOrEmpty(Object o) {
		return o == null || String.valueOf(o).trim().length() == 0;
	}

	/**
	 * 不为空或未定义
	 * @param o
	 * @return
     */
	public static boolean isNotNullOrEmpty(Object o) {
		return !isNullOrEmpty(o);
	}
	
	
	/**
	 * 将java对象序列化为一个String
	 * @throws IOException 
	 */
	public static String writeObject2String(Object obj) throws IOException{
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		String serStr = null;
		ObjectOutputStream os = null;
		os = new ObjectOutputStream(bs);
		os.writeObject(obj);
		serStr = bs.toString("ISO-8859-1");
		serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
		return serStr;
		
	}
	
	/**
	 * 将字符串反序列化为java对象
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object deserializeFromString(String serStr) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		String deserStr = java.net.URLDecoder.decode(serStr, "UTF-8");
		bis = new ByteArrayInputStream(deserStr.getBytes("ISO-8859-1"));
		ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
	

	
	
	
}
