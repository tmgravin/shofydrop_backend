package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Category;
import com.shofydrop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/findAll")
    private ResponseEntity<?> findAll() {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<Category> categoryList = categoryService.findAll();
            if (!categoryList.isEmpty()) {
                responseDto.setMessage("successfully data of category is fetched");
                responseDto.setStatus(HttpStatus.ACCEPTED);
                responseDto.setData(categoryService.findAll());
                return ResponseEntity.ok(responseDto);
            } else {
                responseDto.setMessage("Category is empty");
                responseDto.setStatus(HttpStatus.NO_CONTENT);
                responseDto.setData(null);
                return ResponseEntity.ok(responseDto);
            }
        } catch (NullPointerException ex) {
            responseDto.setStatus(HttpStatus.NO_CONTENT);
            responseDto.setMessage(" category is null");
            responseDto.setData(null);
            return ResponseEntity.ok(responseDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setMessage("successfully data is fetched");
            responseDto.setStatus(HttpStatus.ACCEPTED);
            responseDto.setData(categoryService.findById(id));
            return ResponseEntity.ok(responseDto);
        } catch (NullPointerException ex) {
            responseDto.setMessage("No Content ");
            responseDto.setStatus(HttpStatus.NO_CONTENT);
            responseDto.setData(null);
            return ResponseEntity.ok(responseDto);
        }
    }

    @PostMapping("/categorySave")
    public ResponseEntity<?> save(@RequestBody Category category) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setMessage("successfully data is saved");
            responseDto.setStatus(HttpStatus.CREATED);
            responseDto.setData(categoryService.save(category));
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            responseDto.setStatus(HttpStatus.BAD_REQUEST);
            responseDto.setMessage("saving bad data");
            return ResponseEntity.ok(responseDto);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setData(categoryService.update(category, id));
            responseDto.setMessage("successfully data is updated");
            responseDto.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            responseDto.setMessage("bad data update");
            responseDto.setStatus(HttpStatus.NOT_ACCEPTABLE);
            return ResponseEntity.ok(responseDto);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully deleted");
        categoryService.delete(id);
        return ResponseEntity.ok(responseDto);
    }
}
