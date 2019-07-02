package com.kitri.cafe.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.kitri.cafe.board.model.AlbumDto;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.cafe.service.AlbumService;

@Controller
@RequestMapping("/album")
public class AlbumController {
	
	// 서블릿 컨텍스트 객체 생성 (내장객체인 서블릿 컨텍스트를 통해 realpath얻기위함 | application = servlet context)
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)	// 단순 페이지 이동 (void 시, 클래스의 Mapping/메소드의Mapping으로 감)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter", parameter);
	}
	
	// 글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(AlbumDto albumDto,
						@RequestParam("picture") MultipartFile multipartFile,  // multipartFile : 파일을 받는 역할
						@RequestParam Map<String, String> parameter,
						Model model, HttpSession session) {
						
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			
		if(memberDto != null) { // 로그인한 경우만 글 번호 얻음.
				
			int seq = commonService.getNextSeq();
			albumDto.setSeq(seq);
			albumDto.setId(memberDto.getId());
			albumDto.setName(memberDto.getName());
			albumDto.setEmail(memberDto.getEmail());
			
			// 받은 파일 정보를 dto에 세팅
			if(multipartFile != null && !multipartFile.isEmpty()) { // 파일이 존재하고, 파일이 빈 파일이 아니라면
				String orignPicture = multipartFile.getOriginalFilename();	// [원본 파일명]
				
				String realPath = servletContext.getRealPath("/upload/album"); // 서버에서도 실시간으로 실제 경로를 얻기 위해, getRealPath()를 사용해야 함
				DateFormat df = new SimpleDateFormat("yyMMdd"); // MM : 월, mm : 분
				String saveFolder = df.format(new Date());		// [실제 저장되는 파일명]
				String realSaveFolder = realPath + File.separator + saveFolder; //File.separator : 윈도우면 \, 유닉스 계열이면 /가 알아서 잡힘
				System.out.println(realSaveFolder);
				File dir = new File(realSaveFolder);
				if(!dir.exists()) { // 해당 파일이 존재하지 않는 경우,
					dir.mkdirs(); // 파일 생성
				}

				String savePicture = UUID.randomUUID().toString() + orignPicture.substring(orignPicture.lastIndexOf(".")); // 식별값 + 확장자 // [실제 저장되는 파일명.확장자]
				
				File file = new File(realSaveFolder, savePicture);
				
				try {
					
					multipartFile.transferTo(file); //multipartFile에 있는 파일을 file로 옮겨버림
					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				albumDto.setOrignPicture(orignPicture);
				albumDto.setSavePicture(savePicture);
				albumDto.setSaveFolder(saveFolder);
			}
					
			// TODO : 1. image file 여부 검사 (확장자가 jpg, jepg, png, gif만 올릴 수 있게 하기) | 2. thumbnail image 적용하기
			// 사진게시판 insert
			// 파일 안 올렸을 경우, 써질지 말지 결정해서 구현하기
			// 
			seq = albumService.writeArticle(albumDto);
				
			if(seq != 0) {
				model.addAttribute("seq", seq);
			} else {
				model.addAttribute("errorMsg", "서버문제로 글 작성에 실패했습니다.");
			}
							
			} else {
				// TODO 에러처리 참고!
				//model.addAttribute("error", "~에러가 났습니다.");
				model.addAttribute("errorMsg", "로그인 후 글 작성이 가능합니다.");
			}
			
		model.addAttribute("parameter", parameter);
		return "album/writeok";	
			
	}
	
}
