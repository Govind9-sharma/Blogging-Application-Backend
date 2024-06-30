package com.jdbc.articles;

import org.springframework.stereotype.Service;

import com.jdbc.articles.dtos.CreateArticleRequest;
import com.jdbc.articles.dtos.UpdateArticleRequest;
import com.jdbc.users.UserRepository;
import com.jdbc.users.UserService.UserNotFoundException;

@Service
public class ArticleService {
	
	private final ArticleRepository repository;
	private final UserRepository repository1;
	
	public ArticleService(ArticleRepository repository,UserRepository repository1)
	{
		this.repository=repository;
		this.repository1=repository1;
	}
	
	public Iterable<Article> getAllArticles()
	{
		return repository.findAll();
	}
	
	public Article getArticleById(String slug)
	{
		var article= repository.findBySlug(slug);
		if(article==null)
		{
			throw new ArticleNotFoundException(slug);
		}
		return article;
	}
	
	public Article createArticle(CreateArticleRequest request,Long authorId)
	{
		
		var author=repository1.findById(authorId).orElseThrow(()->new UserNotFoundException(authorId));
		return repository.save(Article.builder().
				        title(request.getTitle())
				        .slug(request.getTitle().toLowerCase().replaceAll("\\s+","-"))//Create a proper Slugification Function
				        .body(request.getBody())
				        .subtitle(request.getSubtitle())
				        .author(author)
				        .build()
				);
	}
	
	public Article updateArticle(Long articleId,UpdateArticleRequest req)
	{
		var article=repository.findById(articleId).orElseThrow(()->new ArticleNotFoundException(articleId));
		
		if(req.getTitle()!=null)
		{
			article.setTitle(req.getTitle());
			article.setSlug(req.getTitle().toLowerCase().replaceAll("\\s+","-"));
		}
		if(req.getBody()!=null)
		{
			article.setBody(req.getBody());
		}
		if(req.getSubtitle()!=null)
		{
			article.setSubtitle(req.getSubtitle());
		}
		return repository.save(article);
	}
	
	
	 static class ArticleNotFoundException extends IllegalArgumentException
	 {
		 public ArticleNotFoundException(String slug)
		 {
			 super("Article "+slug+" Not Found ");
		 }
		 
		 public ArticleNotFoundException(Long id)
		 {
			 super("Article with id ="+id+" Not Found ");
		 }
	 }
}