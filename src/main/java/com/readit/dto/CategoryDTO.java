package com.readit.dto;

import com.readit.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private CategoryDTO parent;

    private List<CategoryDTO> children;

    public CategoryDTO(Category category, List<Category> children) {
        this.id = category.getId();
        this.name = category.getName();
//        this.parent = new CategoryDTO(category.getParent());
//            this.children.add(new CategoryDTO(c));
    }

}
