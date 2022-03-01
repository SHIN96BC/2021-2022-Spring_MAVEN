package sbc.ct.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sbc.addr.domain.Address;
import sbc.addr.service.AddressAjaxService;


@RestController
@RequestMapping("/ajax04")
@AllArgsConstructor
public class AjaxT04Controller {
	private AddressAjaxService service;
	
	// json 으로 받고 싶을때는 .do를 빼고 호출하는곳에 .json을 붙여서 search01.json 으로 호출해야한다.
	@GetMapping(value="search01", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Address search01(long seq) {
		return service.selectBySeqS(seq);
	}
	@PostMapping(value="search02", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Address> search02(String name) {
		return service.selectByNameS(name);
	}
	//RestController 는 String 을 리턴할때 .jsp를 찾지않고 그냥 스트링을 리턴한다.
	//.jsp를 찾고 싶으면 @RestController 대신에 json을 리턴해야하는 메소드에 @ResponseBody를 달아준다.
	@GetMapping("txt")
	public String getText() {
		return "good";
	}
}
