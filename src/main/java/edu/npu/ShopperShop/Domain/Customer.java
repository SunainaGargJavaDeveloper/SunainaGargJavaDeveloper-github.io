package edu.npu.ShopperShop.Domain;



public class Customer {

	    private String firstName;
	    private String lastName;
		
		public Customer(String fname,String lname){
			this.firstName=fname;
			this.lastName=lname;
		}
		public Customer getCustomer(){
			return this;
		}
		public String getFirstName(){
			return firstName;
		}
		public String getLastName(){
			return lastName;
		}
	}

