package lineageM.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//insert 쿼리 시에 null인 칼럼은 쿼리 작성시 빼주세요
@DynamicUpdate //update 쿼리 시에 변경되지 않은 컬럼은 빼주세요
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class JpaBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //mysql auto increament
	private Long no;
	
	@Column(length = 500 ,nullable = false)
	private String subject;
	
	private int count;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;
	

	private String writer;
	
	
	@Column(nullable = false)
	private String user_ip;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime reg_date;

	@Builder
	public JpaBoard(String subject, String content, String writer, String user_ip) {
		this.subject = subject;
		this.content = content;
		this.writer = writer;
		this.user_ip = user_ip;
	}
	
	public JpaBoard update(String subject, String content) {
		this.subject=subject;
		this.content=content;
		return this;
	}
	
	public JpaBoard countIncrement() {
		this.count++;
		return this;
	}
	
	

}
