package edu.npu.ShopperShop.Services;

import java.util.List;





import edu.npu.ShopperShop.Domain.Order;
import edu.npu.ShopperShop.Domain.Product;
import edu.npu.ShopperShop.Exceptions.QuantityExceeded;

public interface InventoryService {
	public void insertInventory(Product prod);
	public List<Product> printCurrentInventory();
	//public void adjustInventory(Order order);
	//public int totalDifferentItems();
	public int deleteInventory(int id);
	public int updateInventory(int id,int quantity);
	public Product getproduct(int id);
	public List<String> getcategories();
	public List<Product> getProductsOfCategory(String category);
	public void adjustInventory(Order order)throws QuantityExceeded;
}
