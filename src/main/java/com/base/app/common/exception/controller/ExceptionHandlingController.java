package com.base.app.common.exception.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController implements ErrorController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// ���� ������ ����
	private final String ERROR_404_PAGE_PATH = "/error/404";
	private final String ERROR_500_PAGE_PATH = "/error/500";
	private final String ERROR_ETC_PAGE_PATH = "/error/error";

	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request, Model model) {

		// ���� �ڵ带 ȹ���Ѵ�.
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		// ���� �ڵ忡 ���� ���� ����
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        
		if (status != null) {
			// HttpStatus�� ���� ������ �б⸦ ������ ���� ����
			int statusCode = Integer.valueOf(status.toString());

			// �α׷� ���°��� ��� �� ���
			logger.info("httpStatus : " + statusCode);

			// 404 error
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				// ���� �������� ǥ���� ����
				model.addAttribute("code", status.toString());
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				model.addAttribute("timestamp", new Date());
				return ERROR_404_PAGE_PATH;
			}
            
			// 500 error
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				// ������ ���� �����̱� ������ ����ڿ��� ������ �������� �ʴ´�.
				return ERROR_500_PAGE_PATH;
			}
		}

		// ������ ���� �� ��� ������ error/error �������� ������.
		return ERROR_ETC_PAGE_PATH;
	}
}