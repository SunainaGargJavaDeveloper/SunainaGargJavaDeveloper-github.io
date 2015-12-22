package edu.npu.ShopperShop.DaoInterface;

import java.util.List;





import edu.npu.ShopperShop.Domain.*;
public interface ProductDao {
	public void insertProduct(Product product);
	public int deleteproduct(int product_id);
	public int updateProduct(int quantity,int id);
	public Product getProductinfo(int id);
	public int getInventoryCount();
	public List<Product> getInventory();
	public int getInventoryQuantity(int id);
	public double getItemcost(String name,String category);
	public Product checkProductName(int id);
	public List<String> getCategories();
	public List<Product> getCategoryproducts(String category);
	
	}