package com.readit.entity;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Book {
    private String author;
    private String name;
    private int year;
    private String description;

    public Book() {
    }

    public Book(String author, String name, int year, String description) {
        this.author = author;
        this.name = name;
        this.year = year;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}