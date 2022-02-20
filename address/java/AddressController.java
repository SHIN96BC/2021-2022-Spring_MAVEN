package sbc.addr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sbc.addr.domain.Address;
import sbc.addr.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService service;
	
	@GetMapping("/list.do")
	public String list(Model model){
		List<Address> addressList = service.listS();
		model.addAttribute("addressList", addressList);
		return "address/list";
	}
	@GetMapping("/input.do")
	public String input(Model model){
		return "address/input";
	}
	@PostMapping("/insert.do")
	public String insert(Address address, Model model){
		boolean flag = service.insertS(address);
		model.addAttribute("flag", flag);
		return "address/insert";
	}
	@GetMapping("/delete.do")
	public String delete(long seq, Model model){
		service.deleteS(seq);
		return "redirect:/address/list.do";
	}
}
