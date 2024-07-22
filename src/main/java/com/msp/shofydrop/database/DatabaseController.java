package com.msp.shofydrop.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database")
public class DatabaseController
{
	@Autowired
	private ProcedureService procedureService;

    @GetMapping(value = "/")
    public ResponseEntity<?> findAll(@RequestParam(name="id", required=false) Integer id)
    {
    	System.out.println("findAll ");
        try
        {
        	List<Users1> u = procedureService.getUsers(id);
            return ResponseEntity.ok().body(u);
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getLocalizedMessage());
        }
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestBody Users1 user)
    {
    	System.out.println("save");
        try
        {
            return ResponseEntity.ok().body(procedureService.saveUser(user));
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getLocalizedMessage());
        }
    }
    
    @PostMapping(value = "/test")
    public ResponseEntity<?> test()
    {
    	System.out.println("test");
        try
        {
            return ResponseEntity.ok().body("test");
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getLocalizedMessage());
        }
    }
}
