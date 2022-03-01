package sbc.file.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import sbc.file.service.FileUploadService;
import static sbc.file.fileset.FilePath.*;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/file")
public class FileController {
	private FileUploadService service; // @Autowired 대신 @AllArgsConstructor 사용
	
	@GetMapping("form.do")
	public String formFu() {
		return "/file/form";
	}
	@PostMapping("upload.do")
	public String upload(String name, MultipartFile file) {
		String ofname = file.getOriginalFilename();
		if(ofname != null) ofname.trim();
		if(ofname.length() != 0) {
			String url = service.saveStore(file);
			log.info("#url: " + url);
			System.out.println("url: "+ url);
		}
		return "redirect:list.do"; // 저장소의 파일리스트로 가라
	}
	@GetMapping("list.do")
	public ModelAndView fileList() {
		File fStore = new File(FILE_STORE);
		if(!fStore.exists()) fStore.mkdirs();
		File files[] = fStore.listFiles();
		
		return new ModelAndView("file/list", "files", files);
	}
	@GetMapping("del.do")
	public String del(String fname) {
		File file = new File(FILE_STORE, fname);
		if(file.exists()) file.delete();
		
		return "redirect:list.do";
	}
	@GetMapping("download.do")
	public ModelAndView download(String fname) {
		File file = new File(FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView","downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	@GetMapping("form_mt.do")
	public String formFuMt() {
		return "file/form_mt";
	}
	@PostMapping("upload_mt.do")
	public String uploadMt(ArrayList<MultipartFile> files) {
		for(MultipartFile file: files) {
			upload("", file);
		}
		
		return "redirect:list.do";
	}
}
