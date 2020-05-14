package com.kh.fp.controller.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.fp.common.PageingFactory;
import com.kh.fp.model.service.menuList.MenuListService;
import com.kh.fp.model.vo.Store;

@Controller
public class MenuListController {
	
//	private String title;
//	private double score;
//	private int reviewCount;
//	private int deleveryMinCharge;
//	private int deleveryTime;
//	private String listCategory;
//	private double km;
//	private String logimg;
//	private String recentimg;
	
	@Autowired
	private MenuListService service;
	
	@RequestMapping("/menu/menuList.do")
	public String menuList(Model m, 
			@RequestParam(value="menuCategory", required=false, defaultValue="전체") String category,
			@RequestParam(required=false, defaultValue="null") String sortType,
			@RequestParam(required=false, defaultValue="null") String search,
			@RequestParam(required=false, defaultValue="1") int cPage,
			@RequestParam(required=false, defaultValue="10") int numperPage,
			@RequestParam(required=false, defaultValue="0") double xl,
			@RequestParam(required=false, defaultValue="0") double yl,
			HttpSession session,
			HttpServletResponse res
			) {		
						
		//위도 경도 세션 처리
		if(xl==0 && yl==0) {
			xl = (double)session.getAttribute("xl");
			yl = (double)session.getAttribute("yl");
		}else {
			session.setAttribute("xl", xl);
			session.setAttribute("yl", yl);
		}
		
		System.out.println("========== xl ==========");
		System.out.println(xl);
		
		System.out.println("========== yl ==========");
		System.out.println(yl);

		Map map = new HashMap();
		
		//최근 본 상품 세션 처리
		if(session.getAttribute("recentList")!=null) {
			map.put("recentList", session.getAttribute("recentList"));
		}

		map.put("category", category);
		map.put("sortType", sortType);
		map.put("search", search);
		map.put("xl", xl);
		map.put("yl", yl);
		
		List<Store> storeList = service.selectMenuList(cPage, numperPage, map);
		
		System.out.println("=====list 출력 =====");
		for(Store s : storeList) {
			System.out.println(s);
		}
		
		System.out.println("==========list==========");
		System.out.println("====정렬순====");
		System.out.println(sortType);
		
		System.out.println("====검색====");
		System.out.println(search);
		
		
		int totalData = service.selectMenuCount(map);
		
		m.addAttribute("list", storeList);
		m.addAttribute("category", category);
		m.addAttribute("sortType", sortType);
		
		m.addAttribute("pageBar", PageingFactory.PageBarFactory(cPage, numperPage, totalData, "/spring/menu/menuList.do", category, search, sortType));
			
		
		return "menu/menulist";
	}
	
	//카테고리 눌럿을때
	@RequestMapping("/menu/menuFilter.do")
	@ResponseBody
	public Map menuCategory(
			@RequestParam(value="menuCategory", required=false, defaultValue="전체") String category,
			@RequestParam(required=false, defaultValue="") String sortType,
			@RequestParam(required=false, defaultValue="") String search,
			@RequestParam(required=false, defaultValue="1") int cPage,
			@RequestParam(required=false, defaultValue="10") int numperPage,
			@RequestParam(required=false, defaultValue="0") double xl,
			@RequestParam(required=false, defaultValue="0") double yl,
			HttpSession session
			) {
		
		
		
		if(xl==0 && yl==0) {
			xl = (double)session.getAttribute("xl");
			yl = (double)session.getAttribute("yl");
		}else {
			session.setAttribute("xl", yl);
			session.setAttribute("yl", yl);
		}
		
		System.out.println("========== xl ==========");
		System.out.println(xl);
		
		System.out.println("========== yl ==========");
		System.out.println(yl);
		
		Map map = new HashMap();
		
		map.put("category", category);
		map.put("sortType", sortType);
		map.put("search", search);
		map.put("xl", xl);
		map.put("yl", yl);
		
		System.out.println("==========filter==========");
		System.out.println("====정렬순====");
		System.out.println(sortType);
		
		System.out.println("====검색====");
		System.out.println(search);
		
		
		
		int totalData = service.selectMenuCount(map);
		
		List<Store> storeList = service.selectMenuListFilter(cPage, numperPage, map);
		
		System.out.println("=====list 출력 =====");
		for(Store s : storeList) {
			System.out.println(s);
		}
		
		map.put("cPage", cPage);
		map.put("list", storeList);
		
		map.put("pageBar", PageingFactory.PageBarFactoryAjax(cPage, numperPage, totalData, "/spring/menu/menuFilter.do", category, search, sortType));
		
		return map;
	}
	
	@RequestMapping("/menu/search.do")
	@ResponseBody
	public Map menuSearch(
			@RequestParam(value="name") String S_NAME,
			@RequestParam String category
			) {
		
		Map searchMap = new HashMap();
		searchMap.put("name", S_NAME);
		searchMap.put("category", category);
		
		List<Store> list = service.selectMenuList(searchMap);
		
		Map map = new HashMap();
		
		map.put("list", list);
		
		return map;
	}
}
