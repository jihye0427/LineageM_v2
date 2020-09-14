package lineageM.domain.dto;

import java.util.List;
import java.util.Vector;

import lineageM.domain.entity.Event;
import lombok.Data;

@Data
public class EventRequestDto {
	private String l_text;
	private String t_text;
	private String b_text;
	
	List<String>uris=new Vector<>();
	
	public Event toEntity() {
		return Event.builder().l_text(l_text).t_text(t_text).b_text(b_text).l_url(uris.get(0)).t_url(uris.get(1)).b_url(uris.get(2)).build();
	}

}
