package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.ReboardDto;

public interface ReboardDao {

	// 글 쓰기 | 리턴 : 작성한 글 번호
	int writeArticle(ReboardDto reboardDto);

	// 글 목록 | 인자1 : 페이지번호, 인자2 : 게시판 타입(bcode), 인자3 : 검색 조건, 인자4 : 검색어
	List<ReboardDto> listArticle(Map<String, String> parameter);

	// 글 보기 | 인자 : 글 번호
	ReboardDto viewArticle(int seq);

	// 글 수정 | 리턴 : 수정한 글 번호
	int modifyArticle(ReboardDto reboardDto);

	// 글 삭제 | 인자 : 글 번호
	void deleteArticle(int seq);

	// ---------------------------------------------------------------
	// [답글]
	void updateStep(ReboardDto reboardDto);
	int replyArticle(ReboardDto reboardDto);
	void updateReply(int pseq);
	
}
