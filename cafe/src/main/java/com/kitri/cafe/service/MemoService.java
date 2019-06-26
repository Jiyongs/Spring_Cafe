package com.kitri.cafe.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

// 댓글 형식 게시판
public interface MemoService {

	// 글 쓰기
	void writeMemo(MemoDto memoDto);

	// 글 목록 | 인자1 : 원글 번호, 인자2: ...
	List<MemoDto> listMemo(Map<String, String> pamameter);

	// 글 수정
	void modifyMemo(MemoDto memoDto);

	// 글 삭제 | 인자 : 댓글 번호
	void deleteMemo(int mseq);

}
