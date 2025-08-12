package com.example.booktracker.model;

public class Book {
    private String title;
    private String author;
    private String status;

    // Constructors
    public Book() {}

    public Book(String title, String author, String status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.status = "Available"; // Default status
	}
		// TODO Auto-generated constructor stub
	public Book(String title) {
		this.title = title;
		this.author = "Unknown"; // Default author
		this.status = "Available"; // Default status
	}

	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				", author='" + author + '\'' +
				", status='" + status + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return title.equals(book.title) && author.equals(book.author);
	}

	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + author.hashCode();
		return result;
	}

	// Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
