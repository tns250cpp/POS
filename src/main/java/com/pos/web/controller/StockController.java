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
import com.pos.web.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	// 재고 관리 메인 페이지
	@GetMapping("")
	public String main(Model model) {

		List<Product> list = stockService.showList("productAll", "");

		model.addAttribute("productList", list);
		return "/stock/stock";
	}

	// 입고 처리 페이지 이동
	@GetMapping("/warehousing")
	public String goWarehousing() {
		return "/stock/warehousing";
	}

	// 입고 처리
	@PostMapping("/doWarehousing")
	public String doWarehousing(Model model, @RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name, @RequestParam(value = "volume") String volume_,
			@RequestParam(value = "price") String price_) {
		int volume, price;
		
		try {
			volume = Integer.parseInt(volume_);
			price = Integer.parseInt(price_);
			if (volume < 0 || price < 0)
				
				
				throw new Exception("NotAllowRange");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "수량 및 가격은 0이상의 숫자만 입력가능합니다. 다시 확인해주세요.");
			model.addAttribute("url", "/stock/warehousing");
			return "/alert/msg";
		}

		Product newProduct = new Product(code, name, volume, price);
		// 날짜별 상품을 입고
		try {
			stockService.insertByDate(newProduct);
		} catch (Exception e) {
			if(e.getMessage().equals("NotMatchProductName"))
				model.addAttribute("msg", "일치하는 상품명이 없습니다.");
			if(e.getMessage().equals("NotMatchProductPrice"))
				model.addAttribute("msg", "재고의 가격이 일치하지 않습니다.");
				
			model.addAttribute("url", "/stock/warehousing");
			return "/alert/msg";
		}

		return "redirect:/stock";
	}

	// 입고일 별 상품리스트 페이지 이동
	@GetMapping("/date")
	public String goStockByDate(Model model) {
		// msg로 해당 리스트 출력
		List<Product> list = stockService.showList("productByDate", "");
		model.addAttribute("productListByDate", list);

		return "/stock/date";
	}

	// 입고일 검색 결과에 따른 상품리스트 페이지 이동
	@PostMapping("/searchByDate")
	public String searchStockByDate(Model model, @RequestParam(value = "date") String date) {
		List<Product> list = stockService.showList("searchProductByDate", date);
		model.addAttribute("searchProductByDate", list);

		return "/stock/searchByDate";
	}

	// 상품 내용(수량, 가격) 변경 페이지 이동
	@GetMapping("/modify")
	public String goModify() {
		return "/stock/modify";
	}

	// 상품 변경 처리
	@PostMapping("/doModify")
	public String doModfiy(Model model, @RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name, @RequestParam(value = "volume") String volume_,
			@RequestParam(value = "price") String price_) {
		int volume, price;
		
		try {
			volume = Integer.parseInt(volume_);
			price = Integer.parseInt(price_);
			if (volume < 0 || price < 0)
				throw new Exception("NotAllowNegative");
		}catch (Exception e) {
			System.out.println(e);
			model.addAttribute("msg", "수량 및 가격은 0이상의 숫자만 입력가능합니다. 다시 확인해주세요.");
			model.addAttribute("url", "/stock/modify");
			return "/alert/msg";
		}

		try {
			stockService.modify(new Product(code, name, volume, price));
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("msg", "변경하려는 상품이 존재하지 않습니다. 존재하는 상품코드를 다시 입력해주세요!");
			model.addAttribute("url", "/stock/modify");
			return "/alert/msg";
		}

		return "redirect:/stock";
	}

}
