package com.example.demo.web;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.filehandle.FileUploadUtil;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.ProductImage;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductImageService;
import com.example.demo.service.ProductService;
import com.example.demo.session.UserSession;
import com.example.demo.web.dto.ProductFormDto;
import com.example.demo.web.dto.UserRegistrationDto;


@Controller
public class AppController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
	
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@ModelAttribute("categories")
	public List<Category> categories(){
		return categoryService.getAllCategories();
	}
	@ModelAttribute("productForm")
	public ProductFormDto productFormDto() {
		return new ProductFormDto();
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
//		
//		UserSession userSession=(UserSession)auth.getPrincipal();
//		System.out.println("Id:"+userSession.getId());
//		System.out.println("Name:"+userSession.getName());
//		System.out.println("Pincode:"+userSession.getPincode());
		model.addAttribute("title", "Home");
		return "index";
	}
	
	@GetMapping("/about")
	public String viewAboutPage(Model model) {
		model.addAttribute("title", "About Us");
		return "about";
	}
	
	@GetMapping("/contact")
	public String viewContactPage(Model model) {
		model.addAttribute("title", "Contact Us");
		return "contact";
	}
	
	@GetMapping("/addproducts")
	public String viewAddProductPage(Model model) {
		
		model.addAttribute("title", "Add Product");
		model.addAttribute("productFormDto", new ProductFormDto());
		return "addproduct";
	}
	
	@PostMapping("/addproducts")
	public String addProducts(@ModelAttribute("productFormDto") ProductFormDto productFormDto,@RequestParam("images[]") MultipartFile multipartFile[]) throws IOException {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		UserSession userSession=(UserSession)auth.getPrincipal();
		productFormDto.setUser(userSession.getId());
		Product product=productService.saveProduct(productFormDto);
		String uploadDir=Paths.get("src/main/resources/static/img/products/").toAbsolutePath().toString();
		for(int i=0;i<multipartFile.length;i++) {
			ProductImage productImage=new ProductImage();
			String image = String.valueOf(System.currentTimeMillis())+"_"+i+StringUtils.cleanPath(multipartFile[i].getOriginalFilename());
			productImage.setPath(image);
			productImage.setProduct(product);
			productImageService.saveProductImage(productImage);
			FileUploadUtil.saveFile(uploadDir, image, multipartFile[i]);
		}
		return "redirect:/?addprodduct";
	}
	
	@GetMapping("/getcategory")
	@ResponseBody
	public Category getCategory(@RequestParam("id") long id) {
		Category category=categoryService.getCategoryById(id);
		return category;
	}
}