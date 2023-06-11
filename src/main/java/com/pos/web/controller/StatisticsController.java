package com.pos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pos.web.entity.Product;
import com.pos.web.service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;

	@GetMapping("")
	public String main(Model model) {
		List<Product> list = statisticsService.showList();
		int sum = 0;

		if (list != null) {
			for (Product product : list)
				sum += product.getTotalPrice();
		}

		model.addAttribute("sumTotalPrice", sum);
		model.addAttribute("totalSalesList", list);
		return "/statistics/statistics";
	}
	
	@PostMapping("/date")
	public String listByDate(Model model, @RequestParam(value = "start")String start, 
										  @RequestParam(value = "end")String end) {
		
		if(start.equals("") || end.equals("")) {
			model.addAttribute("msg", "조회 날짜 입력값을 모두 입력해주세요.");
			model.addAttribute("url", "/statistics");
			return "/alert/msg";
		}
		
		List<Product> list = statisticsService.searchByDate(start, end);
		Product product;
		
		try {
			product =  statisticsService.getTopSalesInfo(start, end);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "날짜의 입력형식이 올바르지 않습니다.");
			model.addAttribute("url", "/statistics");
			return "/alert/msg";
		}
		
		model.addAttribute("startDate", start);
		model.addAttribute("endDate", end);
		model.addAttribute("maxVolume", product.getVolume());
		model.addAttribute("maxRevenue", product.getSumTotalPrice());
		model.addAttribute("topSellingProduct", product.getName());
		model.addAttribute("totalListByDate", list);
		
		return "/statistics/date";
	}
}
