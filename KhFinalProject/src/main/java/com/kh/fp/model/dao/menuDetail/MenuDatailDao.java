package com.kh.fp.model.dao.menuDetail;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.fp.model.vo.MenuDetailReviewMenu;
import com.kh.fp.model.vo.Report;
import com.kh.fp.model.vo.StoreCategory;
import com.kh.fp.model.vo.StoreDetailInfo;
import com.kh.fp.model.vo.StoreMenu;
import com.kh.fp.model.vo.StoreMenuSide;

public interface MenuDatailDao {

	StoreDetailInfo selectStroeDetailInfo(SqlSessionTemplate session, int no);

	List<StoreCategory> selectStoreDetailCategory(SqlSessionTemplate session, int no);

	List<StoreMenu> selectStoreDetailBestMenu(SqlSessionTemplate session, int no);

	List<StoreMenu> selectCategoryMenu(SqlSessionTemplate session, int no);

	StoreMenu selectMenuDetail(SqlSessionTemplate session, int no);

	List<StoreMenuSide> selectMenuDetailSide(SqlSessionTemplate session, int no);

	List selectStoreDetailReview(SqlSessionTemplate session, Map que, int cPage);

	List<String> selectStroeDetailReviewImg(SqlSessionTemplate session, int r_no);

	int selectStoreDetailReviewCount(SqlSessionTemplate session, Map que);

	int insertReport(SqlSessionTemplate session, Report report);

	List<MenuDetailReviewMenu> selectStoreDetailReviewMenu(SqlSessionTemplate session, int o_no);

	int selectBookMarkCheck(SqlSessionTemplate session, Map bmCheck);

	int insertBookMarking(SqlSessionTemplate session, Map<String, Integer> bm);

	int deleteBookMarking(SqlSessionTemplate session, Map<String, Integer> bm);

	List<String> selectStoreDetailImgs(SqlSessionTemplate session, int no);

}
