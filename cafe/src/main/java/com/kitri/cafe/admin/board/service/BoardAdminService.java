package com.kitri.cafe.admin.board.service;

import java.util.List;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.model.BoardTypeDto;
import com.kitri.cafe.admin.board.model.CategoryDto;

public interface BoardAdminService {
	
	List<BoardListDto> getBoardMenuList(int ccode);
	
	List<CategoryDto> getCategoryList();
	void makeCategory(CategoryDto categoryDto);
	
	List<BoardTypeDto> getBoardTypeList();
	void makeBoard(BoardListDto boardListDto);
}
