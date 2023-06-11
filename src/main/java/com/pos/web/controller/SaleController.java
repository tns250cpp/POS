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
import com.pos.web.service.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping("")
	public String main(Model model) {
		List<Product> list = saleService.showList();
		int sum = 0;

		if (list != null) {
			for (Product product : list)
				sum += product.getTotalPrice();
		}

		model.addAttribute("sumTotalPrice", sum);
		model.addAttribute("saleProductList", list);
		return "/sale/saleList";
	}

	@GetMapping("/register")
	public String sale() {
		return "/sale/sale";
	}

	@GetMapping("/reset")
	public String reset() {
		saleService.reset();
		return "redirect:/sale";
	}

	@PostMapping("/doRegister")
	public String registerProduct(Model model, @RequestParam(value = "name") String name,
			@RequestParam(value = "volume") String volume_) {
		int volume;
		try {
			volume = Integer.parseInt(volume_);
			if (volume <= 0)
				throw new Exception("NotAllowRange");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("msg", "입력 수량은 양수만 가능합니다.");
			model.addAttribute("url", "/sale");
			return "/alert/msg";
		}

		try {
			saleService.regist(name, volume);
		} catch (Exception e) {
			if (e.getMessage().equals("NotExistingProduct"))
				model.addAttribute("msg", "일치하는 상품명이 없습니다.");
			if (e.getMessage().equals("OutOfStock"))
				model.addAttribute("msg", "입력하신 수량보다 재고수량이 더 적습니다.");

			model.addAttribute("url", "/sale/register");
			return "/alert/msg";
		}

		return "redirect:/sale";
	}

	@PostMapping("/pay")
	public String pay(Model model, @RequestParam(value = "code", required = false) List<String> codeList) {
		
		if(codeList == null) {
			model.addAttribute("msg", "판매할 상품을 등록하지 않았습니다.");
			model.addAttribute("url", "/sale");
			return "/alert/msg";
		}
		
		for (String code : codeList) {
			try {
				saleService.updateProductTable(code); //1. 판매정보를 재고(product)테이블에 업데이트
			    									  //2. 판매정보를 통계(total_sales)테이블에 업데이트
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		saleService.reset();						  //3. 판매테이블 리셋

		return "redirect:/sale";
	}

}
