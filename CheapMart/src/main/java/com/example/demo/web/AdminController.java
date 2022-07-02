package com.example.demo.web;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.demo.filehandle.FileUploadUtil;
import com.example.demo.model.Admin;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.SubCategory;
import com.example.demo.model.User;
import com.example.demo.repos.ProductRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SubCategoryService;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.AdminLoginDto;
import com.example.demo.web.dto.CategoryFormDto;
import com.example.demo.web.dto.SubCategoryFormDto;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	
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
	
	//Users Routes
	@GetMapping("/admin/AdminUser")
	public String AdminUser(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Users");
			model.addAttribute("user", userService.getAllUserInfo());
			return "admin/AdminUsers";
		}
		return "redirect:/admin/login?denied=true";
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
	//End Users routes
	
	
	@GetMapping("/admin/AdminProduct")
	public String AdminProduct(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Products");
			model.addAttribute("product", productService.getAllProductInfo());
			return "admin/AdminProduct";
		}
		return "redirect:/admin/login?denied=true";
		
	}
	@GetMapping("/admin/BlockOrUnblockProduct")
	public String AdminBlockProduct(@RequestParam long productId , Model model,HttpServletRequest request) 
	{
		
		if(isAuthenticated(request)) {
			Product product = productService.getProductbyId(productId);
			
			if(product.getStatus() == 0)
				product.setStatus(1);
			else
				product.setStatus(0);
			
			productRepository.save(product);
			model.addAttribute("product", productService.getAllProductInfo());
			//userService.delete(userId);
			 return "redirect:/admin/AdminProduct";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	@GetMapping("/admin/AdminOrders")
	public String AdminOrders(Model model,HttpServletRequest request) {
		if(isAuthenticated(request)) {
			model.addAttribute("title", "Orders");
			model.addAttribute("orderr", orderService.getAllOrderInfo());
			return "admin/AdminOrders";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	//Catgeory Routes
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
			Map<String, ?>  inputFlashMap= RequestContextUtils.getInputFlashMap(request);
			if(inputFlashMap != null){
				Set<String> key=inputFlashMap.keySet();
				model.addAttribute(key.toString(), (String)inputFlashMap.get(key.toString()));
			}
			model.addAttribute("label", label);
			model.addAttribute("title", "Categories");
			model.addAttribute("Categories", categoryService.getAllCategories());
			return "admin/AdminCategory";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	@PostMapping("/admin/addcategory")
	public String addCatgeory(@ModelAttribute("categoryForm") CategoryFormDto categoryFormDto,@RequestParam("img") MultipartFile multipartFile,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) throws IOException {
		
		String image = String.valueOf(System.currentTimeMillis())+StringUtils.cleanPath(multipartFile.getOriginalFilename());
		categoryFormDto.setImage(image);
		categoryFormDto.setStatus(0);
		categoryService.save(categoryFormDto);
		String uploadDir=Paths.get("src/main/resources/static/admin/img/category/").toAbsolutePath().toString();
		FileUploadUtil.saveFile(uploadDir, image, multipartFile);
		redirectAttributes.addFlashAttribute("success", "Category Successfully Added");
		return "redirect:/admin/AdminCategory";
		
	}
	
	@GetMapping("/admin/editcategory")
	@ResponseBody
	public Category editCategory(@RequestParam("id") long categoryId) {
		Category category=categoryService.getCategoryById(categoryId);
		category.setSubcategory(null);
		return category;
	}
	
	@PostMapping("/admin/updatecategory")
	public String updateCategory(@ModelAttribute("categoryForm") CategoryFormDto categoryFormDto,@RequestParam(value="img", required=false) MultipartFile multipartFile,RedirectAttributes redirectAttributes) throws IOException{
		Category category=categoryService.getCategoryById(categoryFormDto.getId());
		category.setName(categoryFormDto.getName());
		category.setStatus(categoryFormDto.getStatus());
		if(categoryService.updateCategory(category)) {
			if(multipartFile.getSize()>0) {
				String image=category.getImage();
				String uploadDir=Paths.get("src/main/resources/static/admin/img/category/").toAbsolutePath().toString();
				FileUploadUtil.saveFile(uploadDir, image, multipartFile);
				redirectAttributes.addFlashAttribute("success", "Category Successfully Updated");
			}
		}else {
			redirectAttributes.addFlashAttribute("error", "Category Not Updated.");
		}
		return "redirect:/admin/AdminCategory";
		
	}
	//End Category Routes
	
	
	//SubCatgeory Routes
	@GetMapping("/admin/AdminSubCategory")
	public String adminSubCategories(@ModelAttribute("subCategoryForm") SubCategoryFormDto subCategoryFormDto ,Model model,HttpServletRequest request) {
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
			Map<String, ?>  inputFlashMap= RequestContextUtils.getInputFlashMap(request);
			if(inputFlashMap != null){
				Set<String> key=inputFlashMap.keySet();
				model.addAttribute(key.toString(), (String)inputFlashMap.get(key.toString()));
			}
			model.addAttribute("label", label);
			model.addAttribute("title", "SubCategories");
			model.addAttribute("SubCategories", subCategoryService.getAllSubCategories());
			model.addAttribute("Categories", categoryService.getAllCategories());
			return "admin/AdminSubCategory";
		}
		return "redirect:/admin/login?denied=true";
	}
	
	@PostMapping("/admin/addsubcategory")
	public String addSubCatgeory(@ModelAttribute("subCategoryForm") SubCategoryFormDto subCategoryFormDto,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
		subCategoryService.save(subCategoryFormDto);
		redirectAttributes.addFlashAttribute("success", "Sub-category Successfully Added");
		return "redirect:/admin/AdminSubCategory";
		
	}
	
	@GetMapping("/admin/editsubcategory")
	@ResponseBody
	public SubCategoryFormDto editSubCategory(@RequestParam("id") long subCategoryId) {
		SubCategory subCategory=subCategoryService.getSubCategoryById(subCategoryId);
		SubCategoryFormDto subCategoryFormDto=new SubCategoryFormDto();
		subCategoryFormDto.setCategory(subCategory.getCategory().getId());
		subCategoryFormDto.setId(subCategory.getId());
		subCategoryFormDto.setName(subCategory.getName());
		subCategoryFormDto.setStatus(subCategory.getStatus());
		return subCategoryFormDto;
	}
	
	@PostMapping("/admin/updatesubcategory")
	public String updateSubCategory(@ModelAttribute("subCategoryForm") SubCategoryFormDto subCategoryFormDto,RedirectAttributes redirectAttributes){
		SubCategory subCategory=subCategoryService.getSubCategoryById(subCategoryFormDto.getId());
		subCategory.setName(subCategoryFormDto.getName());
		subCategory.setCategory(categoryService.getCategoryById(subCategoryFormDto.getCategory()));
		subCategory.setStatus(subCategoryFormDto.getStatus());
		if(subCategoryService.updateSubCategory(subCategory)) {
			redirectAttributes.addFlashAttribute("success", "Sub-category Successfully Updated");
		}else {
			redirectAttributes.addFlashAttribute("error", "Sub-category Not Updated.");
		}
		return "redirect:/admin/AdminSubCategory";
		
	}
	//End SubCategory Routes
	
	
	@GetMapping("/admin/logout")
	public String AdminLogout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/admin/login?loggedout=true";
	}
}
