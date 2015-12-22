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
public class OrderController {
	@Autowired
	InventoryService inventService;
	@Autowired
	PurchaseService purchaseService;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@RequestMapping(value = "/newOrder", method = RequestMethod.GET)
	public ModelAndView ShowAllCategories() {
		ModelAndView modelView;
		
		modelView = new ModelAndView("newOrderForm");
		
		return modelView;

	}
	@RequestMapping(value = "/processOrder", method = RequestMethod.POST,params="more")
	public ModelAndView MoreOrder(String firstName,String lastName,int item_id, int quantity,HttpSession session) {
		ModelAndView modelView;
		Order or=new Order();
		or.setCustomer(new Customer(firstName,lastName));
		Product prod=new Product();
		prod.setItem_id(item_id);
		prod.setQuantity(quantity);
		or.addItem(prod);
		modelView = new ModelAndView("MoreOrderForm");
		session.setAttribute("Order",or );
		return modelView;

	}
	@RequestMapping(value = "/processOrder", method = RequestMethod.POST,params="ProcessOrder")
	public ModelAndView processOrder(String firstName,String lastName,int item_id,int quantity) {
		ModelAndView modelView;
	    Order or=new Order();
		or.setCustomer(new Customer(firstName,lastName));
		Product prod=new Product();
		prod.setItem_id(item_id);
		prod.setQuantity(quantity);
		or.addItem(prod);
		int order_no=purchaseService.placeOrder(or);
		Order finalorder=purchaseService.showOrderDetail(order_no);
		modelView = new ModelAndView("receiptForm");
		modelView.addObject("FinalOrder",finalorder);
		return modelView;

	}
	@RequestMapping(value = "/processOrderMore", method = RequestMethod.POST,params="more")
	public ModelAndView MoreOrderMore(int item_id, int quantity,HttpSession session) {
		ModelAndView modelView;
		Order or=(Order)session.getAttribute("Order");
	
		Product prod=new Product();
		prod.setItem_id(item_id);
		prod.setQuantity(quantity);
		or.addItem(prod);
		modelView = new ModelAndView("MoreOrderForm");
		session.setAttribute("Order",or );
		return modelView;

	}
	@RequestMapping(value = "/processOrderMore", method = RequestMethod.POST,params="ProcessOrder")
	public ModelAndView processOrderMore(int item_id,int quantity,HttpSession session) {
		ModelAndView modelView;
	    Order or=(Order)session.getAttribute("Order");
		
		Product prod=new Product();
		prod.setItem_id(item_id);
		prod.setQuantity(quantity);
		or.addItem(prod);
		int order_no=purchaseService.placeOrder(or);
		Order finalorder=purchaseService.showOrderDetail(order_no);
		modelView = new ModelAndView("receiptForm");
		modelView.addObject("FinalOrder",finalorder);
		return modelView;

	}

}
