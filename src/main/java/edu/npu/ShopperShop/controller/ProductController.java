package edu.npu.ShopperShop.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.ShopperShop.Domain.*;
import edu.npu.ShopperShop.Services.*;



@Controller
public class ProductController {
@Autowired
InventoryService inventService;
private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

@RequestMapping(value = "/ShowAllCategories", method = RequestMethod.GET)
public ModelAndView ShowAllCategories() {
	ModelAndView modelView;
	 List<String>ls=inventService.getcategories();
	 //CategoryList catList=new CategoryList();
	// catList.setCList(ls);
	modelView = new ModelAndView("ShowAllCategoryForm");
	modelView.addObject("categories", ls);
	return modelView;

}
@RequestMapping(value = "/ShowAllCategoryProducts", method = RequestMethod.GET)
public ModelAndView ShowAllCategoryProducts(@RequestParam(value="category")String category) {
	ModelAndView modelView;
	 List<Product>ls=inventService.getProductsOfCategory(category);
	// ProductList listOfProducts = new ProductList();
		
	 //CategoryList catList=new CategoryList();
	// catList.setCList(ls);
	modelView = new ModelAndView("ShowAllCategoryProductForm");
	//listOfProducts.setPList(ls);
	modelView.addObject("categoryProducts", ls);
	return modelView;

}

}