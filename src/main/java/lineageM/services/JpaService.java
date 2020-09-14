package lineageM.services;

import java.util.List;

import lineageM.domain.dto.JpaBoardDTO;

public interface JpaService {

	List<JpaBoardDTO> list();

	void save(JpaBoardDTO dto);

	JpaBoardDTO getDetail(Long no);

	void edit(JpaBoardDTO dto);

	void delete(Long no);

}
