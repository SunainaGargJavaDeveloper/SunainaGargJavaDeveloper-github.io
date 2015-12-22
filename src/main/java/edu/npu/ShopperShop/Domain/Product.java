package edu.npu.ShopperShop.Domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
	private String item_name;
	 private double amount;
	 private int quantity=0;
	 private String category; 
	 private int item_id;
	 private String size;
	 public Product(){}
	 public Product(String prod,double p,int quan,String category){
		 item_name=prod;
		 amount=p;
		 quantity=quan;
		 this.category=category;
	 }
	 public Product(String prod,double p,int quan){
		 item_name=prod;
		 amount=p;
		 quantity=quan;
	 }
	 public Product(String prod,double p){
		 item_name=prod;
		 amount=p;
	 }
	 public String getSize(){
		 return size;
	 }
	 public void setSize(String size){
		 this.size=size;
	 }
	 public void setItem_id(int id){
		 this.item_id=id;
		 
	 }
	 public int getItem_id(){
		 return item_id;
	 }
	 public void setItem_name(String name){
		 item_name=name;
	 }
	 public void setAmount(double price){
		 amount=price;
	 }
	 public void setQuantity(int quantity){
		 this.quantity=quantity;
	 }
	 public String getItem_name(){
		 return item_name;
	 }
	 public double getAmount()
	 {
		 return amount;
	 }
	 public int getQuantity(){
	    return quantity;
	}
	 public void setCategory(String category){
		 this.category=category;
	 }
	 public String getCategory(){
		 return category;
		 }
	 
	 public String toString(){
		 String str="product { "+item_id+","+item_name+","+category+","+amount+","+quantity+","+size+" }";
		 return str;
	 }

}
