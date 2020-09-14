package lineageM.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.BoardDTO;
import lineageM.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDTO> selectList() {
		// DB연결
		//System.out.println(boardMapper);
		return boardMapper.selectBoardList();
	}

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void insert(BoardDTO dto) {
		dto.setUser_ip(request.getRemoteAddr());
		boardMapper.insert(dto);
		
	}

	@Override
	public BoardDTO detail(int no) {
		
		return boardMapper.detail(no);
	}

	@Override
	public void edit(BoardDTO dto) {
		boardMapper.edit(dto);
	}

	@Override
	public void delete(int no) {
		boardMapper.delete(no);
		
	}

	@Override
	public void count(int no) {
		boardMapper.count(no);
		
	}

}
