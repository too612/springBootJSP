package com.base.app.main.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.base.app.common.util.MyUtil;
import com.base.app.main.data.dto.MainDTO;
import com.base.app.main.service.MainService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

//@RestController �̰Ŵ� boot���� html�� ������ִ� ���̴�.
//������ ��Ʈ�� jsp�� �Ľ��Ҷ��� @Controller�� �ٲ�����Ѵ�.@@
@Controller
public class MainController {

	@Resource
	private MainService MainService; // �긦 ȣ���ϸ� MainServiceImpl�� ��������

	@Autowired
	MyUtil myUtil; // @Service�� ������ MyUtil�� �ҷ��°�
	
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("index"); // jsp(html)�� ������ setViewName // class�� ������ setView

		return mav;
	}

	@RequestMapping(value = "/main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(HttpServletRequest request) throws Exception {

		return "main/index";
	}

	@RequestMapping(value = "/test.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String test(HttpServletRequest request) throws Exception {

		return "main/main";
	}
	@RequestMapping(value = "/about.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView about(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/about");

		return mav;
	}
	
	@RequestMapping(value = "/main/api.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView api(@RequestBody HashMap<String, Object> map) throws Exception {

		ModelAndView mav = new ModelAndView();
		System.out.println("����" + map);
		System.out.println(map.get("title"));

		String relt = MainService.api(map);
		//System.out.println("controller-->" + relt);
		mav.setViewName("main/main"); // jsp(html)�� ������ setViewName // class�� ������ setView
		mav.addObject("response", relt);

		return mav;
	}

	@RequestMapping(value = "/created.action", method = RequestMethod.GET)
	public ModelAndView created() throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("bbs/created"); // jsp(html)�� ������ setViewName // class�� ������ setView

		return mav;
	}

	@RequestMapping(value = "/created.action", method = RequestMethod.POST)
	public ModelAndView created_ok(MainDTO dto, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();

		int maxNum = MainService.maxNum();

		dto.setNum(maxNum + 1);
		dto.setIpAddr(request.getRemoteAddr());

		MainService.insertData(dto);

		mav.setViewName("redirect:/list.action");

		return mav;

	}

	@RequestMapping(value = "/list.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(MainDTO dto, HttpServletRequest request) throws Exception {

		String pageNum = request.getParameter("pageNum");// ���ڸ� ���°ǰ�?

		int currentPage = 1;

		if (pageNum != null)
			currentPage = Integer.parseInt(pageNum);

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if (searchValue == null) {
			searchKey = "subject";
			searchValue = "";
		} else {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}

		int dataCount = MainService.getDataCount(searchKey, searchValue);

		int numPerPage = 5;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);

		if (currentPage > totalPage)
			currentPage = totalPage;

		int start = (currentPage - 1) * numPerPage + 1; // 1 6 11 16
		int end = currentPage * numPerPage;

		List<MainDTO> lists = MainService.getLists(start, end, searchKey, searchValue);

		String param = "";

		if (searchValue != null && !searchValue.equals("")) { // ���� ã�Ƴ��� ���ϴ°�찡 �ֱ⶧���� ���ʿ� �������� ���ش�.
			param = "searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}

		String listUrl = "/list.action";

		if (!param.equals("")) {
			listUrl += "?" + param;
		}

		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

		String articleUrl = "/article.action?pageNum=" + currentPage;

		if (!param.equals("")) {
			articleUrl += "&" + param;
		}

		/*
		 * request.setAttribute("lists", lists);
		 * request.setAttribute("articleUrl", articleUrl);
		 * request.setAttribute("pageIndexList", pageIndexList);
		 * request.setAttribute("dataCount", dataCount);
		 * 
		 * return "bbs/list";
		 */
		// ModelAndView�� ����
		ModelAndView mav = new ModelAndView();

		mav.addObject("lists", lists);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);

		mav.setViewName("bbs/list");

		return mav;
	}

	@RequestMapping(value = "/article.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView article(HttpServletRequest request) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if (searchValue != null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}

		MainService.updateHitCount(num);

		MainDTO dto = MainService.getReadData(num);

		if (dto == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			return mav;
			// return "redirect:/list.action"; ��ȯ���� String �϶� �̷��� ���ְ� �𵨿���ϱ� ��ó��
		}

		int lineSu = dto.getContent().split("\n").length;

		String param = "pageNum=" + pageNum;
		if (searchValue != null && !searchValue.equals("")) { // �˻��� �ߴٴ¶�

			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}

		// �𵨿���� String�� �޾Ƴ��� ���Ѵ�. ModelAndView mav = new ModelAndView();

		ModelAndView mav = new ModelAndView();

		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("lineSu", lineSu);
		mav.addObject("pageNum", pageNum);

		mav.setViewName("bbs/article");

		return mav;

	}

	@RequestMapping(value = "/updated.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView updated(HttpServletRequest request) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		if (searchValue != null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}

		MainDTO dto = MainService.getReadData(num);

		if (dto == null) {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			return mav;
			// return "redirect:/list.action"; ��ȯ���� String �϶� �̷��� ���ְ� �𵨿���ϱ� ��ó��
		}

		String param = "pageNum=" + pageNum;

		if (searchValue != null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}

		/*
		 * request.setAttribute("dto", dto);
		 * request.setAttribute("pageNum", pageNum);
		 * request.setAttribute("params", param);
		 * request.setAttribute("searchKey", searchKey);
		 * request.setAttribute("searchValue", searchValue);
		 * 
		 * return "bbs/updated";
		 */
		// �𵨾غ� ���۹��
		ModelAndView mav = new ModelAndView();

		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);

		mav.setViewName("bbs/updated");

		return mav;

	}

	@RequestMapping(value = "/updated_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView updated_ok(MainDTO dto, HttpServletRequest request) throws Exception {

		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		dto.setContent(dto.getContent().replaceAll("<br/>", "\r\n"));

		MainService.updateData(dto);

		String param = "?pageNum=" + pageNum;

		if (searchValue != null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		// ModelAndView�� �����Ͷ� ��ΰ� ���� �Ѿ�� ��� ���� �����Ͱ� �ȳѾ�ϱ� ��θ� ��ȯ���ָ��
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/list.action" + param);

		return mav;
	}

	@RequestMapping(value = "/deleted_ok.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView deleted_ok(HttpServletRequest request) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");

		MainService.deleteData(num);

		String param = "?pageNum=" + pageNum;

		if (searchValue != null && !searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/list.action" + param);

		return mav;

	}

	@RequestMapping(value = "/board.action", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView board(HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/sidebar_v1");

		return mav;
	}
}
