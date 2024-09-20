package com.base.app.common.api.service;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiReceiveService.java
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
public interface ApiReceiveService {
	
	public String ReceiveApiJSON(HttpServletRequest param) throws Exception;
	public Map<String, Object> ReceiveApiMap(String JSON) throws Exception;
	public List<Map<String, Object>> ReceiveApiList(String JSON) throws Exception;
}
