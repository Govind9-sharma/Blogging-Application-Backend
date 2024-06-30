package com.jdbc.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	private final CommentRepository repository;
	
	public CommentService(CommentRepository repository)
	{
		this.repository=repository;
	}
	
	
}
