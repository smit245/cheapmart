//package com.example.demo.web;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.demo.model.Category;
//import com.example.demo.service.CategoryService;
//import com.example.demo.web.dto.UserRegistrationDto;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//	@Autowired
//	CategoryService categoryService;
//	
//	@ModelAttribute("user")
//	public UserRegistrationDto userRegistrationDto() {
//		return new UserRegistrationDto();
//	}
//	
//	@ModelAttribute("categories")
//	public List<Category> categories(){
//		return categoryService.getAllCategories();
//	}
//	
//	
//	@RequestMapping("/error")
//	public String handleError() {
//		return "error";
//	}
//	
//	
//}
