package edu.npu.ShopperShop.Domain;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.npu.ShopperShop.Domain.Product;

@XmlRootElement
public class ProductList implements Serializable {
private static final long serialVersionUID = 1L;
//@XmlElement(name = "product")
private List<Product> pList;

public ProductList(){
	
}
public List<Product> getPList(){
	return pList;
}
public void setPList(List<Product> list){
	pList=list;
}
public int numEntries() {
	if (pList == null) return 0;
	return pList.size();
}
public Product getProduct(int idx) {
	return pList.get(idx);
}

public String toString() {
	String listStr;
	
	listStr = "productList[";
	for (Product entry: pList) {
		listStr = listStr + "\n\t" + entry;
	}
	
	listStr = listStr + "\n]";
	return listStr;
}
}
