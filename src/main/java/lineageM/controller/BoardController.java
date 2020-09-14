package lineageM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.BoardDTO;
import lineageM.services.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public ModelAndView board(ModelAndView mv) {
		//DB에 가서 데이터 읽어오자 -Service로~
		
		List<BoardDTO> boardList=boardService.selectList();
		mv.setViewName("/board/list");//html경로 (templates 아래)
		mv.addObject("boardList",boardList); //page에 갖고갈 data
		return mv; 
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "/board/write";
	}
	
	@PostMapping("/board/write")
	public String write(BoardDTO dto) {
		boardService.insert(dto);
		return "redirect:/board/list";
	}
	
	//no의 게시글 정보를 갖고와서 detail 페이지에 보내서 보여준다.
	@GetMapping("/board/detail/{no}")
	public ModelAndView detail(@PathVariable int no) {
		ModelAndView mv= new ModelAndView("/board/detail");
		BoardDTO result=boardService.detail(no);
		boardService.count(no);
		mv.addObject("dto",result); //페이지로 넘어갈 detail
		return mv;
		
	}
	
	@PostMapping("/board/edit")
	public String edit(BoardDTO dto) {
		//요청된 데이터를 db로 보내서 수정처리
		boardService.edit(dto);
		return "redirect:/board/detail/"+dto.getNo();
		
	}
	
	@GetMapping("/board/delete/{no}")
	public String delete(@PathVariable int no) {
		//요청된 no 값을 data를 삭제
		boardService.delete(no);
		return "redirect:/board/list";
		
	}

}
