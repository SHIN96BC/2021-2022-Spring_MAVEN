package sbc.file.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sbc.file.service.DragdropService;


@Controller
@RequestMapping("file")
public class DragdropController {
	@Autowired
	private DragdropService service;
	
	@GetMapping("/form_dd.do")
	public String form() {
		return "drag_drop/form";
	}
	@PostMapping("fileUpload.do")
	public String fileUpload(MultipartHttpServletRequest mhsr) {
		service.setMultipartRequest(mhsr);
		Map<String, List<Object>> map = service.getUpdateFileName();
		System.out.println("#DragdropController map: " + map);
		
		String appendData = mhsr.getParameter("temp");
		System.out.println("#DragdropController appendData: " + appendData);
		return "redirect:list.do";
	}
}
