package lineageM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lineageM.domain.dto.JpaBoardDTO;
import lineageM.services.JpaService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class JpaController {
	@Autowired
	private JpaService jpaService;
	
	@GetMapping("/jpa/list")
	public String jpaList(Model model) {
		//DB에서 list 갖고와서, 페이지로 보냅시다
		List<JpaBoardDTO> list=jpaService.list();
		model.addAttribute("jpaList", list);
		return "/jpa/list";
	}
	
	@GetMapping("/jpa/write")
	public String write() {
		return "/jpa/write";
		
	}
	
	@PostMapping("/jpa/write")
	public String write(JpaBoardDTO dto, HttpServletRequest request) {
		log.info("dto: "+dto);
		//db에 dto를 저장하고
		dto.setUser_ip(request.getRemoteAddr());
		jpaService.save(dto);
		
		//저장 후 페이지 이동
		return "redirect:/jpa/list";
		
	}
	
	@GetMapping("/jpa/detail/{no}")
	public String detail(@PathVariable Long no, Model model) {
		//no의 해당하는 상세정보를 갖고와~
		JpaBoardDTO detail=jpaService.getDetail(no);
		model.addAttribute("dto",detail);
		//상세정보 저장하고 페이지 이동
		return "/jpa/detail";
	}
	
	@PostMapping("/jpa/edit")
	public String edit(JpaBoardDTO dto) {
		//no, subject, content가 넘어옴
		// 수정해주세용
		jpaService.edit(dto);
		
		//수정 후 상세페이지로 이동! 수정 됐는지 확인
		return "redirect:/jpa/detail/"+dto.getNo();
		
	}
	
	@GetMapping("/jpa/delete/{no}")
	public String delete(@PathVariable Long no) {
		jpaService.delete(no);
		return "redirect:/jpa/list";
	}

}
