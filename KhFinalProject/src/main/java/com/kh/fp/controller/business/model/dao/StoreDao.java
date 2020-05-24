package com.kh.fp.controller.business.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.fp.model.vo.StoreEnroll;

public interface StoreDao {

	int insertStore(SqlSessionTemplate session,StoreEnroll s);
	int insertStoreCategory(SqlSessionTemplate session, Map<String, Object> map);
	int insertStoreImage(SqlSessionTemplate session,Map<String, Object> map);
	List<Map<String, Object>> getStoresDetail(SqlSessionTemplate session,int no);
	List<Map<String, Object>> getStoresDetailcategory(SqlSessionTemplate session,Object no);
	List<Map<String, Object>> getStoresDetailfiles(SqlSessionTemplate session,Object no);
	Map<String, Object> getStoresUpdate(SqlSessionTemplate session, int no);
	int storeUpdate(SqlSessionTemplate session,StoreEnroll s);
	int deleteCategory(SqlSessionTemplate session,int no);
	Map<String, Object> orderDetail(SqlSessionTemplate session,int no);
}
