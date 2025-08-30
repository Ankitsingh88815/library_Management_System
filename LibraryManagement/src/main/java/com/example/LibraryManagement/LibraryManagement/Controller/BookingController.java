package com.example.LibraryManagement.LibraryManagement.Controller;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagement.LibraryManagement.DTO.BookingDTO;
import com.example.LibraryManagement.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.LibraryManagement.Service.BookService;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getallbook")
    public ResponseEntity<List<Book>> getAllBook(Book book){
        return ResponseEntity.ok(bookService.getAllBook());
    } 

    @GetMapping("getbookbyid/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookingDTO bookingDTO){
        return ResponseEntity.ok(bookService.updateBook(id,bookingDTO));
    }

    @PostMapping("/addbook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody BookingDTO bookDTO){
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

}
