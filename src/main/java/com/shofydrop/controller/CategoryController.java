
package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Category;
import com.shofydrop.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);


    @GetMapping(value = "/findAll")
    private ResponseEntity<?> findAll() {
        ResponseDto responseDto = new ResponseDto();
        try {
//            List<Category> categoryList = categoryService.findAll();
            responseDto.setMessage("successfully data of category is fetched");
            responseDto.setStatus(HttpStatus.ACCEPTED);
            responseDto.setData(categoryService.findAll());
            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            log.error("Internal Server Error", ex);
            responseDto.setMessage("Internal Server Error");
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
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
        } catch (EntityNotFoundException ex) {
            log.error("Category Not Found Error", ex);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            responseDto.setMessage("Category Not Found");
            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            log.error("Internal Server Error", ex);
            responseDto.setMessage("Internal Server Error");
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
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
        } catch (Exception ex) {
            responseDto.setMessage("Internal Server Error");
            log.error("Internal Server Error", ex);
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
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
        } catch (EntityNotFoundException ex) {
            log.error("Category Not Found Error", ex);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            responseDto.setMessage("Category Not Found");
            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            log.error("Internal Server Error", ex);
            responseDto.setMessage("Internal Server Error");
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.ok(responseDto);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setStatus(HttpStatus.OK);
            responseDto.setMessage("successfully deleted");
            categoryService.delete(id);
            return ResponseEntity.ok(responseDto);
        } catch (EntityNotFoundException ex) {
            log.error("Category Not Found Error", ex);
            responseDto.setStatus(HttpStatus.NOT_FOUND);
            responseDto.setMessage("Category Not Found");
            return ResponseEntity.ok(responseDto);
        } catch (Exception ex) {
            log.error("Internal Server Error", ex);
            responseDto.setMessage("Internal Server Error");
            responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.ok(responseDto);
        }
    }
}