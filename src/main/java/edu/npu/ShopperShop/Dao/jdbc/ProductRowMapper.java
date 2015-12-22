package edu.npu.ShopperShop.Dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import edu.npu.ShopperShop.Domain.Product;


public class ProductRowMapper implements RowMapper<Product>{
	
    public Product mapRow(ResultSet resultSet, int row) throws SQLException,EmptyResultDataAccessException {
 	Product p=new Product();
 	
  
		p.setItem_id(resultSet.getInt("item_id"));
		p.setAmount(resultSet.getFloat("amount"));
		p.setItem_name(resultSet.getString("item_name"));
		p.setQuantity(resultSet.getInt("quantity"));
		p.setCategory(resultSet.getString("category"));
		
			p.setSize(resultSet.getString("size"));
     return p;
	}  

}
