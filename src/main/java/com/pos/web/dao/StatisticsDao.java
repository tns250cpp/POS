package com.pos.web.dao;

import java.sql.ResultSet;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pos.web.entity.Product;

public class StatisticsDao {

	private JdbcTemplate jdbcTemplate;
	public StatisticsDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Product> getTotalList() {
		String sql = "select * from pos.total_sales";
		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product product = new Product(rs.getInt("t_no."), rs.getString("t_code"), rs.getString("t_name"), 
					rs.getInt("t_volume"), rs.getInt("t_price"), rs.getInt("t_total_price"), rs.getDate("t_date"));
			return product;
		});
		return results;
		
	}

	public List<Product> getTotalListByDate(String start, String end) {
		String sql = "select * from pos.total_sales where t_date between ? and ?";
		List<Product> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Product product = new Product(rs.getInt("t_no."), rs.getString("t_code"), rs.getString("t_name"), 
					rs.getInt("t_volume"), rs.getInt("t_price"), rs.getInt("t_total_price"), rs.getDate("t_date"));
			return product;
		}, start, end);
		
		return results;
	}

	public int getMaxVolume(String start, String end) {
		String sql = "select sum(t_volume) from pos.total_sales where t_date between ? and ?";
		int maxVolume = jdbcTemplate.queryForObject(sql, Integer.class, start, end);
		return maxVolume;
	}

	public int getMaxRevenue(String start, String end) {
		String sql = "select sum(t_total_price) from pos.total_sales where t_date between ? and ?";
		int maxRevenue = jdbcTemplate.queryForObject(sql, Integer.class, start, end);
		return maxRevenue;
	}

	public String getTopSellingProduct(String start, String end) {
		String sql = "select A.t_name\r\n"
				   + "from (select t_name, sum(t_volume) as t_volume \r\n"
				   + "	    from pos.total_sales \r\n"
				   + "      where (t_date between ? and ?) \r\n"
				   + "      group by t_name)A\r\n"
				   + "order by t_volume desc limit 1";
		
		String topSellingProduct = jdbcTemplate.queryForObject(sql, String.class, start, end);
		return topSellingProduct;
	}

}
