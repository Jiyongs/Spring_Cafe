package com.kitri.cafe.admin.board.dao;

import java.util.List;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.model.BoardTypeDto;
import com.kitri.cafe.admin.board.model.CategoryDto;

public interface BoardAdminDao {

	// 1 게시판 목록 get *ccode(카테고리 번호)
	
	List<BoardListDto> getBoardMenuList(int ccode);
	
	// 2 카테고리 목록 get
	List<CategoryDto> getCategoryList();
	// 3 카테고리 생성
	void makeCategory(CategoryDto categoryDto);
	
	// 4 게시판 형식 목록 get (공지사항/방명록...)
	List<BoardTypeDto> getBoardTypeList();
	// 5 게시판 생성
	void makeBoard(BoardListDto boardListDto);
}
