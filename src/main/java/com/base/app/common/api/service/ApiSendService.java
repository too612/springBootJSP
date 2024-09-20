package com.base.app.common.api.service;

import jakarta.servlet.http.HttpServletRequest;


/**
 * @Class Name  : ApiSendService.java
 * @Description : API 발송용
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
public interface ApiSendService {
	public String sendApiList(HttpServletRequest param) throws Exception;
}

