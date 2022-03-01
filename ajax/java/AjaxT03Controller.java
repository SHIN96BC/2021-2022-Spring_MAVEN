package sbc.ct.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import sbc.addr.domain.Address;
import sbc.addr.service.AddressAjaxService;

@Controller
@RequestMapping("/ajax03")
@AllArgsConstructor
public class AjaxT03Controller {
	private AddressAjaxService service;
	
	// json 으로 받고 싶을때는 .do를 빼고 호출하는곳에 .json을 붙여서 search01.json 으로 호출해야한다.
	@GetMapping("search01")
	public @ResponseBody Address search01(long seq) {
		return service.selectBySeqS(seq);
	}
	@PostMapping("search02")
	public @ResponseBody List<Address> search02(String name) {
		return service.selectByNameS(name);
	}
}
