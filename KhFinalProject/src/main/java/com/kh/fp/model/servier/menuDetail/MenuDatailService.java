package com.kh.fp.model.servier.menuDetail;

import java.util.List;
import java.util.Map;

import com.kh.fp.model.vo.Report;
import com.kh.fp.model.vo.StoreDetailInfo;
import com.kh.fp.model.vo.StoreMenu;

public interface MenuDatailService {

	StoreDetailInfo selectStroeDetailInfo(int no);

	List<StoreMenu> selectCategoryMenu(int no);

	StoreMenu selectMenuDetail(int no);

	List selectStoreDetailReview(Map que, int cPage);

	int insertReport(Report report);

	int selectBookMarkCheck(Map bmCheck);

	int selectBookMarking(Map<String, Integer> bm);

}
