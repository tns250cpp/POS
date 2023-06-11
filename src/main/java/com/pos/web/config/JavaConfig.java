package com.pos.web.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pos.web.dao.MemberDao;
import com.pos.web.dao.StockDao;
import com.pos.web.dao.SaleDao;
import com.pos.web.dao.StatisticsDao;
import com.pos.web.service.MemberService;
import com.pos.web.service.StockService;
import com.pos.web.service.SaleService;
import com.pos.web.service.StatisticsService;

@Configuration
@EnableTransactionManagement
public class JavaConfig {
	
	@Bean(destroyMethod = "close")
	public  DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/pos?characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("1234");
		
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberDao());
	}
	
	@Bean
	public StockDao productDao() {
		return new StockDao(dataSource());
	}
	
	@Bean
	public StockService productService() {
		return new StockService(productDao());
	}
	
	@Bean
	public SaleDao saleDao() {
		return new SaleDao(dataSource());
	}
	
	@Bean
	public SaleService saleService() {
		return new SaleService(saleDao());
	}
	
	@Bean
	public StatisticsDao statisticsDao() {
		return new StatisticsDao(dataSource());
	}
	
	@Bean 
	public StatisticsService statisticsService() {
		return new StatisticsService(statisticsDao());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
}
