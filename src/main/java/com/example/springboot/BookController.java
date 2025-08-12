package com.example.springboot;

import com.example.booktracker.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*") // Allow frontend to call this API
//@CrossOrigin(origins = "http://localhost:8080")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    //http://localhost:8080/books/add
    @PostMapping("/add")
    public List<Book> addBook(@RequestBody Book book) {
    		System.out.println("Book Added " + book.getTitle() +" "+ book.getAuthor());
        books.add(book);
        return books;
    }
    //http://localhost:8080/books/list
    @GetMapping("/list")
    public List<Book> getBooks() {
    		System.out.println("Inside List of Books...");
    		if(!books.isEmpty())
    			System.out.println("Added Book Named : " + books.get(0).getTitle() +" by "+ books.get(0).getAuthor());
        return books;
    }
    
    @GetMapping("/preetham")
    public List<Book> getSome(@RequestParam String some) {
		// This is just a sample endpoint to demonstrate how to handle a request with a parameter
		System.out.println("Received request with parameter: " + some);
		if (books.isEmpty()) {
			books.add(new Book(some, "Sample Author"));
		}
        return books;
    }
    
    /* The client side HTMK program to invoke the API is as follows:
     * <!DOCTYPE html>
		<html>
		<head>
		  <title>Book Manager (with Status)</title>
		</head>
		<body>
		
		  <h2>Add Book</h2>
		  <input id="title" placeholder="Title" />
		  <input id="author" placeholder="Author" />
		  <input id="status" placeholder="Status" />
		  <button onclick="addBook()">Add Book</button>
		
		  <h2>Book List</h2>
		  <button onclick="loadBooks()">Refresh List</button>
		  <div id="bookList">No books yet.</div>
		
		  <script>
		    const apiUrl = 'http://localhost:8080/books';
		
		    async function addBook() {
		      const title = document.getElementById('title').value.trim();
		      const author = document.getElementById('author').value.trim();
		      const status = document.getElementById('status').value.trim();
		
		      if (!title || !author || !status) {
		        alert('Please enter title, author, and status');
		        return;
		      }
		
		      try {
		        const response = await fetch(apiUrl + '/add', {
		          method: 'POST',
		          headers: { 'Content-Type': 'application/json' },
		          body: JSON.stringify({ title, author, status })
		        });
		
		        if (!response.ok) throw new Error('Failed to add book');
		
		        document.getElementById('title').value = '';
		        document.getElementById('author').value = '';
		        document.getElementById('status').value = '';
		        loadBooks();
		
		      } catch (err) {
		        alert('Error adding book');
		        console.error(err);
		      }
		    }
		
		    async function loadBooks() {
		      try {
		        const response = await fetch(apiUrl + '/list');
		        if (!response.ok) throw new Error('Failed to fetch books');
		        const books = await response.json();
		
		        if (books.length === 0) {
		          document.getElementById('bookList').innerText = 'No books yet.';
		          return;
		        }
		
		        document.getElementById('bookList').innerHTML = books
		          .map(book => `<div><strong>${book.title}</strong> by ${book.author} — Status: ${book.status}</div>`)
		          .join('');
		
		      } catch (err) {
		        document.getElementById('bookList').innerText = 'Error loading books';
		        console.error(err);
		      }
		    }
		
		    loadBooks();
		  </script>
		
		</body>
		</html>
     */
}
