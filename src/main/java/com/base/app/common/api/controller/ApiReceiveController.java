package com.base.app.common.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.app.common.api.service.ApiReceiveService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @Class Name  : ApiReceiveController.java
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
@Controller
public class ApiReceiveController {

	private final ApiReceiveService apiReceiveService;

    @Autowired
    public ApiReceiveController(ApiReceiveService apiReceiveService) {
        this.apiReceiveService = apiReceiveService;
	}
	
	/**
	 * API 요청이 들어올 경우 결과값 반환
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@RequestMapping(value="/common/api/ApiReceive/ReceiveApiJSON.do")
	@ResponseBody
	public String ReceiveApiJSON(HttpServletRequest param) throws Exception{ 
		
//		SetBackEndRequest tempRequest = new SetBackEndRequest(param);
//		tempRequest.setParameter("SCH_API_TYPE", "USR");
//		tempRequest.setParameter("SCH_USER_NAME", "김경");
//		param = (HttpServletRequest)tempRequest;
//		
		String result = apiReceiveService.ReceiveApiJSON(param);
		
		return result;
	}
}