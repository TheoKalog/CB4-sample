package librarian;

public class Book {

	private String title;
	private Author author;
	private final String isbn;
	private int physicalCopies;
	private int availableCopies;
	private int timesRented;

	public Book(String title, Author author, String isbn, int physicalCopies, int availableCopies, int timesRented) {

		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.physicalCopies = physicalCopies;
		this.availableCopies = availableCopies;
		this.timesRented = timesRented;
	}

	/*
	 * public Book(String title,Author author,String isdn,int physicalCopies,int
	 * availableCopies,int timesRented) {
	 * 
	 * this.title=title; this.author=author; this.isbn=isbn;
	 * this.physicalCopies=physicalCopies; this.availableCopies=availableCopies;
	 * this.timesRented=timesRented; }
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPhysicalCopies() {
		return physicalCopies;
	}

	public void setPhysicalCopies(int physicalCopies) {
		this.physicalCopies = physicalCopies;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public int getTimesRented() {
		return timesRented;
	}

	public void setTimesRented(int timesRented) {
		this.timesRented = timesRented;
	}

	public String getIsbn() {
		return isbn;
	}

	
	
	
	public Boolean isAvailable(Boolean result) {

		if (availableCopies > 1) {
			result = true;
		} else {
			result = false;
		}
		return result;

	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author.getAuthorName() + ", isbn=" + isbn + ", physicalCopies=" + physicalCopies
				+ ", availableCopies=" + availableCopies + ", timesRented=" + timesRented + "]";
	}
	
	
	
	/*public Boolean hasAuthor(Boolean check) {
		if()
		
		
	}*/
	
	
	
	
	

}
