package com.base.app.common.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.base.app.common.api.service.ApiReceiveService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiReceiveServiceImpl.java
 * @Description : MIS ���ſ�
 * @author		: �輺ȣ
 * @since		: 2024.05.27
 * @version		: 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *    ������            ������          ��������
 *  ----------  --------  ---------------------------
 *  2024.05.27   �輺ȣ          ���� ����
 */
@Service("common.api.service.ApiReceiveService")
public class ApiReceiveServiceImpl implements ApiReceiveService{
	
	//private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * API ��û�� ���� ��� ����� ��ȯ
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@Override
	public String ReceiveApiJSON(HttpServletRequest param) throws Exception{
		
		
		HashMap<String, Object> map = convertMap(param);
		String result = "";
		
		String str = String.valueOf(map.get("searchKey"));
		JSONParser jsonParser = new JSONParser();
		JSONObject jObject = (JSONObject) jsonParser.parse(str);
		
		List<Map<String, Object>> list = null;
		
		JSONObject headData = new JSONObject();
		JSONObject bodyData = new JSONObject();
		JSONArray jList = new JSONArray();
		Map<String, Object> sMap = new HashMap<String, Object>();
		JSONObject fullJSONObject = new JSONObject(new TreeMap());
		
		try {
			// �λ����� ��Ű�� 
			if ("USR".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				// �˻�����
				sMap.put("SCH_EMP_NO", nullToString(String.valueOf(jObject.get("SCH_IDENTIFT_KEY"))));
				sMap.put("SCH_JOB_POST_NM", nullToString(String.valueOf(jObject.get("SCH_USER_PERSG"))));
				sMap.put("SCH_EMP_NM", nullToString(String.valueOf(jObject.get("SCH_USER_NAME"))));
				sMap.put("SCH_OFFC_TEL_NO", nullToString(String.valueOf(jObject.get("SCH_USER_PHONE_NUM"))));
				sMap.put("SCH_CELL_PON_NO", nullToString(String.valueOf(jObject.get("SCH_USER_MOBILE_NUM"))));
				sMap.put("SCH_EMAIL_ADDR", nullToString(String.valueOf(jObject.get("SCH_USER_EMAIL"))));
				sMap.put("SCH_ENT_DT", nullToString(String.valueOf(jObject.get("SCH_USER_REG_DATE"))));
				sMap.put("SCH_SEX_DIST", nullToString(String.valueOf(jObject.get("SCH_USER_GENDER"))));
				
				//list =	dao.list("mis.pur.PurReceiveAPI.UsrList", sMap);
			// �μ����� ��Ű��
			} else if ("DPT".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				// �˻�����
				sMap.put("SCH_DEPT_CD", nullToString(String.valueOf(jObject.get("SCH_GROUP_CODE"))));
				sMap.put("SCH_DEPT_NM", nullToString(String.valueOf(jObject.get("SCH_GROUP_NAME"))));
				sMap.put("SCH_UP_DEPT_CD", nullToString(String.valueOf(jObject.get("SCH_GROUP_CODE_UP"))));
				sMap.put("SCH_LEV", nullToString(String.valueOf(jObject.get("SCH_GROUP_DEPTH"))));
				sMap.put("SCH_ORD_NO", nullToString(String.valueOf(jObject.get("SCH_GROUP_ORDER"))));
				
				//list =	dao.list("mis.pur.PurReceiveAPI.DptList", sMap);
			// �������� ��Ű��
			} else if ("CST".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				// �˻�����
				sMap.put("SCH_BIZR_NO", nullToString(String.valueOf(jObject.get("SCH_BUSINESS_NUM"))));
				sMap.put("SCH_RQST_NO", nullToString(String.valueOf(jObject.get("SCH_ORDER_NO"))));
				sMap.put("SCH_RQST_EMP_NO", nullToString(String.valueOf(jObject.get("SCH_IDENTIFT_KEY"))));
				sMap.put("SCH_RQST_SBJ", nullToString(String.valueOf(jObject.get("SCH_CONSTR_NAME"))));
				sMap.put("SCH_DLVERY_SDT", nullToString(String.valueOf(jObject.get("SCH_CONSTR_START_DATE"))));
				sMap.put("SCH_DLVERY_EDT", nullToString(String.valueOf(jObject.get("SCH_CONSTR_END_DATE"))));
				sMap.put("SCH_TOT_RQST_AMT", nullToString(String.valueOf(jObject.get("SCH_CONSTR_AMOUNT"))));
				sMap.put("SCH_EVL_DMND_YN", nullToString(String.valueOf(jObject.get("SCH_IS_SH_LEVEL"))));
				
				//list =	dao.list("mis.pur.PurReceiveAPI.CstList", sMap);
			// ��ü���� ��Ű��
			} else if ("COP".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				// �˻�����
				sMap.put("SCH_CTRCT_NO", nullToString(String.valueOf(jObject.get("SCH_ORDER_NO"))));
				sMap.put("SCH_BIZR_NO", nullToString(String.valueOf(jObject.get("SCH_BUSINESS_NUM"))));
				sMap.put("SCH_CTRCT_CUST_NM", nullToString(String.valueOf(jObject.get("SCH_COMPANY_NANE"))));
				sMap.put("SCH_CTRCT_CUST_MNG_NM", nullToString(String.valueOf(jObject.get("SCH_REP_NAME"))));
				sMap.put("SCH_CTRCT_CUST_ADDR", nullToString(String.valueOf(jObject.get("SCH_ADDRESS"))));
				sMap.put("SCH_CTRCT_CUST_TEL_NO", nullToString(String.valueOf(jObject.get("SCH_COMPANY_PHONE"))));
				
				//list =	dao.list("mis.pur.PurReceiveAPI.CopList", sMap);
			// ȭ�й��� ��Ű��
			} else if ("CMS".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				// �˻�����
				sMap.put("SCH_RQST_NO", nullToString(String.valueOf(jObject.get("SCH_ORDER_NO"))));
				sMap.put("SCH_CMS_NO", nullToString(String.valueOf(jObject.get("SCH_CMS_NO"))));
				sMap.put("SCH_LMS_NO", nullToString(String.valueOf(jObject.get("SCH_LMS_NO"))));
				sMap.put("SCH_QTY", nullToString(String.valueOf(jObject.get("SCH_QTY"))));
				sMap.put("SCH_BUY_DATE", nullToString(String.valueOf(jObject.get("SCH_BUY_DATE"))));
				sMap.put("SCH_IDENTIFT_KEY", nullToString(String.valueOf(jObject.get("SCH_IDENTIFT_KEY"))));
				
				//list =	dao.list("mis.pur.PurReceiveAPI.CmsList", sMap);
			} else if ("FILE".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				sMap.put("FILE_BLOB", jObject.get("FILE_BLOB"));
				
				headData.put("mesg", "Successful");
				
				
				bodyData.put("totalCount", 1);
				bodyData.put("FILE_INFO", sMap.get("FILE_BLOB"));
				
				fullJSONObject.put("head", headData);
				fullJSONObject.put("body", bodyData);
				
				// Base64->byte�� ����
				//byte[] decode =Base64.decodeBase64(String.valueOf(sMap.get("FILE_BLOB")).getBytes());
				
				// ������ ��η� ������ ����
				//Files.write(Paths.get("C:\\Users\\msi\\Desktop\\test2.pdf"), decode);
			
			}
			
			// ���ϰ��� �κ��� �����ϰ� json ���� ����
			if (!"FILE".equals(String.valueOf(jObject.get("SCH_API_TYPE")))) {
				headData.put("code", "200");
				headData.put("mesg", "Successful");
				for (int i = 0; i < list.size(); i++) {
					Map gmap = (Map) list.get(i);
					JSONObject data = new JSONObject(gmap);
					jList.add(data);
				}
				bodyData.put("count", list.size());
				bodyData.put("list", jList);
				
				fullJSONObject.put("head", headData);
				fullJSONObject.put("body", bodyData);
			}
				
			
		} catch (Exception e) {
			headData.put("code", "500");
			headData.put("mesg", "Failed : " + e.toString());
			fullJSONObject.put("head", headData);
			
			bodyData.put("count", 0);
			bodyData.put("list", "");
			fullJSONObject.put("body", bodyData);
		}
		
		
		result = fullJSONObject.toString();
		System.out.println("receive--->" + result);
		return result;
	}
	
	/**
	 * JSON -> MAP ��ȯ
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	public Map<String, Object> ReceiveApiMap(String JSON) throws ParseException {
		
		Map<String, Object> map = null;
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(JSON);
		JSONObject body = (JSONObject) jsonObj.get("head");
		
		map = getMapFromJsonObject(body);
		
		return map;
	}
	
	/**
	 * JSON -> LIST ��ȯ
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	public List<Map<String, Object>> ReceiveApiList(String JSON) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(JSON);
		JSONObject body = (JSONObject) jsonObj.get("body");
		List<Map<String, Object>> list = null;
		
		int totalCnt = Integer.valueOf(String.valueOf(body.get("count")));
		
		JSONArray jsonArray = (JSONArray) body.get("list");
		
		list = separateListFrmJSONArrayToList(jsonArray);
		
		for (int i = 0; i < totalCnt; i++) {
			Map<String, Object> map = new HashMap<String,Object>();
			
			JSONObject dataObj = (JSONObject) jsonArray.get(i);
			
			map.put("ROW_CNT", dataObj.get("ROW_CNT"));
		}
		
		return list;
	}

	/**
	 * JsonArray���� List�� ����ȯ�ϱ�
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> separateListFrmJSONArrayToList (JSONArray jSongArray) throws ParseException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < jSongArray.size(); i++){   //������ hashmap �������� ������ŭ �ݺ�
			Map<String, Object> map = getMapFromJsonObject((JSONObject)jSongArray.get(i));
			
			list.add(map);
		}
		return list;
	}
	
	/**
	 * JsonObject���� Map���� ����ȯ�ϱ�
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getMapFromJsonObject(JSONObject jsonObject) { 
		Map<String, Object> map = null;
		try {
			map = new ObjectMapper().readValue(jsonObject.toJSONString(), Map.class);
		} catch (JsonParseException e) {
			System.out.println("Failed to JsonParseException : " + e);
		} catch (JsonMappingException e) {
			System.out.println("Failed to JsonMappingException : " + e);
		} catch (IOException e) {
			System.out.println("Failed to IOException : " + e);
		}
		return map;
	}
	
	
	/**
	 * HttpServletRequest map���� ��ȯ
	 * @param InputStream in : InputStream
	 * @return hmap
	 */
	public HashMap<String, Object> convertMap(HttpServletRequest request) {
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		String key;
		Enumeration<?> enumList = request.getParameterNames();

		while (enumList.hasMoreElements()) {
			key = (String) enumList.nextElement();
			if (request.getParameterValues(key).length > 1) {
				hmap.put(key, request.getParameterValues(key));
			} else {
				hmap.put(key, request.getParameter(key));
			}
		}
		return hmap;
	}
	
	/**
	 * null�� ġȯ
	 * @param String str : String
	 * @return String
	 */
	public static String nullToString(String str) {
		return (str == "null") ? "" : str.trim();
	}
}
