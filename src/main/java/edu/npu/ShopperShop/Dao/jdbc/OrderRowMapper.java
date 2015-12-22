package edu.npu.ShopperShop.Dao.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import edu.npu.ShopperShop.Domain.*;

public class OrderRowMapper implements RowMapper<Order>{
	public Order mapRow(ResultSet resultSet, int row) throws SQLException,EmptyResultDataAccessException {
	 	Order p=new Order();
	 	    p.setCustomer(new Customer(resultSet.getString("customer_firstName"),resultSet.getString("customer_lastName")));
			p.setOrder_no(resultSet.getInt("order_no"));
			p.setTotal1(resultSet.getFloat("order_amount"));
			
				
	     return p;
		}  
}
