package com.base.app.common.api.service;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiReceiveService.java
 * @Description : MIS 수신용
 * @author		: 김성호
 * @since		: 2024.05.27
 * @version		: 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *    수정일            수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2024.05.27   김성호          최초 생성
 */
public interface ApiReceiveService {
	
	public String ReceiveApiJSON(HttpServletRequest param) throws Exception;
	public Map<String, Object> ReceiveApiMap(String JSON) throws Exception;
	public List ReceiveApiList(String JSON) throws Exception;
}
