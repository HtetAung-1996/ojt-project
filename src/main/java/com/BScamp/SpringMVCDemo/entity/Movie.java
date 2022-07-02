package com.BScamp.SpringMVCDemo.entity;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movie_id;
	@Column(nullable = false)
	private String adult;
	@Column(length = 50, nullable = false, unique = true)
	private String original_title;
	private String poster_path;
	private String budget;
	private String homepage;
	@Column(length = 500)
	private String trailer;
	@Column(length = 1000)
	private String overview;
	private String type;

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", adult=" + adult + ", original_title=" + original_title
				+ ", poster_path=" + poster_path + ", budget=" + budget + ", homepage=" + homepage + ", trailer="
				+ trailer + ", overview=" + overview + ", type=" + type + "]";
	}

}
