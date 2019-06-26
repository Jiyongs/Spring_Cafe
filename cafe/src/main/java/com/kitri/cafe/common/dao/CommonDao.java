package com.kitri.cafe.common.dao;

public interface CommonDao {

	// 1. 다음 글 번호(seq) 얻기
	public int getNextSeq();

	// 2. 조회수(hit) 올리기
	public void udateHit(int seq);

}
