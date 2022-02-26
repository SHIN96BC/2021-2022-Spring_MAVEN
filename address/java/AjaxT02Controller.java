package sbc.ct.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import sbc.addr.domain.Address;
import sbc.addr.service.AddressAjaxService;

@Controller
@RequestMapping("/ajax02")
@AllArgsConstructor
public class AjaxT02Controller {
	private AddressAjaxService service;
	
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Address address = service.selectBySeqS(seq);
		
		System.out.println("#address: " + address);
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String json = om.writeValueAsString(address);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
		}catch(JsonProcessingException jpe) {
		}catch(IOException ie) {
		}
	}
	@PostMapping("search02.do")
	public void search02(String name, HttpServletResponse response) {
		List<Address> addressList = service.selectByNameS(name);
		
		ObjectMapper om = new ObjectMapper();
		
		try {
			String json = om.writeValueAsString(addressList);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
		}catch(JsonProcessingException jpe) {
		}catch(IOException ie) {
		}
	}
}
