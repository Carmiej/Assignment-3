package models;

import java.util.ArrayList;

import models.Film;

public class Database {
	private ArrayList<Film> films;
	
	//this method returns an array of films back to the caller.
	public Film[] toList()
	{
		Film[] als = new Film[this.films.size()];
		for(int i=0;i<als.length;i++)
		{			
			als[i] = this.films.get(i);
		}

		return als;
	}
	
	public void remove(Film selectedTitle)
	{
		this.films.remove(selectedTitle);	
	}
	//----------------------------------------------------------------------
	public void add(Film a)
	{
		this.films.add(a);
	}
	//----------------------------------------------------------------------
	public int getNumberOffilms()
	{
		return this.films.size();
	}
	//----------------------------------------------------------------------
	public Database()
	{
		this.films = new ArrayList<Film>();

	}
	//----------------------------------------------------------------------
	public Film get(int index) {
		if(index >= 0&&index<this.films.size())
		{
			return this.films.get(index);
		}
		return null;
	}

}
