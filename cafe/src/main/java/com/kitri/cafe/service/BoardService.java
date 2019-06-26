package com.kitri.cafe.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BoardDto;

public interface BoardService {

	// 글 쓰기 | 리턴 : 작성한 글 번호
	int writeArticle(BoardDto boardDto);

	// 글 목록 | 인자1 : 페이지번호, 인자2 : 게시판 타입(bcode), 인자3 : 검색 조건, 인자4 : 검색어
	List<BoardDto> listArticle(Map<String, String> pamameter);

	// 글 보기 | 인자 : 글 번호
	BoardDto viewArticle(int seq);

	// 글 수정 | 리턴 : 수정한 글 번호
	int modifyArticle(BoardDto boardDto);

	// 글 삭제 | 인자 : 글 번호
	void deleteArticle(int seq);

}
