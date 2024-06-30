package com.jdbc.comments;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.jdbc.articles.Article;
import com.jdbc.users.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id",nullable=false)
	private Long id;
	@Nullable
	private String title;
	@NonNull
	private String body;
	@CreatedDate
	private Date createdAt;
	@ManyToOne
	@JoinColumn(name="articleId",nullable=false)
	private Article article;
	@ManyToOne
	@JoinColumn(name="authorId",nullable=false)
	private User author;
}
