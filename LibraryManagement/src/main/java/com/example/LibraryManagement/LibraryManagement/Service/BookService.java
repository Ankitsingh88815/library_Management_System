package com.example.LibraryManagement.LibraryManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagement.LibraryManagement.DTO.BookingDTO;
import com.example.LibraryManagement.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.LibraryManagement.Repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        Book book = bookRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Book not found "));
            return book;
    }

    public Book addBook(BookingDTO bookingDTO){
        Book book = new Book();
        book.setTitle(bookingDTO.getTitle());
        book.setAuthor(bookingDTO.getAuthor());
        book.setISDN(bookingDTO.getISDN());
        book.setIsAvailable(bookingDTO.getIsAvailable());
        book.setQuantity(bookingDTO.getQuantity());

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookingDTO bookingDTO){

        Book oldbook = bookRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("Book not found"));
        oldbook.setTitle(bookingDTO.getTitle());
        oldbook.setAuthor(bookingDTO.getAuthor());
        oldbook.setISDN(bookingDTO.getISDN());
        oldbook.setIsAvailable(bookingDTO.getIsAvailable());
        oldbook.setQuantity(bookingDTO.getQuantity());

        return bookRepository.save(oldbook);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

}
