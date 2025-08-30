package com.example.LibraryManagement.LibraryManagement.DTO;

import lombok.Data;

@Data
public class BookingDTO {

    private String title;
    private String author;
    private Long quantity;
    private String ISDN;
    private Boolean isAvailable;


}
