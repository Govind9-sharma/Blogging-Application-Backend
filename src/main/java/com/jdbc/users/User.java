package com.jdbc.users;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity(name="users")
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id",nullable=false)
	private Long id;
	@Column(nullable=false)
	@NonNull
	private String username;
	@Column(nullable=false)
	@NonNull
	private String email;
	@Column(nullable=true)
	@Nullable
	private String bio;
	@Column(nullable=true)
	@Nullable
	private String image;
	
}