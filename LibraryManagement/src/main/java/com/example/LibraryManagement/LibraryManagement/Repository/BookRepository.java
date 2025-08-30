package com.example.LibraryManagement.LibraryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibraryManagement.LibraryManagement.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
