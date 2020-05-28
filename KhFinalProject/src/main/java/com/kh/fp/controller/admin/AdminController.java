package com.kh.fp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.fp.model.service.admin.AdminService;
import com.kh.fp.model.vo.AdminApplyStore;
import com.kh.fp.model.vo.AdminApplyStoreInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	
	@RequestMapping("/admin/adminMain.do")
	public ModelAndView adminMainPage(ModelAndView mv) {
		mv.setViewName("/admin/main");
		return mv;
	}
	
	@RequestMapping("/admin/applyStoreList")
	public ModelAndView applyStoreList(ModelAndView mv) {
		List<AdminApplyStore> sList = service.selectApplyStoreList();
		mv.addObject("sList",sList);
		mv.setViewName("/admin/applyStoreList");
		return mv;
	}
	
	@RequestMapping("/admin/applyStoreInfo")
	@ResponseBody
	public AdminApplyStoreInfo applyStoreInfo(@RequestParam int s_no) {
		return service.selectApplyStoreInfo(s_no);
	}

}
