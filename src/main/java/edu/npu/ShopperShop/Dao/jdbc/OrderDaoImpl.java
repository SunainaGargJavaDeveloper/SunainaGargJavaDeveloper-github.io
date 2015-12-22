package edu.npu.ShopperShop.Dao.jdbc;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.ShopperShop.Domain.*;
import edu.npu.ShopperShop.DaoInterface.*;
import edu.npu.ShopperShop.Exceptions.ProductNotFoundException;
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private SimpleJdbcInsert jdbcInsert1;
	private OrderRowMapper orderRowMapper;
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		orderRowMapper=new OrderRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("orderTable")
        .usingGeneratedKeyColumns("order_no")
        .usingColumns("customer_firstName", "customer_lastName", "order_amount");
		jdbcInsert1 = new SimpleJdbcInsert(dataSource)
        .withTableName("orderDetail")
        .usingColumns("order_no","item_id","quantity");
	}
	
	@Transactional(rollbackForClassName="ProductNotFoundException")
	public int insertOrder(Order order)throws ProductNotFoundException{
		//String sql="Insert into OrderDetail (customer_firstName, customer_lastName, order_amount,order_info)values(?,?,?,?)";
		
	    Map<String,Object> args= new HashMap<String,Object>();
        int item_id;
        double amount;
	    for(int i=0;i<order.getItems().size();i++){
	    	item_id=order.getItems().get(i).getItem_id();
	    	amount=getItemAmount(item_id);
	    	order.getItems().get(i).setAmount(amount);
	    }
	    order.setTotal();
	    
	    args.put("customer_firstName",order.getCustomer().getFirstName());
	    args.put("customer_lastName", order.getCustomer().getLastName());
	    args.put("order_amount", order.getTotal());
	    Number id=jdbcInsert.executeAndReturnKey(args);
	    
		order.setOrder_no(id.intValue());
		
		Map<String,Object> args1= new HashMap<String,Object>();
		args1.put("order_no", id);
		int quantity;
		for(int i=0;i<order.getItems().size();i++){
			item_id=order.getItems().get(i).getItem_id();
			Product p=productDao.checkProductName(item_id);
			if (p==null)
				throw new ProductNotFoundException("product not found");
			quantity=order.getItems().get(i).getQuantity();
			args1.put("item_id",item_id);
			args1.put("quantity", quantity);
			jdbcInsert1.execute(args1);
		}
		return (id.intValue());
	}
	
	public Order showOrder(int orderno){
		String sql="select * from orderTable where order_no=:order_no";
		MapSqlParameterSource params=new MapSqlParameterSource("order_no",orderno);
		Order order=dbTemplate.queryForObject(sql,params,orderRowMapper);
		return order;
	}
	
	public List<Integer> showOrderItemID(int orderno){
		String sql="select item_id from orderDetail where order_no=:order_no";
		MapSqlParameterSource params=new MapSqlParameterSource("order_no",orderno);
		List<Integer> lt=dbTemplate.queryForList(sql,params,Integer.class);
		return lt;
	}
	public List<Integer> showOrderItemQuantity(int orderno){
		String sql="select quantity from orderDetail where order_no=:order_no";
		MapSqlParameterSource params=new MapSqlParameterSource("order_no",orderno);
		List<Integer> lt=dbTemplate.queryForList(sql,params,Integer.class);
		return lt;
	}
	
	public double getItemAmount(int id){
		String sql="select amount from product where item_id=?";
		double amount=jdbcTemplate.queryForObject(sql, Double.class,id);
		return amount;
	}
}
