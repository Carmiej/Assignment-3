package models;

public class Film {	
	private String title;
	private String director;
	private String rating;
	private String stars;
	private String genre;
	
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getrating() {
		return rating;
	}
	public void setrating(String rating) {
		this.rating = rating;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getdirector() {
		return director;
	}
	public void setdirector(String director) {
		this.director = director;
	}

	public Film(String title,String director, String rating, String stars, String genre)
	{
		this.setTitle(title);
		this.setdirector(director);
		this.setStars(stars);
		this.setrating(rating);
		this.setGenre(genre);
	}
	public String toString(){
		return  this.title+" - "+this.director+" - "+this.rating+" - "+this.stars+" - "+this.genre;
	}
	public String toFile(){
		return  this.title+"\n"+this.director+"\n"+this.rating+"\n"+this.stars+"\n"+this.genre+"\n";
	}
	
}