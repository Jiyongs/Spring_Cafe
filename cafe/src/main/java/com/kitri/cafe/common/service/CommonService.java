package com.kitri.cafe.common.service;

import java.util.Map;

import com.kitri.cafe.util.PageNavigation;

// 게시판의 공통 기능
public interface CommonService {

	// 1. 다음 글 번호(seq) 얻기
	public int getNextSeq();
	
	// 2. 페이지 정보 얻기
	PageNavigation getPageNavigation(Map<String, String> parameter);
	
	
}
