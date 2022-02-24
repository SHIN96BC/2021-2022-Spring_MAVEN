package sbc.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import static sbc.file.fileset.FilePath.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Log4j
@Service
public class MemoryFileUploadService implements FileUploadService {
	@Override
	public String saveStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx); // 대부분 (이상, 미만) 이다.
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder(); // + 연산자를 사용하면 계속 새로운 스트링 객체가 생기기떄문에 StringBuilder 를 시용해서 메모리 사용을 최소화 해준다. 
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String saveFileName = sb.toString();
		
		long fsize = file.getSize();
		log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize);
		
		// 업로드 로직
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		return FILE_STORE + "savaFileName";
	}
	private boolean writeFile(MultipartFile file, String savaFileName) {
		File dir = new File(FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(FILE_STORE+savaFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}
}
