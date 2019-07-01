package com.kitri.cafe.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

// 댓글 형식 게시판
public interface MemoService {

	// 글 쓰기
	void writeMemo(MemoDto memoDto);

	// 댓글 목록 | 인자1 : 댓글 번호
	String listMemo(int seq);

	// 글 수정
	String modifyMemo(int seq, int mseq, String mcontent);

	// 글 삭제 | 인자 : 댓글 번호
	String deleteMemo(int seq, int mseq);

}
