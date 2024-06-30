package com.jdbc.users;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jdbc.users.dtos.CreateUserRequest;

@Service
public class UserService {

	 private final UserRepository repository; 
	 private final ModelMapper mapper;
	 
	 public UserService(UserRepository repository,ModelMapper mapper)
	 {
		 this.repository=repository;
		 this.mapper=mapper;
	 }
	 
	 public User createUser(CreateUserRequest request)
	 {
		 User newUser=mapper.map(request, User.class);
		 //Encypt The user passWord here TODO
//		 var newUser=User.builder().
//				 username(request.getUsername()).
//				 email(request.getEmail()).
//   			 password(password).
//				 build();
		 return repository.save(newUser);
	 }
	 
	 public User getUser(String username)
	 {
		 return repository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
	 }
	 
	 
	 public User getUser(Long userId)
	 {
		 return repository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
	 }

	 
	 public User loginUser(String username,String password)
	 {
		 var user=repository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
		 if(user==null)
		 {
			 throw new UserNotFoundException(username);
		 }
		 //TODO Match The Password
		 return user;
	 }
	 
	 
	 public static class UserNotFoundException extends IllegalArgumentException
	 {
		 public UserNotFoundException(String username)
		 {
			 super("User "+username+" Not Found ");
		 }
		 
		 public UserNotFoundException(Long userId)
		 {
			 super("User "+userId+" Not Found ");
		 }
	 }
}
