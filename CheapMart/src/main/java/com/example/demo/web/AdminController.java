package com.example.demo.web;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.filehandle.FileUploadUtil;
import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.AdminLoginDto;
import com.example.demo.web.dto.CategoryFormDto;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AdminService adminService;
	
	
	@ModelAttribute("AdminLogin")
	public AdminLoginDto adminLoginDto() {
		return new AdminLoginDto();
	}
 	
//	private boolean isAuthenticated() {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication == null || AnonymousAuthenticationToken.class.
//	      isAssignableFrom(authentication.getClass())) {
//	        return false;
//	    }
//	    return authentication.isAuthenticated();
//	}
	private boolean isAuthenticated(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		if(session.getAttribute("Admin")!=null) {
			return (boolean)session.getAttribute("Admin");
		}
		return false;
		
	}
	
	@GetMapping("/admin/login")
	public String AdminLogin(Model model,HttpServletRequest request) {
		model.addAttribute("title", "Admin|Login");
		 if (isAuthenticated(request)) {
		        return "redirect:/admin/AdminHome";
		 }
		return "admin/AdminLogi";
	}
	
	@PostMapping("/admin/login")
	public String AdminLoginAuth(@ModelAttribute("AdminLogin") AdminLoginDto adminLoginDto,HttpServletRequest request) {
		List<Admin> admin=adminService.getAdminByEmail(adminLoginDto.getEmail());
		
		for(int i=0; i<admin.size();i++) {
			if(admin.get(i).getEmail().equalsIgnoreCase(adminLoginDto.getEmail())) {
				if(admin.get(i).getPassword().equals(adminLoginDto.getPassword())) {
					HttpSession session=request.getSession();
					session.setAttribute("Admin", true);
					return "redirect:/admin/AdminHome";
				}else {
					return "redirect:/admin/login?error=true";
				}
			}
		}
		return "redirect:/admin/login?error=true";
	}
	
	@GetMapping("/admin")
	public String AdminMapping() {
		return "redirect:/admin/login";
	}
	
	@GetMapping("/admin/AdminHome")
	public String AdminHome(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Home");
			return "admin/AdminIndex";
		}
		return "redirect:/admin/login?denied=true";
	}
	@GetMapping("/admin/AdminUser")
	public String AdminUser(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Users");
			model.addAttribute("user", userService.getAllUserInfo());
			return "admin/AdminUsers";
		}
		return "redirect:/admin/login?denied=true";
	}
	@GetMapping("/admin/AdminProduct")
	public String AdminProduct(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Products");
			return "admin/AdminProduct";
		}
		return "redirect:/admin/login?denied=true";
		
	}
	@GetMapping("/admin/AdminOrders")
	public String AdminOrders(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Orders");
			return "admin/AdminOrders";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	@GetMapping("/admin/AdminCategory")
	public String AdminCategories(@ModelAttribute("categoryForm") CategoryFormDto categoryFormDto ,Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			//making label Map
			Map<Integer,Map<String,String>> label= new HashMap<Integer,Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("class","label-success");
			map.put("text", "Active");
			label.put(0, map);
			map = new HashMap<String, String>();
			map.put("class","label-danger");
			map.put("text", "Inative");
			label.put(1, map);
			model.addAttribute("label", label);
			model.addAttribute("title", "Categories");
			model.addAttribute("Categories", categoryService.getAllCategories());
			return "admin/AdminCategory";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	@PostMapping("/admin/addcategory")
	public String addCatgeory(@ModelAttribute("categoryForm") CategoryFormDto categoryFormDto,@RequestParam("img") MultipartFile multipartFile) throws IOException {
		
		String image = String.valueOf(System.currentTimeMillis())+StringUtils.cleanPath(multipartFile.getOriginalFilename());
		categoryFormDto.setImage(image);
		categoryService.save(categoryFormDto);
		String uploadDir=Paths.get("src/main/resources/static/admin/img/category/").toAbsolutePath().toString();
		FileUploadUtil.saveFile(uploadDir, image, multipartFile);
		return "redirect:/admin/AdminCategory";
		
	}
	@GetMapping("/admin/BlockOrUnblock")
	public String AdminBlockUser(@RequestParam long userId , Model model,HttpServletRequest request) 
	{
		
		if(isAuthenticated(request)) {
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
		return "redirect:/admin/login?denied=true";
	}
	
	@GetMapping("/admin/logout")
	public String AdminLogout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/admin/login?loggedout=true";
	}
}
