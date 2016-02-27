//package com.readit.dto;
//
//import com.readit.entity.Author;
//import com.readit.entity.Book;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//public class BookDTO {
//    private Long id;
//
//    private String title;
//
//    private String image;
//
//    private Integer year;
//
//    private String description;
//
//    private List<AuthorDTO> author = new ArrayList<AuthorDTO>();
//
//    public BookDTO (Book book) {
//        this.id = book.getId();
//        this.title = book.getTitle();
//        this.image = book.getImage();
//        this.year = book.getYear();
//        this.description = book.getDescription();
//        for (Author a : book.getAuthors()) {
//            this.author.add(new AuthorDTO(a));
//        }
//    }
//}
