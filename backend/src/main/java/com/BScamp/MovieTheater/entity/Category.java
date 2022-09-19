package com.BScamp.MovieTheater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

	@Id
	@NotBlank(message = "Required")
	private int id;

	@Column(length = 50, nullable = false)
	private String name;

}
