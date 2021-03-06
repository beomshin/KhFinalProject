package com.kh.fp.controller.business;

import java.beans.Encoder;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.fp.controller.business.service.StoreService;
import com.kh.fp.model.vo.Business;
import com.kh.fp.model.vo.OrderInfo;
import com.kh.fp.model.vo.Sales;
import com.kh.fp.model.vo.Store;
import com.kh.fp.model.vo.StoreEnroll;

@Controller
public class StoreController {

	@Autowired
	Logger logger;

	@Autowired
	StoreService service;

	@Autowired
	BCryptPasswordEncoder encoder;

	@RequestMapping("/store/storeEnroll.do")

	public ModelAndView insertStore(ModelAndView mv,MultipartFile[] slogimg,HttpSession session,StoreEnroll s ) {
	
		String path = session.getServletContext().getRealPath("/resources/upload/store/");
		Business b = (Business) session.getAttribute("loginMember");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		s.setBno(b.getB_No());
		List<String> files = new ArrayList<String>();
		int result=0;
		File f= new File(path);
		

		if(!f.exists()) {
			f.mkdirs();
		}
		
		for(int i=0;i<slogimg.length;i++){
			
			if(!slogimg[i].isEmpty()) {
				
				String orilog = slogimg[i].getOriginalFilename();
				String ext = orilog.substring(orilog.lastIndexOf("."));
				String rename="deliveryKing_"+sdf.format(System.currentTimeMillis())+"_"+ext;
				try {
					slogimg[i].transferTo(new File(path+rename));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==0) {
					s.setLogimg(rename);
				}else {
					files.add(rename);
				}
				
				
			}
			
		}
		

		
		try {
			result =service.insertStore(s,files);
		}catch (RuntimeException e) {
			File delFlog = new File(path+s.getLogimg());
			if(delFlog.exists()) {
				delFlog.delete();
			}
//			File delF = new File(path+storeimg);
//			if(delF.exists()) {
//				delF.delete();
//			}
		}
		

		mv.addObject("msg", "가게 등록성공!");
		mv.addObject("loc", "/store/mypage");
		mv.setViewName("common/msg");
		return mv;
	}

	@RequestMapping("/store/storedetail")
	public ModelAndView storedetail(ModelAndView mv, HttpSession session) {
		// 가맹점등록정보

		Business b = (Business) session.getAttribute("loginMember");

		if (b == null) {
			mv.addObject("msg", "로그인해주세요");
			mv.addObject("loc", "/");
			mv.setViewName("common/msg");
			return mv;
		}

		List<Map<String, Object>> stores = service.getStoresDetail(b.getB_No());

		mv.addObject("stores", stores);

		mv.setViewName("business/storedetail");
		return mv;
	}

	@RequestMapping("/store/checkPw")
	@ResponseBody
	public boolean checkPw(String userpw, HttpSession session) {
		Business b = (Business) session.getAttribute("loginMember");
		boolean flag = false;
		if (b != null && encoder.matches(userpw, b.getB_Pw())) {
			flag = true;
		}
		return flag;

	}

	@RequestMapping("/store/storeupdate")
	public ModelAndView storeUdpate(ModelAndView mv, int no) {

		Map<String, Object> store = service.getStoresUpdate(no);
		mv.addObject("store", store);
		mv.setViewName("business/storeUpdate");
		return mv;
	}

	@RequestMapping("/store/storeupdateEnd")
	public ModelAndView storeUpdate(ModelAndView mv, StoreEnroll s, HttpSession session) {

		int result = service.storeUpdate(s);

		mv.addObject("msg", "가게 등록 수정 성공!");
		mv.addObject("loc", "/store/storedetail");
		mv.setViewName("common/msg");
		return mv;
	}

	@RequestMapping("/store/mypage")
	public ModelAndView myPage(ModelAndView mv, HttpSession session) {

		Business b = (Business) session.getAttribute("loginMember");

		if (b == null) {
			mv.addObject("msg", "로그인해주세요");
			mv.addObject("loc", "/");
			mv.setViewName("common/msg");
			return mv;
		}

		List<Map<String, Object>> stores = service.getStoresInfo(b.getB_No());

		mv.addObject("stores", stores);
		if (!stores.isEmpty()) {
			mv.addObject("sales", service.getSales(stores.get(0).get("S_NO")));
			mv.addObject("orderinfo", service.getOrderInfo(stores.get(0).get("S_NO")));
		}
		mv.setViewName("business/mypage");
		return mv;
	}

	@RequestMapping("/store/orderInfoGet.do")
	@ResponseBody
	public List<Map<String, Object>> orderinfoGet(int no) {
		List<Map<String, Object>> list = service.getOrderInfo(no);

		for (Map<String, Object> m : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			String time = sdf.format(m.get("O_DATE"));
			m.put("O_DATE", time);
		}

		return list;
	}

	@RequestMapping("/store/saleInfo.do")
	@ResponseBody
	public List<Sales> saleInfo(int no) {
		List<Sales> list = service.getSales(no);

		for (Sales m : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
			String time = sdf.format(m.getOrderDate());
			m.setTime(time);
		}

		return list;
	}

	@RequestMapping("/store/salesMonth.do")
	@ResponseBody
	public List<Map<String, Object>> salesMonth(int no) {
		List<Map<String, Object>> list = service.getSaleMonth(no);
		return list;
	}

	@RequestMapping("/licensee/calculate")
	public ModelAndView calculate(HttpSession session, ModelAndView mv) {
		// 정산내역

		Business b = (Business) session.getAttribute("loginMember");

		if (b == null) {
			mv.addObject("msg", "로그인해주세요");
			mv.addObject("loc", "/");
			mv.setViewName("common/msg");
			return mv;
		}

		List<Map<String, Object>> stores = service.getStoresInfo(b.getB_No());

		if (stores.isEmpty()) {
			mv.addObject("msg", "가게등록해 주세요");
			mv.addObject("loc", "/store/mypage");
			mv.setViewName("common/msg");
			return mv;
		}

		mv.addObject("stores", stores);
		mv.addObject("sales", service.getSales(stores.get(0).get("S_NO")));
		mv.addObject("salesmonth", service.getSaleMonth(stores.get(0).get("S_NO")));
		mv.setViewName("business/calculate");
		return mv;
	}

	@RequestMapping("/store/orderdetail.do")
	@ResponseBody
	public OrderInfo orderDetail(int no) {

		OrderInfo m = service.orderDetail(no);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		DecimalFormat dec = new DecimalFormat("#,###");
		m.setDate(sdf.format(m.getO_date()));
		m.setPrice(dec.format(m.getO_oriprice()));

		return m;
	}

	@RequestMapping("/order/orderSelect.do")
	public ModelAndView orderSelect(int flag, int sno, int no, int cPage, ModelAndView mv) {

		int result = 0;

		if (flag == 1) { // 승인
			result = service.orderSelectOk(no);
		} else { // 거절
			result = service.orderSelectReject(no);
		}

		if (result == 0) {
			mv.addObject("msg", "로그인해주세요");
			mv.addObject("loc", "/");
			mv.setViewName("common/msg");
			return mv;
		}

		mv.setViewName("redirect:/licensee/order?no=" + sno + "&cPage=" + cPage);
		return mv;

	}

	@RequestMapping("/store/orderChatting")
	public ModelAndView orderChatting(int no, ModelAndView mv) {
		StoreEnroll s = service.getStore(no);
		mv.addObject("no", no);
		mv.addObject("sname", s.getSname());
		mv.setViewName("/business/orderChatting");
		return mv;
	}

}
