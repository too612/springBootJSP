package com.base.app.common.api.service;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiSendService.java
 * @Description : API �߼ۿ�
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
public interface ApiSendService {
	public String sendApiList(HttpServletRequest param) throws Exception;
}

