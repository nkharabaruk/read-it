package com.readit.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.readit.entity.AbstractEntity;
import com.readit.entity.Book;
import com.readit.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO extends AbstractEntity {
    private String name;
    private List<Long> books = new ArrayList<>();

    public static CategoryDTO from(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.name = category.getName();
        dto.books = category.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        return dto;
    }
}
