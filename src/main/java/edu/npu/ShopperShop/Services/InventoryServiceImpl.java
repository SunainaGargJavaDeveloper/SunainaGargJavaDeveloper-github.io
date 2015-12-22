package edu.npu.ShopperShop.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.ShopperShop.DaoInterface.ProductDao;
import edu.npu.ShopperShop.Domain.Order;
import edu.npu.ShopperShop.Domain.Product;
import edu.npu.ShopperShop.Exceptions.QuantityExceeded;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService{
	@Autowired  
	private ProductDao productDao;
		
	    public InventoryServiceImpl(){
			}
	    
		public int totalDifferentItems(){
			int total=productDao.getInventoryCount();
			return total;
		}
		@Transactional
		public void adjustInventory(Order order)throws QuantityExceeded{ 
			int quantity;
            for (int i=0;i<order.getItems().size();i++)	{
            	quantity=productDao.getInventoryQuantity(order.getItems().get(i).getItem_id());
            	if(quantity<order.getItems().get(i).getQuantity())
            		throw new QuantityExceeded("Quantity Execeeded");
            	productDao.updateProduct(quantity-order.getItems().get(i).getQuantity(),order.getItems().get(i).getItem_id());
            }
			    
	     }    
		
		public List<Product> printCurrentInventory(){
			List<Product>products=productDao.getInventory();
			System.out.println("Current Items in inventory:");
			System.out.println("Item Name \t\t Quantity");
		    for(int i=0;i<products.size();i++){
				 System.out.println(products.get(i).getItem_name()+"\t\t"+products.get(i).getQuantity());
			}
		    return (products);
		}
			
		@Transactional
		public void insertInventory(Product prod){
			productDao.insertProduct(prod);
		}
		
		@Transactional
		public int deleteInventory(int id){
			int rowsdeleted=productDao.deleteproduct(id);
			return rowsdeleted;
		}
		
		@Transactional
		public int updateInventory(int id,int quantity){
			int rowsChanged=productDao.updateProduct(quantity, id);
			return rowsChanged;
		}
		
		@Transactional
		public Product getproduct(int id) {
			Product prod=productDao.getProductinfo(id);
			return prod;
		}
		@Transactional
		public List<String> getcategories() {
			List <String> ls=productDao.getCategories();
			return ls;
		}
		@Transactional
		public List<Product> getProductsOfCategory(String category) {
			List <Product> ls=productDao.getCategoryproducts(category);
			
			return ls;
		}
     
}
