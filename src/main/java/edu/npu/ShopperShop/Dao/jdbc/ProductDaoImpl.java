package edu.npu.ShopperShop.Dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.ShopperShop.Domain.Product;
import edu.npu.ShopperShop.DaoInterface.ProductDao;
import edu.npu.ShopperShop.Dao.jdbc.ProductRowMapper;


@Repository("productDao")
public class ProductDaoImpl implements ProductDao{
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private ProductRowMapper productRowMapper;
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		productRowMapper = new ProductRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("product")
		                 .usingGeneratedKeyColumns("item_id")
		                 .usingColumns("category", "quantity", "amount","item_name","size");
	}
	
	
	public int getInventoryCount() {
		String sql = "select count(*) from product";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public int getInventoryQuantity(int id) {
		String sql = "select quantity from product where item_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class,id);
	}
   public void insertProduct(Product product){
	   SqlParameterSource params = new BeanPropertySqlParameterSource(product);
	   Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	   product.setItem_id(newId.intValue());
	}
	public int deleteproduct(int itemID){
		String sql="delete from product where item_id=?";
		int rowschanged=jdbcTemplate.update(sql,itemID);
		return rowschanged;
	}
	public int  updateProduct(int quantity,int item_id){
		String sql="Update product set quantity=? where item_id=?";
		int rowschanged=jdbcTemplate.update(sql,quantity,item_id);
		return rowschanged;
	}
	public Product getProductinfo(int id){
		String sql="select * from product where item_id=:item_id";
		MapSqlParameterSource params=new MapSqlParameterSource("item_id",id);
		Product product=dbTemplate.queryForObject(sql,params,productRowMapper);
		return product;
	}
	public List<Product> getCategoryproducts(String category){
		System.out.println(category);
		String sql="select * from product where category=:category";
		MapSqlParameterSource params=new MapSqlParameterSource("category",category);
		List<Product> listProduct=dbTemplate.query(sql,params ,productRowMapper);
		//if(listProduct.size()
		return listProduct;
	}
	public Product checkProductName(int id){
		String sql="select * from product where item_id=:item_id";
 		MapSqlParameterSource params=new MapSqlParameterSource("item_id",id);
 		Product product;
 		try{
		 product=dbTemplate.queryForObject(sql,params,productRowMapper);
 		}
 		catch(EmptyResultDataAccessException e){
        	product=null;
        }
		return product;
	}
	public List<Product> getInventory(){
		String sql = "select * from product";
		List<Product> listProduct=jdbcTemplate.query(sql,productRowMapper);
		//if(listProduct.size().>)
		return listProduct;
	}
	public double getItemcost(String name,String category) {
		String sql = "select amount from product where item_name=? and category=? ";
		return jdbcTemplate.queryForObject(sql, Integer.class,name,category);
	}
	
	public List<String> getCategories(){
		String sql = "select distinct category from product";
		List<String> list=jdbcTemplate.queryForList(sql, String.class);
		
		return list;
	}
	}

