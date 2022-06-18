package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService userService;
 	

	
	@GetMapping("/AdminLogin")
	public String AdminLogin(Model model) {
		model.addAttribute("title", "Admin|Login");
		return "Admin/AdminLogi";
	}
	@PostMapping("/AdminHome")
	public String AdminHome(Model model) {
		model.addAttribute("title", "Home");
		return "Admin/AdminIndex";
	}
	@GetMapping("/AdminHome")
	public String AdminHome1(Model model) {
		model.addAttribute("title", "Home");
		return "Admin/AdminIndex";
	}
	@GetMapping("/AdminUser")
	public String AdminUser(Model model) {
		model.addAttribute("title", "Users");
		model.addAttribute("user", userService.getAllUserInfo());
		return "Admin/AdminUsers";
	}
	@GetMapping("/AdminProduct")
	public String AdminProduct(Model model) {
		model.addAttribute("title", "Products");
		return "Admin/AdminProduct";
	}
	@GetMapping("/AdminOrders")
	public String AdminOrders(Model model) {
		model.addAttribute("title", "Orders");
		return "Admin/AdminOrders";
	}
	@GetMapping("/BlockOrUnblock")
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
		 return "redirect:/AdminUser";
	}
}
