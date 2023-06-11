package com.pos.web.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pos.web.entity.Product;

public class SaleDao {

	private JdbcTemplate jdbcTemplate;
	public SaleDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Product> searchProductList()  {
		String sql = "SELECT * FROM pos.sale";
		
		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product saleProduct = new Product(rs.getString("s_code"), rs.getString("s_name"), rs.getInt("s_volume"), 
					rs.getInt("s_price"), rs.getInt("s_total_price"));

			return saleProduct;
		});
		return results.isEmpty() ? null : results;
	}
	
	public Product searchInProductByName(String name) throws Exception {
		String sql = "SELECT * FROM pos.product WHERE name = ?";

		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product product = new Product(rs.getString("code"), rs.getString("name"), 
											rs.getInt("volume"), rs.getInt("price"));

			return product;
		}, name);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public Product searchInSaleByName(String name) throws Exception{
		String sql = "SELECT * FROM pos.sale WHERE s_name = ?";

		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product saleProduct = new Product(rs.getString("s_code"), rs.getString("s_name"), rs.getInt("s_volume"), 
					rs.getInt("s_price"), rs.getInt("s_total_price"));

			return saleProduct;
		}, name);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public Product searchInSaleByCode(String code) throws Exception{
		String sql = "SELECT * FROM pos.sale WHERE s_code = ?";

		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product saleProduct = new Product(rs.getString("s_code"), rs.getString("s_name"), rs.getInt("s_volume"), 
					rs.getInt("s_price"), rs.getInt("s_total_price"));

			return saleProduct;
		}, code);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public void reset() {
		jdbcTemplate.update("TRUNCATE pos.sale");
	}

	public void insertSaleProduct(Product saleProduct) throws Exception{
		jdbcTemplate.update("INSERT INTO pos.sale (s_code, s_name, s_volume, s_price, s_total_price) VALUES (?,?,?,?,?)", 
										saleProduct.getCode(), saleProduct.getName(), 
										saleProduct.getVolume(), saleProduct.getUnitPrice(),
										saleProduct.getVolume() * saleProduct.getUnitPrice() );
	}

	public void updateSaleProduct(Product saleProduct, Product oldSaleProduct, int stock_Volume) throws Exception {
		int volume = saleProduct.getVolume() + oldSaleProduct.getVolume();
		if(volume > stock_Volume)
			throw new Exception("OutOfStock");
		
		int totalPrice = volume * saleProduct.getUnitPrice();
		jdbcTemplate.update("UPDATE pos.sale SET s_volume = ?, s_total_price = ? WHERE s_code = ?", 
							volume, totalPrice, saleProduct.getCode());
	}

	public void updateProduct(Product saleProduct, int volume) throws Exception{
		jdbcTemplate.update("UPDATE pos.product SET volume = ? WHERE code = ?", 
				volume, saleProduct.getCode());
		
	}

	public void updateTotalSales(Product saleProduct) throws Exception {
		String sql = "SELECT MAX(t_sum_total_price) FROM pos.total_sales;";
		
		int totalPrice = saleProduct.getVolume() * saleProduct.getUnitPrice();
		int sumTotalPrice; 
		try {
			sumTotalPrice = jdbcTemplate.queryForObject(sql, Integer.class);
			sumTotalPrice += totalPrice;
		}catch (Exception e) {
			sumTotalPrice = totalPrice;
		}
		
		jdbcTemplate.update("INSERT INTO pos.total_sales "
				+ "(t_code, t_name, t_volume, t_price, t_total_price, t_date, t_sum_total_price) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)",
				saleProduct.getCode(), saleProduct.getName(), saleProduct.getVolume(), saleProduct.getUnitPrice(),
				totalPrice, LocalDate.now(), sumTotalPrice);
		
	}


	

}
