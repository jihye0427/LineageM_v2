package lineageM.services;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.JpaBoardDTO;
import lineageM.domain.entity.JpaBoard;
import lineageM.domain.entity.JpaBoardRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JpaServiceImpl implements JpaService {
	@Autowired
	private JpaBoardRepository jpaBoardRepository;

	@Override
	public List<JpaBoardDTO> list() {
		List<JpaBoard> result=jpaBoardRepository.findAll();
		List<JpaBoardDTO> list=new Vector<>();
		for(JpaBoard jpaBoard:result) {
			JpaBoardDTO dto= new JpaBoardDTO(jpaBoard);
			list.add(dto);
			
		}
		return list;
	}

	@Override
	public void save(JpaBoardDTO dto) {
		//dto-entity로 변환
		
		jpaBoardRepository.save(dto.toEntity());
		
		
	}

	@Transactional
	@Override
	public JpaBoardDTO getDetail(Long no) {
		//Optional<JpaBoard> op=jpaBoardRepository.findById(no);
		//if(op.isPresent()) {
			//JpaBoard jpaBoard=op.get();
			//return new JpaBoardDTO(jpaBoard);
		//}else {
			//return null;
		//}
		
		//jpaBoardRepository.findById(no) Optional 리턴시
		// JpaBoard entity가 나올것이라 단정하고 코딩할 수 있다.
		//null 인 경우에 처리는 orElse()로 
	
		JpaBoard result=jpaBoardRepository.findById(no)
				.map(e->e.countIncrement()) //조회수 증가, 데이터 수정
				.orElse(null);
		return new JpaBoardDTO(result);
	}

	@Transactional
	@Override
	public void edit(JpaBoardDTO dto) {
		
		//DB에 있는 원래의 데이터
		  JpaBoard board=jpaBoardRepository.findById(dto.getNo()).orElse(null);
		  //수정할 데이터만 수정
		  board.update(dto.getSubject(), dto.getContent()); 
		  log.debug("dto : "+ dto);
		  //jpaBoardRepository.save(board);
		 
		
		
	}

	@Override
	public void delete(Long no) {
		jpaBoardRepository.deleteById(no);
		
	}

}
