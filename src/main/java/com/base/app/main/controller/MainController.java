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

//@RestController 이거는 boot에서 html을 사용해주는 것이다.
//하지만 부트에 jsp를 파싱할때는 @Controller로 바꿔줘야한다.
@Controller
public class MainController {

	@Resource
	private MainService MainService; // 얘를 호출하면 MainServiceImpl이 딸려들어옴

	@Autowired
	MyUtil myUtil; // @Service로 구현된 MyUtil을 불러온것
	
	@RequestMapping(value = "/")
	public ModelAndView index() throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("index"); // jsp(html)로 갈때는 setViewName // class로 갈때는 setView

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
		System.out.println("진입" + map);
		System.out.println(map.get("title"));

		String relt = MainService.api(map);
		System.out.println("controller-->" + relt);
		mav.setViewName("main/main"); // jsp(html)로 갈때는 setViewName // class로 갈때는 setView
		mav.addObject("response", relt);

		return mav;
	}

	@RequestMapping(value = "/created.action", method = RequestMethod.GET)
	public ModelAndView created() throws Exception {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("bbs/created"); // jsp(html)로 갈때는 setViewName // class로 갈때는 setView

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

		String pageNum = request.getParameter("pageNum");// 문자만 따온건가?

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

		if (searchValue != null && !searchValue.equals("")) { // 널을 찾아내지 못하는경우가 있기때문에 양쪽에 부정문을 써준다.
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
		// ModelAndView로 전송
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
			// return "redirect:/list.action"; 반환값이 String 일때 이렇게 써주고 모델엔뷰니깐 위처럼
		}

		int lineSu = dto.getContent().split("\n").length;

		String param = "pageNum=" + pageNum;
		if (searchValue != null && !searchValue.equals("")) { // 검색을 했다는뜻

			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}

		// 모델엔뷰는 String을 받아내지 못한다. ModelAndView mav = new ModelAndView();

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
			// return "redirect:/list.action"; 반환값이 String 일때 이렇게 써주고 모델엔뷰니깐 위처럼
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
		// 모델앤뷰 전송방식
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
		// ModelAndView는 데이터랑 경로가 같이 넘어갈때 사용 여긴 데이터가 안넘어가니깐 경로만 반환해주면됌
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
