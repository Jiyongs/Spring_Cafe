package com.kitri.cafe.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.service.AlbumService;

@Controller
@RequestMapping("/album")
public class AlbumController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)	// 단순 페이지 이동 (void 시, 클래스의 Mapping/메소드의Mapping으로 감)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}
	
}
