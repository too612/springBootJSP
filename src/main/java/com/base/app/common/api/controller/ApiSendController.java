package com.base.app.common.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.app.common.api.service.ApiSendService;
import com.base.app.common.util.SetBackEndRequest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * @Class Name  : ApiSendController.java
 * @Description : MIS �߼ۿ�
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
public class ApiSendController {

	private final ApiSendService apiSendService;

	/**
	 * API ��û
	 * @param HttpServletRequest param : parameter
	 * @return String
	 */
	@RequestMapping(value="/common/api/ApiSend/sendApiList.do")
	@ResponseBody
	public String sendApiList(HttpServletRequest param) throws Exception{ 
	
		SetBackEndRequest tempRequest = new SetBackEndRequest(param);
		tempRequest.setParameter("SCH_API_TYPE", "CMS");
		tempRequest.setParameter("SCH_ORDER_NO", "P12-240603-001");
		param = (HttpServletRequest)tempRequest;
		String result = apiSendService.sendApiList(param);
		
		return result;
	}
	
	
}