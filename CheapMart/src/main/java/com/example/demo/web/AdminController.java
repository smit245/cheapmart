package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService userService;
 	

	
	@GetMapping("/admin/login")
	public String AdminLogin(Model model) {
		model.addAttribute("title", "Admin|Login");
		return "admin/AdminLogi";
	}
	@GetMapping("/admin")
	public String AdminMapping() {
		return "redirect:/admin/login";
	}
	
	
	@PostMapping("/admin/AdminHome")
	public String AdminHome(Model model) {
		model.addAttribute("title", "Home");
		return "admin/AdminIndex";
	}
	@GetMapping("/admin/AdminHome")
	public String AdminHome1(Model model) {
		model.addAttribute("title", "Home");
		return "admin/AdminIndex";
	}
	@GetMapping("/admin/AdminUser")
	public String AdminUser(Model model) {
		model.addAttribute("title", "Users");
		model.addAttribute("user", userService.getAllUserInfo());
		return "admin/AdminUsers";
	}
	@GetMapping("/admin/AdminProduct")
	public String AdminProduct(Model model) {
		model.addAttribute("title", "Products");
		return "admin/AdminProduct";
	}
	@GetMapping("/admin/AdminOrders")
	public String AdminOrders(Model model) {
		model.addAttribute("title", "Orders");
		return "admin/AdminOrders";
	}
	@GetMapping("/admin/BlockOrUnblock")
	public String AdminBlockUser(@RequestParam long userId , Model model) 
	{
		User user1 = userService.getUserbyId(userId);
		
		if(user1.getStatus() == 0)
			user1.setStatus(1);
		else
			user1.setStatus(0);
		
		userService.saveUser(user1);
		model.addAttribute("user", userService.getAllUserInfo());
		//userService.delete(userId);
		 return "redirect:/admin/AdminUser";
	}
}
