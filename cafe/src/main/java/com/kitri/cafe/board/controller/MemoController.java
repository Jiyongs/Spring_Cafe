package com.kitri.cafe.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.cafe.service.MemoService;

@RestController // REST API만 사용하는 컨트롤러 | 컨트롤러의 모든 메소드 리턴타입이, view가 아닌 응답값일때 사용하는 컨트롤러 어노테이션. 이걸 지정하면, @ResponseBody 없이도 return값이 경로가 아님을 명시할 수 있음.
//@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	private MemoService memoService;

	// 댓글 작성
	// @RequestBody : 요청하는 쪽에서 파라미터가 아닌, json을 전송할 때 사용. 아래의 경우, json형식으로 받은 값들이
	// memodto에 자동으로 들어감.
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", headers = {"Content-type=application/json"})
	public String write(@RequestBody MemoDto memoDto, HttpSession session) {
		
		System.out.println(memoDto.getMcontent());
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			memoService.writeMemo(memoDto);
			
			String json = memoService.listMemo(memoDto.getSeq());
			return json;
			
		}
	
		return "";
		
	}
	
	// 댓글 목록 뿌리기
	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", headers = {"Content-type=application/json"})
	public String list(int seq) {
		String json = memoService.listMemo(seq);
		return json;
	}
	
	// 댓글 삭제하기
	// /memo/12/1231332
	// value="/{mseq}   : mseq는 변수임을 알려주는 것이 {}임
	// -> 인자로 받을 때, 인자 앞에 @PathVariable를 명시해야 함!
	//    -> 인자가 두 개 일땐, 받는 이름을 지정함
	@RequestMapping(value="/{seq}/{mseq}", method = RequestMethod.DELETE, consumes = "application/json", headers = {"Content-type=application/json"})
	public String delete(@PathVariable(name="seq") int seq, @PathVariable(name="mseq") int mseq) {
		
		String json  = memoService.deleteMemo(seq, mseq);
		return json;
	}
	
	// 댓글 수정하기
	@RequestMapping(value="/{seq}/{mseq}/{mcontent}", method=RequestMethod.PUT, consumes = "application/json", headers = {"Content-type=application/json"})
	public String modify(@PathVariable(name="seq") int seq, @PathVariable(name="mseq") int mseq, @PathVariable(name="mcontent") String mcontent) {
		
		String json = memoService.modifyMemo(seq, mseq, mcontent);
		return json;
	}
	
}
