package sbc.addr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sbc.addr.domain.Address;
import sbc.addr.service.AddressService;

@Controller
@RequestMapping("rest_addr")
public class AddressController4RestController { // 4 == for
	
	@GetMapping("write.do")
	public String write(){
		return "rest_addr/write";
	}
}
