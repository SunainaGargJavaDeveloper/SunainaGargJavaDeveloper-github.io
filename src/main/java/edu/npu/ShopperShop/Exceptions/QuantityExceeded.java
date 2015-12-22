package edu.npu.ShopperShop.Exceptions;


public class QuantityExceeded extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public QuantityExceeded(String msg) {
		super(msg);
	}
}	