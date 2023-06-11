package com.pos.web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pos.web.dao.StockDao;
import com.pos.web.entity.Product;

public class StockService {

	private StockDao dao;

	public StockService(StockDao productDao) {
		this.dao = productDao;
	}

	public List<Product> showList(String msg, String param) {
		List<Product> list = dao.searchProductList(msg, param);
		return list;
	}

	// 입고일별 상품 테이블에 조건에 부합할 시 삽입하고
	// 그 후 총 상품 테이블에 UPDATE
	@Transactional(rollbackFor = SQLException.class)
	public void insertByDate(Product newProduct) throws Exception {
		Product oldProduct = dao.searchProductByCode(newProduct);
		
		// 재고 DB에 상품이 존재하면서
		if (oldProduct != null) {	
			if (!oldProduct.getName().equals(newProduct.getName()))
				// 상품명이 같지 않은 경우 상품명 예외 처리
				throw new Exception("NotMatchProductName");
			if (oldProduct.getUnitPrice() != newProduct.getUnitPrice())
				// 가격이 기존 재고와 같지 않은 경우 가격 예외 처리
				throw new Exception("NotMatchProductPrice");
			
			dao.insertProductByDate(newProduct);
			dao.updateProduct(oldProduct, newProduct);
		}else {
			dao.insertProductByDate(newProduct);
			dao.insertProduct(newProduct);
		}
		
	}

	@Transactional(rollbackFor = SQLException.class)
	public void modify(Product input) throws Exception {
		Product oldProduct = dao.searchProductByCode(input);
		if(oldProduct == null) 
			throw new Exception("NotExistingProduct");
		
		dao.modifyProduct(input);
	}

}
