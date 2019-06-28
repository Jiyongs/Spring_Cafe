package com.kitri.cafe.service;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitri.cafe.board.dao.ReboardDao;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.cafe.util.CafeConstance;
import com.kitri.cafe.util.NumberCheck;

@Service
public class ReboardServiceImpl implements ReboardService{

	@Autowired
	private SqlSession sqlSession;
	

	// [일반 게시판]
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
	@Transactional
	public ReboardDto viewArticle(int seq) {
		sqlSession.getMapper(CommonDao.class).updateHit(seq);
		ReboardDto reboardDto = sqlSession.getMapper(ReboardDao.class).viewArticle(seq);	
		
		reboardDto.setContent(reboardDto.getContent().replace("\n", "<br>")); //글 내용의 enter를 <br>로 변환 (나중에 editor 사용 시 삭제 예정)
		return reboardDto;
	}

	@Override
	@Transactional
	public ReboardDto getArticle(int seq) {

		return sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
	}

	
	@Override
	public int modifyArticle(ReboardDto reboardDto) {
		return 0;
	}

	
	@Override
	public void deleteArticle(int seq) {
		
	}

	// ---------------------------------------------------------------------------------
	// [답글]
	
	@Override
	@Transactional
	public int replyArticle(ReboardDto reboardDto) {
		ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);
		reboardDao.updateStep(reboardDto);
		reboardDao.replyArticle(reboardDto);
		reboardDao.updateReply(reboardDto.getPseq());
		
		return reboardDto.getSeq();
	}

}
