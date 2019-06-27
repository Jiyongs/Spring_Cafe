package com.kitri.cafe.common.dao;

import java.util.Map;

public interface CommonDao {

	// 1. 다음 글 번호(seq) 얻기
	public int getNextSeq();

	// 2. 조회수(hit) 올리기
	public void updateHit(int seq);
	
	// 3. 새 글 개수 얻기
	public int getNewArticleCount(int bcode);
	// 4. 전체 글 개수 얻기
	public int getTotalArticleCount(Map<String, String> parameter);
	

}
