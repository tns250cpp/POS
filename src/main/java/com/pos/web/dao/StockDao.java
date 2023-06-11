package com.pos.web.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pos.web.entity.Product;

public class StockDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public StockDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/////////////////////////////////////////
	//// 날짜별 상품 입고
//	@Override
	public void insertProductByDate(Product newProduct) {
		// 정상작동시 상품을 날짜별 상품 테이블에 입고
		String sql = "INSERT INTO pos.product_by_date (code, name, volume, price, date) VALUES (?,?,?, ?, ?)";
		LocalDate now = LocalDate.now();
		jdbcTemplate.update(sql, newProduct.getCode(), newProduct.getName(), newProduct.getVolume(),
				newProduct.getUnitPrice(), now);

	}

	/////////////////////////////////////////
	//// 총 상품 재고에 대한 테이블 UPDATE
	public void insertProduct(Product newProduct) {
		// 재고 상품 테이블에 새롭게 삽입
		jdbcTemplate.update("INSERT INTO pos.product (code, name, volume, price) VALUES (?,?,?, ?)", 
						newProduct.getCode(), newProduct.getName(), newProduct.getVolume(), newProduct.getUnitPrice());
	}
	
	public void updateProduct(Product oldProduct, Product newProduct) {
		// 기존에 존재하던 데이터에 새로운 product의 수량을 더하여 총 수량 UPDATE
		int total = oldProduct.getVolume() + newProduct.getVolume();
		jdbcTemplate.update("UPDATE pos.product SET volume = ? WHERE code = ? AND name = ?", 
											total, newProduct.getCode(), newProduct.getName());
	}
	
	

	/////////////////////////////////////////
	//// 상품코드를 기준으로 수량, 가격등 수정을 담당
	public void modifyProduct(Product input) {

		String sql = "UPDATE pos.product SET volume = ?, price = ? WHERE code = ?";
		jdbcTemplate.update(sql, input.getVolume(), input.getUnitPrice(), input.getCode());
	}

	
	///////////////////////////////////////////
	//// 입력상품을 가지고 기존에 존재하는 데이터인지 찾기
	public Product searchProductByCode(Product input) {
		String sql = "SELECT * FROM pos.product WHERE code = ?";

		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product product = new Product(rs.getString("code"), rs.getString("name"), rs.getInt("volume"),
					rs.getInt("price"));

			return product;
		}, input.getCode());

		return results.isEmpty() ? null : results.get(0);
	}
	

	///////////////////////////////////////////
	//// msg에 의해 요청하는 LIST를 처리
	public List<Product> searchProductList(String msg, String param) {
		String sql = "";

		// 총 재고 리스트를 보여줌
		if (msg.equals("productAll")) {
			sql = "SELECT * FROM pos.product ORDER BY code";
			List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
				Product product = new Product(rs.getString("code"), rs.getString("name"), rs.getInt("volume"),
						rs.getInt("price"));
				return product;
			});
			return results;
		}
		// 입고일 기준 리스트를 보여줌
		if (msg.equals("productByDate")) {
			sql = "SELECT * FROM pos.product_by_date ORDER BY date DESC";

			List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
				Product product = new Product(rs.getString("code"), rs.getString("name"), rs.getInt("volume"),
						rs.getInt("price"), rs.getDate("date"));
				return product;
			});
			return results;
		}
		// 입고일 검색 결과를 보여줌
		if (msg.equals("searchProductByDate")) {
			sql = "SELECT * FROM pos.product_by_date WHERE date = ?";

			List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
				Product product = new Product(rs.getString("code"), rs.getString("name"), rs.getInt("volume"),
						rs.getInt("price"), rs.getDate("date"));
				return product;
			}, param);
			return results;
		}

		return null;
	}

}
