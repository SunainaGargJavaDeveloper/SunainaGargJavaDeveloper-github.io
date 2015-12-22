package edu.npu.ShopperShop.Services;
import edu.npu.ShopperShop.Domain.*;
import edu.npu.ShopperShop.Exceptions.ProductNotFoundException;
import edu.npu.ShopperShop.Exceptions.QuantityExceeded;


public interface PurchaseService {
	public int placeOrder(Order or)throws 
	ProductNotFoundException,QuantityExceeded;
	 public Order showOrderDetail(int order_no);
}
