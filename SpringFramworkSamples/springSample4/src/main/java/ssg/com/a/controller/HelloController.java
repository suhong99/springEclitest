package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@Controller
public class HelloController {
	
	@Autowired
	MemberService service;	// MemberService service = new MemberServiceImpl();

	@RequestMapping(value = "hello.do", method = RequestMethod.GET)
	public String hello(Model model) {
		System.out.println("HelloController hello() " + new Date());
		
		List<MemberDto> list = service.allMember();		
		model.addAttribute("list", list);
		
		return "hello";
	}
	
	@GetMapping("find.do")
	public String findMember(String id, Model model) {
		System.out.println("HelloController findMember() " + new Date());
		
		MemberDto mem = service.find(id);
		model.addAttribute("mem", mem);
		
		return "hello";
	}
	
}










