package com.readit.entity;

import java.util.Set;

class Author {
    private String name;
    private int dateOfBirth;
    private int dateOfDeath;
    private Set<Book> book;
    private String biography;

    public Author(String name, int dateOfBirth, int dateOfDeath, Set<Book> book, String biography) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.book = book;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(int dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
