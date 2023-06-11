package com.pos.web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pos.web.dao.SaleDao;
import com.pos.web.entity.Product;

public class SaleService {

	private SaleDao dao;

	public SaleService(SaleDao saleDao) {
		this.dao = saleDao;
	}

	public List<Product> showList() {
		List<Product> list = dao.searchProductList();
		return list;
	}
	
	public void reset() {
		dao.reset();
	}
	
	
	@Transactional(rollbackFor = SQLException.class)
	public void regist(String name, int volume) throws Exception {
		Product oldProduct = dao.searchInProductByName(name);
		if(oldProduct == null) 
			throw new Exception("NotExistingProduct");
		if(oldProduct.getVolume() < volume)
			throw new Exception("OutOfStock");
		
		int totalPrice = oldProduct.getUnitPrice() * volume;
		Product saleProduct = new Product(oldProduct.getCode(), oldProduct.getName(), volume, 
											oldProduct.getUnitPrice(), totalPrice);
		
		Product oldSaleProduct = dao.searchInSaleByName(name);
		
		if(oldSaleProduct == null) {
			// 판매테이블에 상품이 없다면 그대로 삽입
			dao.insertSaleProduct(saleProduct);
		}else {
			// 판매테이블에 상품이 판매등록 되어 있으면 수량 및 총액 업데이트
			dao.updateSaleProduct(saleProduct, oldSaleProduct, oldProduct.getVolume());
		}
				
	}

	@Transactional(rollbackFor = SQLException.class)
	public void updateProductTable(String code) throws Exception {
		// sale테이블에 있던 상품의 판매정보를 product테이블에서 수량 업데이트
		Product saleProduct = dao.searchInSaleByCode(code);
		Product product = dao.searchInProductByName(saleProduct.getName());
		
		int volume = product.getVolume() - saleProduct.getVolume();
		
		dao.updateProduct(saleProduct, volume);		//1. 판매정보를 재고(product)테이블에 업데이트
		dao.updateTotalSales(saleProduct);			//2. 판매정보를 통계(total_sales)테이블에 업데이트
		
	}
	

}
