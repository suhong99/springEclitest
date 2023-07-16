package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	
	@GetMapping("bbslist.do")
	public String bbslist(BbsParam param, Model model) {
		System.out.println("BbsController bbslist() " + new Date());
		System.out.println(param.toString());
		
		if(param == null || param.getSearch() == null || param.getChoice() == null) {
			param = new BbsParam("", "", 0);
		}
		
		List<BbsDto> list = service.bbslist(param);
				
		// 글의 총수
		int count = service.getAllBbs(param);	// 23
		// 페이지를 계산
		int pageBbs = count / 10;	// -> 2
		if((count % 10) > 0) {
			pageBbs = pageBbs + 1;	// -> 3 페이지
		}
		
		
		model.addAttribute("bbslist", list);
		model.addAttribute("pageBbs", pageBbs);
		model.addAttribute("param", param);
		
		return "bbslist";
	}
	
	@GetMapping("bbswrite.do")
	public String bbswrite() {
		System.out.println("BbsController bbswrite() " + new Date());
		return "bbswrite";
	}
	
	@PostMapping("bbswriteAf.do")
	public String bbswriteAf(BbsDto dto, Model model) {
		System.out.println("BbsController bbswriteAf() " + new Date());
		
		boolean isS = service.bbswrite(dto);
		String bbswrite = "BBS_ADD_OK";
		if(isS == false) {
			bbswrite = "BBS_ADD_NO";
		}
		model.addAttribute("bbswrite", bbswrite);
		
		return "message";
	}
	
	@GetMapping("bbsdetail.do")
	public String bbsdetail(int seq, Model model) {
		System.out.println("BbsController bbsdetail() " + new Date());
		
		BbsDto dto = service.bbsdetail(seq);		
		model.addAttribute("bbsdto", dto);
		
		return "bbsdetail";
	}
	
}



















