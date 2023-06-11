package com.pos.web.service;

import java.util.List;

import com.pos.web.dao.StatisticsDao;
import com.pos.web.entity.Product;

public class StatisticsService {

	private StatisticsDao dao;
	public StatisticsService(StatisticsDao statisticsDao) {
		this.dao = statisticsDao;
	}
	
	public List<Product> showList() {
		List<Product> list = dao.getTotalList();
		return list;
	}

	public List<Product> searchByDate(String start, String end) {
		List<Product> list = dao.getTotalListByDate(start, end);
		return list;
	}

	public Product getTopSalesInfo(String start, String end) throws Exception {
		Product product = new Product();
		try {
		product.setVolume(dao.getMaxVolume(start, end));
		product.setSumTotalPrice(dao.getMaxRevenue(start, end));
		product.setName(dao.getTopSellingProduct(start, end));
		}catch (Exception e) {
			throw new Exception("DismatchDateType");
		}
		
		return product;
	}

	
	
}
