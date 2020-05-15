package com.kh.fp.controller.business.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.fp.controller.business.model.dao.LicenseeDao;
import com.kh.fp.model.vo.MenuCategory;
import com.kh.fp.model.vo.Side;

@Service
public class LicenseeServiceImpl implements LicenseeService {

	
	@Autowired
	LicenseeDao dao;
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public List<Map<String, Object>> getOrderInfo(int no,int cPage,int numPerpage) {
		// TODO Auto-generated method stub
		return dao.getOrderInfo(session,no,cPage,numPerpage);
	}

	@Override
	public int getOrderInfoAll(int no) {
		// TODO Auto-generated method stub
		return dao.getOrderInfoAll(session,no);
	}

	@Override
	public List<Map<String, Object>> getDetailOrder(int no) {
		// TODO Auto-generated method stub
		return dao.getDetailOrder(session,no);
	}
	
	@Override
	public int insertSide(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertSide(session, map);
	}
	@Override
	public List<Side> selectOption(int sNo) {
		// TODO Auto-generated method stub
		
		return dao.selectOption(session,sNo);
	}
	@Override
	public int insertCategory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.insertCategory(session,map);
	}
	@Override
	public List<MenuCategory> selectCategory() {
		// TODO Auto-generated method stub
		return dao.selectCategory(session);
	}
	
	
	
	
	
	
	
	
	
}
