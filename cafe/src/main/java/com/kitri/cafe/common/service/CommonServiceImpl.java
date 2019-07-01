package com.kitri.cafe.common.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.cafe.util.CafeConstance;
import com.kitri.cafe.util.NumberCheck;
import com.kitri.cafe.util.PageNavigation;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getNextSeq() {
		
		return sqlSession.getMapper(CommonDao.class).getNextSeq();
		
	}

	@Override
	public PageNavigation getPageNavigation(Map<String, String> parameter) {
		PageNavigation navigation = new PageNavigation();
		
		int newArticleCount =
				sqlSession.getMapper(CommonDao.class).getNewArticleCount(Integer.parseInt(parameter.get("bcode")));
		int totalArticleCount =
				sqlSession.getMapper(CommonDao.class).getTotalArticleCount(parameter);
		int totalPageCount =
				(totalArticleCount - 1) / CafeConstance.ARTICLE_SIZE + 1;
		int pg =
				NumberCheck.NotNumberToOne(parameter.get("pg"));
		// true : 이전 못 누름 | false : 이전 누름 가능
		boolean nowFirst = (pg <= CafeConstance.PAGE_SIZE);
		// true : 다음 못 누름 | false : 다음 누름 가능
		boolean nowEnd = (pg > (totalPageCount - 1) / CafeConstance.PAGE_SIZE * CafeConstance.PAGE_SIZE);
		
		navigation.setNewArticleCount(newArticleCount);
		navigation.setTotalArticleCount(totalArticleCount);
		navigation.setTotalPageCount(totalPageCount);
		navigation.setNowFirst(nowFirst);
		navigation.setNowEnd(nowEnd);
		navigation.setPageNo(pg);
		
		return navigation;
	}

}
