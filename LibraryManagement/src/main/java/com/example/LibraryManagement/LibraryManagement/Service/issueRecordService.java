package com.example.LibraryManagement.LibraryManagement.Service;

import java.time.LocalDate;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.LibraryManagement.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.LibraryManagement.Entity.IssuedRecord;
import com.example.LibraryManagement.LibraryManagement.Entity.User;
import com.example.LibraryManagement.LibraryManagement.Repository.BookRepository;
import com.example.LibraryManagement.LibraryManagement.Repository.IssueRecordRepository;
import com.example.LibraryManagement.LibraryManagement.Repository.UserRepository;

@Service
public class issueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private IssuedRecord issueTheBook(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("No book found for this id "+bookId));
        if(book.getQuantity()<=0 || !book.getIsAvailable()){
            throw new RuntimeException("Book is not available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                    .orElseThrow(()-> new RuntimeException("User not found"));
        
        IssuedRecord issuedRecord = new IssuedRecord();
        issuedRecord.setIssueDate(LocalDate.now());
        issuedRecord.setDueDate(LocalDate.now().plusDays(10));
        issuedRecord.setIsReturn(false);
        issuedRecord.setUser(user);
        issuedRecord.setBook(book);
        
        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0){
            book.setIsAvailable(false);
        }
        bookRepository.save(book);
        return issueRecordRepository.save(issuedRecord);
    }

    public IssuedRecord returnTheBook(Long issueRecordId){
        IssuedRecord issuedRecord = issueRecordRepository.findById(issueRecordId)
                            .orElseThrow(()-> new RuntimeException("issue record not found"));
        if(issuedRecord.getIsReturn()){
            throw new RuntimeException("book is already returned");
        }

        Book book = issuedRecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setIsAvailable(true);
        bookRepository.save(book);

        issuedRecord.setReturnDate(LocalDate.now());
        issuedRecord.setIsReturn(true);

        return issueRecordRepository.save(issuedRecord);
    }
}
