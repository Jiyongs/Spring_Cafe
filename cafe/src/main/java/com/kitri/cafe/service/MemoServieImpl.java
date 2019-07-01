package com.kitri.cafe.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.board.dao.MemoDao;
import com.kitri.cafe.board.model.MemoDto;

@Service
public class MemoServieImpl implements MemoService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void writeMemo(MemoDto memoDto) {
		sqlSession.getMapper(MemoDao.class).writeMemo(memoDto);
	}

	@Override
	public String listMemo(int seq) {
		return makeJson(seq);
		
	}

	@Override
	public String modifyMemo(int seq, int mseq, String mcontent) {
		
		MemoDto memoDto = new MemoDto();
		memoDto.setMseq(mseq);
		memoDto.setMcontent(mcontent);
		
		sqlSession.getMapper(MemoDao.class).modifyMemo(memoDto);
		
		return makeJson(seq);
	}

	@Override
	public String deleteMemo(int seq, int mseq) {
		
		sqlSession.getMapper(MemoDao.class).deleteMemo(mseq);
		
		return makeJson(seq);
		
	}

	
	// 댓글 전체 목록 -> json 만들기 메소드
	private String makeJson(int seq) {
		List<MemoDto> list = sqlSession.getMapper(MemoDao.class).listMemo(seq);
		
		JSONArray array = new JSONArray(list);
		JSONObject json = new JSONObject();
		json.put("memolist", array);
		
		return json.toString();
	}
	
}
