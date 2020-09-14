package lineageM.services;

import java.util.List;

import lineageM.domain.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> selectList();

	void insert(BoardDTO dto);

	BoardDTO detail(int no);

	void edit(BoardDTO dto);

	void delete(int no);

	void count(int no);

}
