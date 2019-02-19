package views;

import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import models.Database;
import models.Film;


/*
 * This class is the view of the program, it will control what the gui will look like.
 */
public class View extends JPanel {
	

	private Database database;
	private Database searchDatabase;
	private JButton addButton;
	private JButton saveButton;
	private JButton removeButton;
	private JButton clearButton;
	private JButton searchButton;
	private JList<Film> filmList;
	private JList<Film> searchList;
	private JTextField titleInput;
	private JLabel directorLabel;
	private JTextField directorInput;	
	private JLabel ratingLabel;
	private JTextField ratingInput;
	private JLabel starsLabel;
	private JTextField starsInput;
	private JLabel genreLabel;
	private JTextField genreInput;
	private JTextField search;
	private JLabel searchLabel;
	
	private JScrollPane albumScrollPane;
	private JScrollPane searchScrollPane;
	private JLabel titleLabel;

	public JLabel getTitleLabel() {
		return titleLabel;
	}
	public JLabel getdirectorLabel() {
		return directorLabel;
	}
	public JLabel getSearchLabel() {
		return searchLabel;
	}
	public JLabel getGenreLabel()
	{
		return genreLabel;
	}
	public JTextField getGenre() {
		return genreInput;
	}
	public JTextField getTitle() {
		return titleInput;
	}
	public JTextField getDirector() {
		return directorInput;
	}
	public JScrollPane getAlbumScrollPane(){
		return this.albumScrollPane;
	}
	public JScrollPane getSearchScrollPane(){
		return this.searchScrollPane;
	}
	public JList getFilmList(){
		return filmList;
	}
	public JButton getRemoveButton()
	{
		return this.removeButton;
	}
	public JButton getAddButton()
	{
		return this.addButton;
	}
	public JButton getSaveButton()
	{
		return this.saveButton;
	}
	public JButton getSearchButton()
	{
		return this.searchButton;
	}
	public JButton getClearButton()
	{
		return this.clearButton;
	}
	public JTextField getRating()
	{
		return this.ratingInput;
	}
	public JTextField getStars()
	{
		return this.starsInput;
	}
	public JTextField getsearch()
	{
		return this.search;
	}

	public View(Database db, Database sdb)
	{
		super();

		this.database = db;
		this.searchDatabase = sdb;

		this.setLayout(null);

		this.filmList = new JList<Film>();
		this.searchList = new JList<Film>();
		
		this.filmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.albumScrollPane = new JScrollPane(this.filmList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.searchScrollPane = new JScrollPane(this.searchList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		//Scroll pane
		albumScrollPane.setLocation(10, 10);
		albumScrollPane.setSize(300,200);
		add(albumScrollPane);
		
		//Scroll pane
		searchScrollPane.setLocation(10, 220);
		searchScrollPane.setSize(300,200);
		add(searchScrollPane);

		//Add button
		this.addButton = new JButton("Add");		
		addButton.setLocation(370,220);
		addButton.setSize(100, 30);
		add(addButton);

		//remove button
		this.removeButton = new JButton("Remove");		
		removeButton.setLocation(470,220);
		removeButton.setSize(100, 30);
		add(removeButton);

		//Save button
		this.saveButton = new JButton("Save");		
		saveButton.setLocation(370,260);
		saveButton.setSize(100, 30);
		//add(saveButton);
		
		//search button
		this.searchButton = new JButton("Search");		
		searchButton.setLocation(370,340);
		searchButton.setSize(100, 30);
		add(searchButton);
		
		//clear button
		this.clearButton = new JButton("Clear");		
		clearButton.setLocation(470,340);
		clearButton.setSize(100, 30);
		add(clearButton);
		
		//title label text
		this.titleLabel = new JLabel("Title");
		titleLabel.setLocation(320, 20);
		titleLabel.setSize(100, 30);
		add(titleLabel);

		//input field for title
		this.titleInput = new JTextField();
		titleInput.setLocation(370, 20);
		titleInput.setSize(200, 30);
		add(titleInput);

		//director label text
		this.directorLabel = new JLabel("Director");
		directorLabel.setLocation(320,20+40);
		directorLabel.setSize(100, 30);
		add(directorLabel);

		//input field for director
		this.directorInput = new JTextField();
		directorInput.setLocation(370, 20+40);
		directorInput.setSize(200, 30);
		add(directorInput);

		//Rating Label
		this.ratingLabel = new JLabel("Rating");
		ratingLabel.setLocation(320,20+80);
		ratingLabel.setSize(100, 30);
		add(ratingLabel);

		//input field for rating
		this.ratingInput = new JTextField();
		ratingInput.setLocation(370, 20+80);
		ratingInput.setSize(200, 30);
		add(ratingInput);
		
		//stars label text
		this.starsLabel = new JLabel("Stars");
		starsLabel.setLocation(320,20+120);
		starsLabel.setSize(100, 30);
		add(starsLabel);

		//input field for stars
		this.starsInput = new JTextField();
		starsInput.setLocation(370, 20+120);
		starsInput.setSize(200, 30);
		add(starsInput);
		
		//genre label text
		this.genreLabel = new JLabel("Genre");
		genreLabel.setLocation(320,20+160);
		genreLabel.setSize(100, 30);
		add(genreLabel);

		//input field for genre
		this.genreInput = new JTextField();
		genreInput.setLocation(370, 20+160);
		genreInput.setSize(200, 30);
		add(genreInput);
		
		//title label text
		this.searchLabel = new JLabel("Search");
		searchLabel.setLocation(320, 20+280);
		searchLabel.setSize(100, 30);
		add(searchLabel);
		
		//input field for search
		this.search = new JTextField();
		search.setLocation(370, 20+280);
		search.setSize(200, 30);
		add(search);
		
		//update the window
		this.update();
	}
	//----------------------------------------------------------------------

	 //This method updates the window
	
	public void update()
	{
		//update the view's list of films
		this.getRemoveButton().setEnabled((this.database.getNumberOffilms()>0));
		this.filmList.setListData(this.database.toList());
		this.searchList.setListData(this.searchDatabase.toList());
	}
}
