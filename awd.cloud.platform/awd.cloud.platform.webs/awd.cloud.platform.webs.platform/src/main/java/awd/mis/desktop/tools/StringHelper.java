package awd.mis.desktop.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
public class StringHelper {

	public static String emptyIf(String value, String defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return value;
	}

	public static String makeAllWordFirstLetterUpperCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1) {
				result += strs[i];
			} else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}

	public static String makeAllWordLowerCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (preStr.length() == 1) {
				result += strs[i];
			} else {
				result += strs[i];
			}
			preStr = strs[i];
		}
		return result;
	}

	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/** copy from spring */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/** copy from spring */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	/** copy from spring */
	private static String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	private static final Random RANDOM = new Random();

	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	public static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers) {
		return random(count, start, end, letters, numbers, null, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char[] chars, Random random) {
		if (count == 0) {
			return "";
		} else if (count < 0) {
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}
		if ((start == 0) && (end == 0)) {
			end = 'z' + 1;
			start = ' ';
			if (!letters && !numbers) {
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0) {
			char ch;
			if (chars == null) {
				ch = (char) (random.nextInt(gap) + start);
			} else {
				ch = chars[random.nextInt(gap) + start];
			}
			if ((letters && Character.isLetter(ch))
					|| (numbers && Character.isDigit(ch))
					|| (!letters && !numbers)) {
				if (ch >= 56320 && ch <= 57343) {
					if (count == 0) {
						count++;
					} else {
						// low surrogate, insert high surrogate after putting it
						// in
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				} else if (ch >= 55296 && ch <= 56191) {
					if (count == 0) {
						count++;
					} else {
						// high surrogate, insert low surrogate before putting
						// it in
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				} else if (ch >= 56192 && ch <= 56319) {
					// private high surrogate, no effing clue, so skip it
					count++;
				} else {
					buffer[count] = ch;
				}
			} else {
				count++;
			}
		}
		return new String(buffer);
	}

	/**
	 * Convert a name in camelCase to an underscored name in lower case. Any
	 * upper case letters are converted to lower case with a preceding
	 * underscore.
	 * 
	 * @param filteredName
	 *            the string containing original name
	 * @return the converted name
	 */
	public static String toUnderscoreName(String name) {
		if (name == null)
			return null;

		String filteredName = name;
		if (filteredName.indexOf("_") >= 0
				&& filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}
		if (filteredName.indexOf("_") == -1
				&& filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}

		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preString = filteredName.substring(i - 1, i);
				String s = filteredName.substring(i, i + 1);
				if (s.equals("_")) {
					result.append("_");
					continue;
				}
				if (preString.equals("_")) {
					result.append(s.toLowerCase());
					continue;
				}
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toLowerCase());
				} else {
					result.append(s);
				}
			}
		}
		return result.toString();
	}

	/**
	 * @author 杨书江
	 * @since 2009年7月14日14:07:55
	 * 
	 *        验证一个字符串是否为0-9数字组成的
	 * @param str
	 *            验证的字符串
	 * @return 是返回 true 不是返回 false
	 */
	public static boolean isNumber(String str) {
		boolean isNum = false;
		if (str != null && str.trim().matches("^[0-9]+$")) {
			isNum = true;
		}
		return isNum;
	}

	/**
	 * @author 杨书江
	 * @since 2009年7月14日14:07:55
	 * 
	 *        验证一个字符串是否为a-zA-z英文字母组成的
	 * @param str
	 *            验证的字符串
	 * @return 是返回 true 不是返回 false
	 */
	public static boolean isEnglishChars(String str) {
		boolean isChar = false;
		if (str != null && str.trim().matches("^[a-zA-Z]+$")) {
			isChar = true;
		}
		return isChar;
	}

	/**
	 * @author 杨书江
	 * @since 2009年7月14日14:07:55
	 * 
	 *        验证一个字符串是否为汉字组成的
	 * @param str
	 *            验证的字符串
	 * @return 是返回 true 不是返回 false
	 */
	public static boolean isChinese(String str) {
		boolean isChinese = false;
		if (str != null && str.trim().matches("^[\u4e00-\u9fa5]+$")) {
			isChinese = true;
		}
		return isChinese;
	}

	/**
	 * @author 杨书江
	 * @since 2009年8月5日10:33:43
	 * 
	 *        判断字符串是否为空，或者NULL
	 * @param str
	 * @return 是空返回true 不是false
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.trim().equals("") || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @author 杨书江
	 * @since 2011年5月28日 14:48:43
	 * 
	 *        判断字符串是否不为空，并且不是NULL
	 * @param str
	 * @return 是空返回true 不是false
	 */
	public static boolean isNotBlank(String str) {
		if (str == null || str.trim().equals("") || str.length() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * @author 杨书江
	 * @since 2009年8月5日10:33:43
	 * 
	 *        用于21位编号值+1 例如：...0001 >> ...0002
	 * @param str
	 * @return
	 */
	public static String plusOne(String str) {
		String rslt = "";
		try {
			rslt = str.substring(0, 15)
					+ lpad((Integer.parseInt(str.substring(15)) + 1) + "", 6,
							"0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rslt;
	}

	/**
	 * @author 杨书江
	 * @since 2009年8月24日22:44:27
	 * 
	 *        字符串左填充 * eg. lpad('abc',5,'0') >> '00abc'
	 * @param opStr
	 *            操作的字符串
	 * @param length
	 *            指定长度
	 * @param pad
	 *            填充符
	 * @return
	 */
	public static String lpad(String opStr, int length, String pad) {
		String result = "";
		String padStr = "";
		if (isBlank(opStr)) {
			opStr = "";
		}
		if (isBlank(pad)) {
			pad = "0";
		}
		while (length >= 0 && pad.length() <= length) {
			padStr += pad;
			length = length - pad.length();
		}
		result = opStr;
		if (padStr.length() > opStr.length()) {
			result = padStr.substring(0, padStr.length() - opStr.length())
					+ opStr;
		}
		return result;
	}

	public static void main(String args[]) {
		String str = "310000111" + "20091020" + "0028";
		String rslt = str.substring(0, 15)
				+ lpad((Integer.parseInt(str.substring(15)) + 1) + "", 6, "0");
		System.out.println(rslt);
		System.out.println((Integer.parseInt(str.substring(15)) + 1) + "");

	}

	/**
	 * @author 杨书江
	 * @since 2009年8月24日22:44:27
	 * 
	 *        字符串右填充 eg. rpad('abc',5,'0') >> 'abc00'
	 * @param opStr
	 *            操作的字符串
	 * @param length
	 *            指定长度
	 * @param pad
	 *            填充符
	 * @return
	 */
	public static String rpad(String opStr, int length, String pad) {
		String result = "";
		String padStr = "";

		if (isBlank(opStr)) {
			opStr = "";
		}
		if (isBlank(pad)) {
			pad = "0";
		}
		while (length >= 0 && pad.length() <= length) {
			padStr += pad;
			length = length - pad.length();
		}
		if (padStr.length() > opStr.length()) {
			result = padStr.substring(0, padStr.length() - opStr.length())
					+ opStr;
		}
		return result;
	}

	/**
	 * Object转String
	 * 
	 * @param o
	 * @return
	 */
	public static String obj2Str(Object o) {
		return o == null ? "" : o.toString();
	}

	/**
	 * Return the extension portion of the file's name . 2010年11月29日15:46:13 by
	 * watchdb
	 * 
	 * @see #getExtension
	 */
	public static String getExtension(File f) {
		return (f != null) ? getExtension(f.getName()) : "";
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename) {
		return getExtension(filename, "");
	}

	/**
	 * 获取文件名称，去除后缀名后的
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileName(String filename) {
		String name = "";
		if ((filename != null) && (filename.length() > 0)) {
			return filename.substring(0, filename.lastIndexOf('.') - 1);
		}
		return name;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 *            文件名称
	 * @param defExt
	 *            默认名称
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}

	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}

	/**
	 * 将文件转码为二进制
	 * 
	 * @param f
	 *            文件
	 * @return
	 */
	public static byte[] encodeFile(File f) {
		byte[] bt = null;
		BufferedInputStream bns;
		try {
			bns = new BufferedInputStream(new FileInputStream(f));
			int bufferSize = (int) f.length();// 取得BLOB的长度
			bt = new byte[bufferSize];
			bns.read(bt, 0, bufferSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}
}
