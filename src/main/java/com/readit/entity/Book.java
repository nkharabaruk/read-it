package com.readit.entity;

import java.util.Set;

class Book {
    private Set<Author> author;
    private String name;
    private int year;
    private String description;

    public Book(Set<Author> author, String name, int year, String description) {
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

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }
}
