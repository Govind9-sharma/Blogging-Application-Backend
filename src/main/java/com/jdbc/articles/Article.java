package com.jdbc.articles;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.jdbc.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id",nullable=false)
	private Long id;
	@NonNull
	private String title;
	@NonNull
	@Column(unique=true)
	private String slug;
	@Nullable
	private String subtitle;
	@NonNull
	private String body;
	@CreatedDate
	private Date createdAt;
	@ManyToOne
	@JoinColumn(name="authorId",nullable=true)
	private User author;
}
