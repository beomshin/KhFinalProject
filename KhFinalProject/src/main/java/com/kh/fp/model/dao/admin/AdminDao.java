package com.kh.fp.model.dao.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import com.kh.fp.model.vo.AdminApplyStore;
import com.kh.fp.model.vo.AdminApplyStoreInfo;

public interface AdminDao {

	List<AdminApplyStore> selectApplyStoreList(SqlSessionTemplate session);

	List<String> selectApplyStoreCategory(SqlSessionTemplate session, int s_No);

	AdminApplyStoreInfo selectApplyStoreInfo(SqlSessionTemplate session, int s_no);

}
