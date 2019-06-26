package com.kitri.cafe.service;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.board.dao.ReboardDao;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.util.CafeConstance;
import com.kitri.cafe.util.NumberCheck;

@Service
public class ReboardServiceImpl implements ReboardService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int cnt = sqlSession.getMapper(ReboardDao.class).writeArticle(reboardDto);
	
		return cnt != 0 ? reboardDto.getSeq() : 0;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> parameter) {
		
		int pg = NumberCheck.NotNumberToOne(parameter.get("pg"));
		
		int end = pg * CafeConstance.ARTICLE_SIZE;		// 시작 페이지
		int start = end - CafeConstance.ARTICLE_SIZE;	// 끝 페이지
		
		parameter.put("start", start + "");
		parameter.put("end", end + "");
		
		return sqlSession.getMapper(ReboardDao.class).listArticle(parameter);
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		ReboardDto reboardDto = sqlSession.getMapper(ReboardDao.class).viewArticle(seq);	
		
		reboardDto.setContent(reboardDto.getContent().replace("\n", "<br>")); //글 내용의 enter를 <br>로 변환 (나중에 editor 사용 시 삭제 예정)
		return reboardDto;
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public void deleteArticle(int seq) {
		
	}

}
