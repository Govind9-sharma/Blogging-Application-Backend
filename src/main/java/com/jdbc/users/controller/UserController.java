package com.jdbc.users.controller;

import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.common.dtos.ErrorResponse;
import com.jdbc.users.User;
import com.jdbc.users.UserService;
import com.jdbc.users.UserService.UserNotFoundException;
import com.jdbc.users.dtos.CreateUserRequest;
import com.jdbc.users.dtos.UserResponse;
import com.jdbc.users.dtos.LoginUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private final UserService service;
	private final ModelMapper mapper;
	
	
	public UserController(UserService service,ModelMapper mapper) {
		this.service = service;
		this.mapper=mapper;
	}

	@PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request)
    {
		User savedUser=service.createUser(request);
		URI savedUserUri=URI.create("/users/"+savedUser.getImage());
		return ResponseEntity.created(savedUserUri).body(mapper.map(savedUser, UserResponse.class));
    } //Model Mapper used to map entity with the dto and dto with entity..
	
	@PostMapping("/login")
	ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request)
	{
	     User savedUser=service.loginUser(request.getUsername(), request.getPassword());
         return ResponseEntity.ok(mapper.map(savedUser,UserResponse.class));
	}
	
	@ExceptionHandler({
		UserService.UserNotFoundException.class		
	})
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex)
	{
		
		String message;
		HttpStatus status;
		if(ex instanceof UserService.UserNotFoundException)
		{
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(ErrorResponse.builder()
//							.message(ex.getMessage()).build());
		
		     message=ex.getMessage();
		     status=HttpStatus.NOT_FOUND;
		}else
		{
			 message="Something went wrong .";
		     status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		ErrorResponse response=ErrorResponse.builder().message(message).build();
		return ResponseEntity.status(status).body(response);
	}
}
