package com.aem.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class})
public class BooksModel {

    private String test;
    private List<Book> books;

    @PostConstruct
    protected void init() {
        setTest("I am from Test");
        setBooks();
    }

    public String getTest() {
        return test;
    }

    private void setTest(String test) {
        this.test = test;
    }

    public List<Book> getBooks() {
        return books;
    }

    private void setBooks() {
        books = new ArrayList<>();

        Book book = new Book();
        book.setBook("Book 1");
        book.setSubject("Subject 1");
        books.add(book);

        Book book2 = new Book();
        book2.setBook("Book 2");
        book2.setSubject("Subject 2");
        books.add(book2);
    }
}
