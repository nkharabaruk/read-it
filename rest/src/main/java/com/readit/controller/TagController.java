package com.readit.controller;

import com.readit.entity.Tag;
import com.readit.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping("/{tagId}")
    public Tag getTagById(@PathVariable long tagId) {
        return tagService.findById(tagId);
    }

    @PostMapping
    public Tag saveTag(@RequestBody Tag tag) {
        return tagService.save(tag);
    }

    @DeleteMapping("/all")
    public void deleteAllTags() {
        tagService.deleteAll();
    }

    @DeleteMapping
    public void deleteTag(@RequestBody Tag tag) {
        tagService.delete(tag);
    }
}
