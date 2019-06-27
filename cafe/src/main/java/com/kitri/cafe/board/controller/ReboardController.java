package com.kitri.cafe.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.cafe.service.ReboardService;
import com.kitri.cafe.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ReboardService reboardService;
	
	// 글쓰기 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)	// 단순 페이지 이동 (void 시, 클래스의 Mapping/메소드의Mapping으로 감)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}
	
	// 글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(ReboardDto reboardDto,
						@RequestParam Map<String, String> parameter,
						Model model, HttpSession session) {
		
		String path = "";
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		
		if(memberDto != null) { // 로그인한 경우만 글 번호 얻음.
			
			int seq = commonService.getNextSeq();
			reboardDto.setSeq(seq);
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail());
			reboardDto.setRef(seq);
			
			seq = reboardService.writeArticle(reboardDto);
			
			if(seq != 0) {
				model.addAttribute("seq", seq);
				path = "reboard/writeok";
			} else {
				path = "reboard/writefail";
			}
						
		} else {
			path = "";			
		}
		
		model.addAttribute("parameter", parameter);
		return path;	
		
	}
	
	// 작성 글 확인 페이지 이동
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam("seq") int seq,
			@RequestParam Map<String, String> parameter,
			Model model, HttpSession session) {
				
		String path="";
		
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			
			ReboardDto  reboardDto = reboardService.viewArticle(seq);
			
			model.addAttribute("article", reboardDto);
			model.addAttribute("parameter", parameter);
			path="reboard/view";
		
		} else {
		
			path="redirect:/index.jsp";
		
		}
		
		return path;
	}
	
	// 글 목록 이동
	@RequestMapping(value = "/list", method = RequestMethod.GET)	// 단순 페이지 이동 (void 시, 클래스의 Mapping/메소드의Mapping으로 감)
	public void list(@RequestParam Map<String, String> parameter, Model model, HttpServletRequest request) {
		
		List<ReboardDto> list = reboardService.listArticle(parameter);
		PageNavigation pageNavigation = commonService.getPageNavigation(parameter);
		
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.makeNavigator();
		
		model.addAttribute("parameter", parameter);
		model.addAttribute("articleList", list);
		model.addAttribute("navigator", pageNavigation);
	}
	
	
	
}
