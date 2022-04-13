package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "netflix_titles")
public class NetflixTitle {

	@Id
	private String showId;
	private String type;
	private String title;
	private String director;
	private String cast;
	private String country;
	private String dateAdded;
	private Integer releaseYear;
	private String rating;
	private String duration;
	private String listedIn;
	private String description;
	
}
