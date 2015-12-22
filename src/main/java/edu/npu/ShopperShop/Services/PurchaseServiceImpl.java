package edu.npu.ShopperShop.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import edu.npu.ShopperShop.DaoInterface.OrderDao;
import edu.npu.ShopperShop.DaoInterface.ProductDao;
import edu.npu.ShopperShop.Domain.Customer;
import edu.npu.ShopperShop.Domain.Order;
import edu.npu.ShopperShop.Domain.Product;
import edu.npu.ShopperShop.Exceptions.*;


@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
	private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    @Qualifier("inventoryService")
    private InventoryService inventoryService;
    @Transactional(rollbackForClassName="QuantityExceeded")
	public int placeOrder(Order or)throws ProductNotFoundException,QuantityExceeded{
    	int order_no=orderDao.insertOrder(or);
		inventoryService.adjustInventory(or);
		return order_no;
	}
    
    @Transactional
    public Order showOrderDetail(int order_no){
    	Order or=orderDao.showOrder(order_no);
    	List<Integer>order_ids=orderDao.showOrderItemID(order_no);
    	List<Integer> quantities=orderDao.showOrderItemQuantity(order_no);
    	for(int i=0;i<order_ids.size();i++){
    		Product p=productDao.getProductinfo(order_ids.get(i));
    		p.setQuantity(quantities.get(i));
    	    or.addItem(p);
    	}
    return 	or;
    }
    
}
