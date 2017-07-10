package com.readit.integration.client;

import com.readit.entity.AbstractEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Client<T extends AbstractEntity> {

    @GetMapping("/{id}")
    T get(@PathVariable("id") long id);

    @GetMapping
    List<T> get();

    @PostMapping
    T save(@RequestBody T entity);

    @PutMapping("/{id}")
    T update(@PathVariable("id") long id, @RequestBody T entity);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") long id);

    @DeleteMapping
    void delete();
}
