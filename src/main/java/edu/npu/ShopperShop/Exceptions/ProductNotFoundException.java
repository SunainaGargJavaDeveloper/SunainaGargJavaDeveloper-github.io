package edu.npu.ShopperShop.Exceptions;

	public class ProductNotFoundException extends RuntimeException {
			private static final long serialVersionUID = 1L;

			public ProductNotFoundException(String msg) {
				super(msg);
			}
		}	
	
