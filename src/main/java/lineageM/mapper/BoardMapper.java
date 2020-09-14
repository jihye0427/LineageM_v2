package lineageM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lineageM.domain.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	//xml의 namespace랑 매퍼 BoardMapper의 경로랑 일치
	//BoardMapper selectBoardList는 xml의 id와 일치
	List<BoardDTO> selectBoardList();

	void insert(BoardDTO dto);

	BoardDTO detail(int no);

	void edit(BoardDTO dto);

	void delete(int no);

	void count(int no);

}
