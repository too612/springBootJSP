package com.base.app.common.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.app.common.api.service.ApiReceiveService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * @Class Name  : ApiReceiveController.java
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
@Controller
@RequiredArgsConstructor
public class ApiReceiveController {

	private final ApiReceiveService apiReceiveService;

	/**
	 * API ��û�� ���� ��� ����� ��ȯ
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@RequestMapping(value="/common/api/ApiReceive/ReceiveApiJSON.do")
	@ResponseBody
	public String ReceiveApiJSON(HttpServletRequest param) throws Exception{ 
		
//		SetBackEndRequest tempRequest = new SetBackEndRequest(param);
//		tempRequest.setParameter("SCH_API_TYPE", "USR");
//		tempRequest.setParameter("SCH_USER_NAME", "���");
//		param = (HttpServletRequest)tempRequest;
//		
		String result = apiReceiveService.ReceiveApiJSON(param);
		
		return result;
	}
}