package com.readit.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.readit.entity.AbstractEntity;
import com.readit.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryTree extends AbstractEntity {
    private String name;
    private List<CategoryTree> children;

    public static CategoryTree fromCategory(Category category) {
        CategoryTree categoryTree = new CategoryTree();
        categoryTree.setId(category.getId());
        categoryTree.name = category.getName();
        categoryTree.children = category.getChildren().stream().map(CategoryTree::fromCategory).collect(Collectors.toList());
        return categoryTree;
    }
}
