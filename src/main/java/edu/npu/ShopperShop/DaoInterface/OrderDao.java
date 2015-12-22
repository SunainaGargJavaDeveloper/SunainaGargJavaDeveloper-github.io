package edu.npu.ShopperShop.DaoInterface;

import java.util.List;

import edu.npu.ShopperShop.Domain.Order;

public interface OrderDao {
	public int insertOrder(Order order);
	public Order showOrder(int orderno);
	public List<Integer> showOrderItemID(int orderno);
	public List<Integer> showOrderItemQuantity(int orderno);
	public double getItemAmount(int id);
}
