package com.jdbc.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.jdbc.articles.ArticleRepository;
@DataJpaTest
@ActiveProfiles("test")
public class ArticleRepositoryTest {
	

	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	@Order(1)
	void can_article_create()
	{
		var article=Article.builder().
				title("Title").
				author(null).
				body("This is the body").
				createdAt(null).
				slug("This is the Slug").build();
		articleRepository.save(article);
 	}
}
