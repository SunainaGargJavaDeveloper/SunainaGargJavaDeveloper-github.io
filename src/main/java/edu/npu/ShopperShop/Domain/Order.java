package edu.npu.ShopperShop.Domain;



import java.util.ArrayList;
import java.util.List;

public class Order {
	private int order_no=0;
	private Customer customer;
	private List<Product> items=new ArrayList<Product>();
	double total=0;
	
	public Order() {
	}
	public void setOrder_no(int no){
		order_no=no;
	}
	public int getOrder_no(){
		return order_no;
	}
	public void addItem(Product p){
		items.add(p);
	}
	
     public void setTotal(){
    	for(int i=0;i<items.size();i++){
    		total+=(items.get(i).getQuantity())*(items.get(i).getAmount());
    	}
    	
    }
    
     public void setTotal1(double total){
     	this.total=total;
     }
     public double getTotal(){
    	return total;
    }
  
    public List<Product> getItems(){
    	return items;
    }
    public Customer getCustomer(){
    	return customer;
    }
    public void setCustomer(Customer customer){
    	this.customer=customer;
    }
   
    
}
