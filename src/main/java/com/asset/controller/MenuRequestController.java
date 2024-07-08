package com.asset.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.entity.Menu;
import com.asset.service.MenuService;

@RestController
@CrossOrigin(origins = "${cross_orgin_url}")
@RequestMapping("/api")
public class MenuRequestController {
	 @Autowired
	    MenuService menuService;

	    @GetMapping("/menu")
	    public ResponseEntity<List<Menu>> getAllLoginUsers() {
	        try {
	            List<Menu> menus = new ArrayList<Menu>();
	            menuService.findAllByOrderByIdAsc().forEach(menus::add);
//	            if (menus.isEmpty()) {
//	                return new ResponseEntity<>(menus,HttpStatus.NO_CONTENT);
//	            }
	            return new ResponseEntity<>(menus, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/menu/{id}")
	    public ResponseEntity<Menu> getMenuById(@PathVariable("id") long id) {
	        Optional<Menu> menuData = menuService.findById(id);
	        if (menuData.isPresent()) {
	            return new ResponseEntity<>(menuData.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/menu/role/{name}")
	    public ResponseEntity<List<Menu>> getMenuByRoleName(@PathVariable("name") String roleName) {
	        try {
	            List<Menu> menus = new ArrayList<Menu>();
	            menuService.findByRoleName(roleName).forEach(menus::add);
	            if (menus.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(menus, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }	
	    
	    @PostMapping("/menu")
	    public ResponseEntity<Menu> createMenu(@RequestBody Menu menus) {
	        try {
	            menuService.save(menus);
	            return new ResponseEntity<>(HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PutMapping("/menu/{id}")
	    public ResponseEntity<Menu> updateMenu(@PathVariable("id") long id, @RequestBody Menu menus) {

	        if (menuService.update(menus, id)) {
	            return new ResponseEntity<>(HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/menu/{id}")
	    public ResponseEntity<HttpStatus> deleteMenu(@PathVariable("id") long id) {
	        try {
	            menuService.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/menu")
	    public ResponseEntity<HttpStatus> deleteAllMenus() {
	        try {
	            menuService.deleteAll();
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	    }
}
