package lineageM.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventRequestDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	@Autowired
	private EventService eventService;
	
	@GetMapping("/event/list")
	public String event() {
		return "/event/list";
	}
	
	@GetMapping("/event/reg")
	public String reg() {
		return "/event/reg";
	}
	
	@PostMapping("/event/reg")
	public String reg(@RequestParam("files")MultipartFile[] files, EventRequestDto dto) throws IllegalStateException, IOException {
		log.debug(dto);
		log.debug(files);
		String path="D:/IDE/spring/workspace/lineageM/src/main/resources/static"+"/upload/event";
		File dir=new File(path);
		if(!dir.exists()) { //현재 디렉토리가 존재하지 않으면
			dir.mkdirs(); //디렉토리 생성
			log.debug("dir 생성");
		}
		
		for(MultipartFile mf:files) {
			String fileName=mf.getOriginalFilename();
			//dto에 파일저장
			dto.getUris().add(fileName);
			//파일 업로드
			mf.transferTo(new File(dir,fileName));
			
		}
		log.debug(dto);
		eventService.save(dto);
		//완성
		return "/event/reg";
	}


}
