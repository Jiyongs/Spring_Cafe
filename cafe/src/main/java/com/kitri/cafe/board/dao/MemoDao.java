package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoDao {

	// 글 쓰기
	int writeArticle(MemoDto memoDto);

	// 글 목록 | 인자1 : 원글 번호, 인자2: ...
	List<MemoDto> listArticle(Map<String, String> pamameter);

	// 글 보기 | 인자 : 글 번호
	//MemoDto viewArticle(int seq);

	// 글 수정
	int modifyArticle(MemoDto memoDto);

	// 글 삭제 | 인자 : 댓글 번호
	void deleteArticle(int seq);

}
