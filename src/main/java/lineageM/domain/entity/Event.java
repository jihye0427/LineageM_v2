package lineageM.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@Table(name="event")
@Getter
@NoArgsConstructor
@Entity
public class Event {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	@Column(nullable = false)
	private String l_text;
	@Column(nullable = false)
	private String l_url;
	@Column(nullable = false)
	private String t_text;
	@Column(nullable = false)
	private String t_url;
	@Column(nullable = false)
	private String b_text;
	@Column(nullable = false)
	private String b_url;
	
	@Column(columnDefinition = "varchar(255) default 'on'")
	private String used;

	@Builder
	public Event(String l_text, String l_url, String t_text, String t_url, String b_text, String b_url) {
		this.l_text = l_text;
		this.l_url = l_url;
		this.t_text = t_text;
		this.t_url = t_url;
		this.b_text = b_text;
		this.b_url = b_url;
	}
	
	

}
