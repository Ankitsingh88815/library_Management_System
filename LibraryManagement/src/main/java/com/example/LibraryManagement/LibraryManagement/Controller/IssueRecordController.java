package com.example.LibraryManagement.LibraryManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagement.LibraryManagement.Entity.IssuedRecord;
import com.example.LibraryManagement.LibraryManagement.Repository.IssueRecordRepository;
import com.example.LibraryManagement.LibraryManagement.Service.issueRecordService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/issuerecords")
public class IssueRecordController {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @PostMapping("/issuethebook/{bookid}")
    public ResponseEntity<IssuedRecord> issueTheBook(@PathVariable Long bookId){
        return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issuerecordid}")
    public ResponseEntity<IssuedRecord> returnTheBook(@PathVariable Long issueRecordId){
        ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }

}
