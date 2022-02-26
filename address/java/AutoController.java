package sbc.addr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sbc.addr.domain.Address;
import sbc.addr.service.AddressAjaxService;

@Controller
@RequestMapping("auto")
public class AutoController {
	@GetMapping("auto.do")
	public String showView() {
		return "auto/autocomplete";	
	}
	
	@Autowired
	private AddressAjaxService service;
	@PostMapping("autoData")
	public @ResponseBody List<Address> getAddressList(String writer) {
		List<Address> list = service.selectByNameS(writer);
		return list;
	}
}
