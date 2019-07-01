package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoDao {

	// 글 쓰기
	int writeMemo(MemoDto memoDto);

	// 댓글 목록 | 인자 : 원글 번호
	List<MemoDto> listMemo(int seq);

	// 글 보기 | 인자 : 글 번호
	MemoDto viewMemo(int seq);

	// 글 수정
	void modifyMemo(MemoDto memoDto);

	// 글 삭제 | 인자 : 댓글 번호
	void deleteMemo(int mseq);

}
