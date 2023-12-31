package ssg.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist.do")
	public String pdslist(Model model) {
		System.out.println("PdsController pdslist() " + new Date());
		
		List<PdsDto> list = service.pdslist();		
		model.addAttribute("pdslist", list);
		
		return "pdslist";
	}
	
	@GetMapping("pdswrite.do")
	public String pdswrite() {
		System.out.println("PdsController pdswrite() " + new Date());		
		return "pdswrite";
	}
	
	@PostMapping("pdsupload.do")
	public String pdsupload(PdsDto pds, 
							@RequestParam(value = "fileupload", required = false)
							MultipartFile fileupload,
							HttpServletRequest request) {
		System.out.println("PdsController pdsupload() " + new Date());
		
		// filename 취득
		String filename = fileupload.getOriginalFilename();
		System.out.println("filename:" + filename);
		
		// db에 저장을 위해서 
		pds.setFilename(filename);
		
		// upload의 경로 설정
		// tomcat
		String fupload = request.getServletContext().getRealPath("/upload");		
		// 폴더
	//	String fupload = "d:\\tmp";
		
		System.out.println("파일업로드경로:" + fupload);
		
		// 파일명을 변경 data.txt -> 323423423.txt 
		String newfilename = PdsUtil.getNewfileName(filename);		
		System.out.println("newfilename:" + newfilename);
		
		// db에 저장을 위해서
		pds.setNewfilename(newfilename);
		
		// 파일 업로드
		File file = new File(fupload + "/" + newfilename);
		
		try {
			// 실제 파일에 업로드
			FileUtils.writeByteArrayToFile(file, fileupload.getBytes());
			
			// DB에 저장
			boolean isS = service.uploadPds(pds);
			if(isS) {
				System.out.println("파일 업로드 성공!");
			}else {
				System.out.println("파일 업로드 실패");
			}
			
		} catch (IOException e) {			 
			e.printStackTrace();
		}		
		
		return "redirect:/pdslist.do";
	}
}















