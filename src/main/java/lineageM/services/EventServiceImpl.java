package lineageM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.EventRequestDto;
import lineageM.domain.entity.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepository;

	@Override
	public void save(EventRequestDto dto) {
		eventRepository.save(dto.toEntity());
		
		
	}

}
