package lineageM.domain.dto;

import java.time.LocalDateTime;

import lineageM.domain.entity.JpaBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JpaBoardDTO {
	
	private Long no;
	private String subject;
	private int count;
	private String writer;
	private String content;
	private LocalDateTime reg_date;
	private String user_ip;
	
	
	public JpaBoardDTO(JpaBoard jpaBoard) {
		this.no=jpaBoard.getNo();
		this.subject = jpaBoard.getSubject();
		this.count = jpaBoard.getCount();
		this.writer = jpaBoard.getWriter();
		this.content = jpaBoard.getContent();
		this.reg_date = jpaBoard.getReg_date();
		this.user_ip = jpaBoard.getUser_ip();
	}
	
	public JpaBoard toEntity() {
		return JpaBoard.builder().subject(subject).content(content).user_ip(user_ip).writer(writer).build();
		//return new JpaBoard(subject, writer, user_ip, content) 단, 순서를 지켜야함
	}
	
	

}
