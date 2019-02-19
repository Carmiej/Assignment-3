package controllers;

import java.util.Scanner;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.Database;
import models.Film;
import views.View;

public class AppController extends JFrame{

	//the model
	private Database FilmDatabase;
	private Database searchDatabase;
	private Film Selection;
	//the "window"
	private View view;

	public AppController(String title) throws IOException
	{
		super(title);
		this.Selection = null;
		this.FilmDatabase = new Database();
		this.searchDatabase = new Database();
		this.loadFile();
		this.view = new View(this.FilmDatabase, this.searchDatabase);
		
		getContentPane().add(this.view);

		//Dimentions of the Jpane
		setSize(700,525);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//The Action listeners.
		view.getAddButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					eventHandlerAddButton();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});

		view.getRemoveButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandlerRemoveButton();	
			}
		});
		
		view.getSaveButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					eventHandlerSaveButton();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		view.getSearchButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandlerSearchButton();	
			}
		});
		
		view.getClearButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandlerClearButton();	
			}
		});

		view.getFilmList().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				eventHandlerUpdateTitleArtistFields();
			}

		});
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				try {
					eventHandlerSaveButton();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});

	}
	
	//the event handler for the add button that checks the JText fields and if it meets the requirements will add the film to the jlist.
	private void eventHandlerAddButton() throws FileNotFoundException
	{
		String title=view.getTitle().getText();
		String director=view.getDirector().getText();
		String rating=view.getRating().getText();
		String stars=view.getStars().getText();
		String genre=view.getGenre().getText();

		if(!(title.isEmpty()||director.isEmpty()||rating.isEmpty()||stars.isEmpty()))
		{
			Film film = new Film(title, director, rating, stars, genre);
			this.FilmDatabase.add(film);
			this.clearAlbumInput();
		}		
		view.update();
	}

	//this clears the input in the JTextFields.
	private void clearAlbumInput()
	{
		this.view.getTitle().setText("");
		this.view.getDirector().setText("");
		this.view.getRating().setText("");
		this.view.getStars().setText("");
		this.view.getGenre().setText("");
	}

	//this will update the JTextFields with the information of the film selected
	private void eventHandlerUpdateTitleArtistFields()
	{

		this.Selection = 
				(Film) this.view.getFilmList().getSelectedValue();
		 
		
		if(Selection!=null)
		{
			this.view.getTitle().setText(Selection.getTitle());
			this.view.getDirector().setText(Selection.getdirector());
			this.view.getRating().setText(Selection.getrating());
			this.view.getStars().setText(Selection.getStars());
			this.view.getGenre().setText(Selection.getGenre());
		}
		else{

			clearAlbumInput();
		}
		view.update();
	}
	
	//this will remove a film from the jlist
	private void eventHandlerRemoveButton()
	{
		this.FilmDatabase.remove(Selection);
		this.Selection = null;
		clearAlbumInput();

		view.update();
	}
	
	private void eventHandlerClearButton()
	{
		this.clear();

		view.update();
	}
	
	private void clear()
	{
		int i = 0;
		while (i<searchDatabase.getNumberOffilms())
		{
			this.searchDatabase.remove(searchDatabase.get(0));
		}
	}
	
	private void eventHandlerSearchButton()
	{		
		int i = 0;
		while (i<searchDatabase.getNumberOffilms())
		{
			this.searchDatabase.remove(searchDatabase.get(0));
		}
		
		int counter = 0;
		
		Film film;
		
		if (this.view.getsearch().getText() != null)
		{
		
			while (counter < this.FilmDatabase.getNumberOffilms())
			{
				film = FilmDatabase.get(counter);
				String search=view.getsearch().getText();
			
				if (film.getTitle().toLowerCase().contains(search.toLowerCase()))
				{
					searchDatabase.add(FilmDatabase.get(counter));
				}
				else if (film.getdirector().toLowerCase().contains(search.toLowerCase()))
				{
					searchDatabase.add(FilmDatabase.get(counter));
				}
				else if (film.getStars().toLowerCase().contains(search.toLowerCase()))
				{
					searchDatabase.add(FilmDatabase.get(counter));
				}
				else if (film.getrating().toLowerCase().contains(search.toLowerCase()))
				{
					searchDatabase.add(FilmDatabase.get(counter));
				}
				else if (film.getGenre().toLowerCase().contains(search.toLowerCase()))
				{
					searchDatabase.add(FilmDatabase.get(counter));
				}
				++counter;
			}
		}
		

		view.update();
	}

	public static void main(String[] args) throws IOException
	{
		JFrame app = new AppController("Film Database");
		app.setVisible(true);
	}
	
	//This method loads in the textfile and adds all of the contents to the JList.
	private void loadFile() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("files/Films.txt"));
		
		int numberOfFilms = 0;
		String title;
		String director;
		String rating;
		String stars;
		String genre;
		
		numberOfFilms = scanner.nextInt();
		title = scanner.nextLine();
		while (numberOfFilms > 0)
		{
			title = scanner.nextLine();
			director = scanner.nextLine();
			rating = scanner.nextLine();
			stars = scanner.nextLine();
			genre = scanner.nextLine();
					
			
			
			Film film = new Film(title, director, rating, stars, genre);
			this.FilmDatabase.add(film);
			--numberOfFilms;
		}
		scanner.close();
	}
	
	//This method saves all films that are in the JList to a text file.
	private void saveFile() throws IOException
	{
		int sizeOfDatabase;
		
		File file = new File("files/Films.txt");
		
		int counter = 0;
		int films = FilmDatabase.getNumberOffilms();
		Film[] als;
		
		PrintWriter out = new PrintWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(out);
		
		als = FilmDatabase.toList();
		
		bw.write(String.valueOf(als.length));
		bw.write("\n");
		
		while (counter < films)
		{
			bw.write(als[counter].toFile());
			++counter;
		}
		bw.close();
		out.close();
		
	}
	
	//calls the save method.
	private void eventHandlerSaveButton() throws IOException
	{
		this.saveFile();
	}

}
