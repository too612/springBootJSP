package com.base.app.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;


/**
 * 공통 Utility 클래스.
 *
 * @author
 */
public class GeneralUtil {

	public GeneralUtil() {
		super();
		// empty constructor
	}

	/**
	 * paramter가 Null일 경우 문자열 (공백문자)를 리턴
	 */
	public static String nullToString(Object str) {
		//return (str == null) ?"": nullToString((String)str);
		return (str == null) ? "" : nullToString(str.toString());
	}

	/**
	 * <pre>
	 * 객체가 null인 경우 'null'문자열로 출력되는 겻을 방지하기 위해 "" 변환 하며 넘겨온 문자열로 변경하여 반환
	 * </pre>
	 * @param Object   출력대상 객체
	 * @param str	바꿀문자열
	 * @return String  해당 객체에 대한 문자열 표시
	 */
	public static String nullToString(Object o, String str) {
		if (o == null)
			o = "";
		if (o.equals(""))
			o = str;
		return o.toString();
	}

	/**
	 * paramter가 Null일 경우 문자열 (공백문자)를 리턴
	 */
	public static String nullToString(String str) {
		return (str == null) ? "" : str.trim();
	}

	/**
	 * paramter가 Null일 경우 문자열 (공백문자)를 리턴
	 */
	public static String nullToString2(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * paramter가 Null일 경우 문자열 "&nbsp;"을 리턴
	 */
	public static String nullToHtml(String str) {
		return (str == null) ? "&nbsp;" : str;
	}

	/**
	 * getMap의 data Null일 경우 지정된 값으로 put
	 *
	 * @param map
	 * @param str
	 * @param var
	 * @author 문덕찬
	 */
	public static void nullToValue(Map<String, Object> map, String str, String var) {
		if (map.get(str) == null) {
			map.put(str, var);
		}
	}

	/**
	 * paramter가 Null, 빈값일 경우 지정된 값으로 리턴
	 *
	 * @param request
	 * @param str
	 * @param var
	 * @return
	 * @author 문덕찬
	 */
	public static String nullChkData(HttpServletRequest req, String str, String var) {
		if (req.getParameter(str) != null && !req.getParameter(str).equals("")) {
			return req.getParameter(str);
		} else {
			return var;
		}
	}

	/**
	 * String을 int로 변환
	 *
	 * @param String
	 *            str
	 */
	public static int stringToInt(String str) {
		if (str == null || str.trim().length() < 1) {
			return (int) 0;
		} else {
			return Integer.parseInt(str.trim());
		}
	}

	/**
	 * String을 long로 변환
	 *
	 * @param String
	 *            str
	 */
	public static long stringToLong(String str) {
		if (str == null || str.trim().length() < 1) {
			return 0;
		} else {
			return Long.parseLong(str.trim());
		}
	}

	/**
	 * String을 float로 변환
	 *
	 * @param String
	 *            str
	 */
	public static float stringToFloat(String str) {
		if (str == null || str.trim().length() < 1) {
			return 0;
		} else {
			return Float.parseFloat(str.trim());
		}
	}

	/**
	 * String을 double로 변환
	 *
	 * @param String
	 *            str
	 */
	public static double stringToDouble(String str) {
		if (str == null || str.trim().length() < 1) {
			return 0;
		} else {
			return Double.parseDouble(str.trim());
		}
	}

	/**
	 * paramter가 Null일 경우 문자열 (공백문자)를 리턴
	 * 특정특수문자 수정
	 */
	public static String specialFilter(Object str) {
		String value = nullToString(str);

		value = value.replace("&", "&amp;");
		value = value.replace("<", "&lt;");
		value = value.replace(">", "&gt;");
		value = value.replace("\"", "&quot;");
		value = value.replace("'", "&apos;");

		return value;
	}

	/**
	 * 파일명 return
	 *
	 * @param String
	 *            str
	 */
	public static String getFileNms(String[] str) {
		String data = "";
		for (int i = 0; i < str.length; i++) {
			if (!str[i].equals("")) {
				if (i != 0) {
					data = data + ",";
				}
				data = data + str[i].substring(str[i].lastIndexOf("\\") + 1);
			}
		}

		return data;
	}

	/**
	* data1과 data2값을 비교한다. data1이 data2와 같을 시에는 "seleted" 문자를 return한다.
	* @param String
	* @param String
	* @return String
	*/
	public static String selected(String data1, String data2) {
		String rs = null;
		if (data1 == null || data2 == null) {
			rs = "";
		} else if (data2.equals(data1)) {
			rs = "selected";
		}
		return rs;
	}

	/**
	* 파일삭제
	* @param String : 디렉토리명
	* @param String : 파일명
	*/
	public static void delFile(String sDir, String sFileNm) {
		java.io.File fileNm = new java.io.File(sDir, sFileNm);
		fileNm.delete();
	}

	/**
	* data1과 data2값을 비교한다. data1이 data2와 같을 시에는 "checked" 문자를 return한다.
	* @param String
	* @param String
	* @return String
	*/
	public static String checked(String data1, String data2) {
		String rs = null;
		if (data1 == null || data2 == null) {
			rs = "";
		} else if (data2.equals(data1)) {
			rs = "checked";
		}
		return rs;
	}

	/**
	* 주민번호 format(000000-0000000)
	* @return  String
	* @param data
	* @return  String
	*/
	public static String juminFormat(String data) {
		if (data == null) {
			return "";
		} else if (data.trim().length() == 13) {
			return data.substring(0, 6) + "-" + data.substring(6, 13);
		} else {
			return data;
		}
	}

	/**
	* 주민번호 format(000000-0******)
	* @return  String
	* @param data
	* @return  String
	*/
	public static String juminFormatHidden(String data) {
		if (data == null) {
			return "";
		} else if (data.trim().length() == 13) {
			return data.substring(0, 6) + "-" + data.substring(6, 7) + "******";
		} else {
			return data;
		}
	}

	/**
	* 우편번호 format(000-000)
	* @return  String
	* @param data
	* @return  String
	*/
	public static String postFormat(String data) {
		if (data == null) {
			return "";
		} else if (data.trim().length() == 6) {
			return data.substring(0, 3) + "-" + data.substring(3, 6);
		} else {
			return data;
		}
	}

	/**
	* 날짜 format(0000-00-00)
	* @return  String
	* @param data
	* @return  String
	*/
	public static String dateFormat(String data) {
		if (data == null) {
			return "";
		} else if (data.trim().length() == 8) {
			return data.substring(0, 4) + "-" + data.substring(4, 6) + "-" + data.substring(6, 8);
		} else {
			return data;
		}
	}

	/**
	* 날짜 format remove(0000000000000)
	* @param data
	* @return  String
	*/
	public static String dateFormatRemove(String data) {

		if (data == null || data.equals("")) {
			return "";
		} else {
			String temp = "";
			temp = data.substring(0, 4);
			temp += data.substring(5, 7);
			temp += data.substring(8, 10);
			return temp;
		}
	}

	/**
	* 주민번호 format remove(0000000000000)
	* @param data
	* @return  String
	*/
	public static String juminFormatRemove(String data) {
		if (data == null) {
			return "";
		} else {
			String temp = "";
			temp = data.substring(0, 6);
			temp += data.substring(7, 14);
			return temp;
		}
	}

	/**
	* 우편번호 format remove(000000)
	* @param data
	* @return  String
	*/
	public static String postFormatRemove(String data) {
		if (data == null) {
			return "";
		} else {
			String temp = "";
			temp = data.substring(0, 3);
			temp += data.substring(4, 7);
			return temp;
		}
	}

	/**
	* 글자 줄임
	* @param string
	* @param int
	* @return  String
	*/
	public static String parseTitle(String title, int length) {
		if (title == null) {
			return "";
		}
		String tmp = title;
		int slen = 0, blen = 0;
		char c;
		if (tmp.getBytes().length > length) {
			while (blen + 1 < length) {
				c = tmp.charAt(slen);
				blen++;
				slen++;
				if (c > 127) {
					blen++;
				}
			}
			tmp = tmp.substring(0, slen) + "...";
		}
		return tmp;
	}

	/**
	* 달력 색상 설정
	* @param Calendar cal :
	* @param Calendar cur :
	* @return  String
	*/
	public static String getCalColor(Calendar cal, Calendar cur) {
		if (cal.equals(cur))
			return "#f2f2f2";
		else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return "FFF2F2";
		else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return "EBF0FC";
		else
			return "#FFFFFF";
	}

	/**
	* keyWordPoint
	* @param String data : 대상
	* @param String strKeyWord : 검색어
	* @return  String
	*/
	public static String keyWordPoint(String data, String strKeyWord) {
		if ((data == null) || (strKeyWord == null) || strKeyWord.equals(""))
			return null;

		String startBlue = "<font color=blue><b>";
		String endBlue = "</b></font>";

		int su = strKeyWord.length();
		int point1 = 0;//
		int point2 = 0;//

		StringBuffer str = new StringBuffer();

		while ((point2 = data.indexOf(strKeyWord, point1)) != -1) {
			str.append(data.substring(point1, point2));
			str.append(startBlue);
			str.append(strKeyWord);
			str.append(endBlue);
			point1 = point2 + su;
		}

		str.append(data.substring(point1));
		data = str.toString();
		return data;
	}


	/**
	 * replaceAll
	 * @param strSrc
	 * @param strOld
	 * @param strNew
	 * @return
	 */
	public static String replaceAll(String strSrc, String strOld, String strNew) {
		if (strSrc == null) {
			return null;
		}

		StringBuffer strDest = new StringBuffer("");
		int iLen = strOld.length();
		int iSrcLen = strSrc.length();
		int iPos = 0;
		int iOldpos = 0;

		while ((iPos = strSrc.indexOf(strOld, iOldpos)) >= 0) {
			strDest.append(strSrc.substring(iOldpos, iPos));
			strDest.append(strNew);
			iOldpos = iPos + iLen;
		}

		if (iOldpos < iSrcLen) {
			strDest.append(strSrc.substring(iOldpos, iSrcLen));
		}

		return strDest.toString();
	}

	/**
	 * null을 공백으로 치환
	 * @param param
	 * @return
	 */
	public static String null2void(Object param) {
		String str = new String();

		if (param == null) {
			return "";
		}

		if (param instanceof String) {
			str = (String) param;
		} else if (param instanceof String[]) {
			str = ((String[]) param)[0];
		} else if (param instanceof Date) {
			str = ((Date) param).toString();
		} else {
			str = String.valueOf(param);
		}

		if (str.equals("")) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 전각문자를 반각문자로 변환
	 * @param src
	 * @return
	 */
	public static String toHalfChar(String src) {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);
			//영문이거나 특수 문자 일경우.
			if (c >= '！' && c <= '～')
				c -= 0xfee0;
			// 공백
			else if (c == '　')
				c = 0x20;
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 반각문자를 전각문자로 변환하기
	 * @param src
	 * @return
	 */
	public static String toFullChar(String src) {
		if (src == null)
			return null;
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);
			// 영문 알파벳 이거나 특수 문자
			if (c >= 0x21 && c <= 0x7e)
				c += 0xfee0;
			// 공백
			else if (c == 0x20)
				c = 0x3000;
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 문자열 hex처리
	 * @Method Name : stringToHex
	 * @Description :
	 * @param s
	 * @return
	 */
	public static String stringToHex(String s) {
		String result = "";

		for (int i = 0; i < s.length(); i++) {
			result += String.format("%02X ", (int) s.charAt(i));
		}

		return result;
	}

}
