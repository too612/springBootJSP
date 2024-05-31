package com.base.app.common.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.app.common.api.service.ApiSendService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @Class Name  : ApiSendController.java
 * @Description : MIS 발송용
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
public class ApiSendController {

	private final ApiSendService apiSendService;

    @Autowired
    public ApiSendController(ApiSendService apiSendService) {
        this.apiSendService = apiSendService;
	}

	/**
	 * API 요청
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@RequestMapping(value="/mis/pur/purSendAPI/sendApiList.do")
	@ResponseBody
	public String sendApiList(HttpServletRequest param) throws Exception{ 
	
		String result = apiSendService.sendApiList(param);
		
		return result;
	}
	
	
}