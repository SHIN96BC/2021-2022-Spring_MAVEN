package sbc.addr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sbc.addr.domain.Address;
import sbc.addr.service.AddressAjaxService;

@RestController
@RequestMapping("rest_addr")
public class AddressRestController {
	@Autowired
	private AddressAjaxService service;
	
	//(1) Create( insert )
	/*
	//자바스크립트 객체로 날아온 데이터를 받을 떄
	@PostMapping("create")
	public void create(Address address) {
		System.out.println("#AddressRestController create: " + address);
		service.insertS(address);
	}
	*/
	// json 객체로 날아온 데이터를 받을 떄
	@PostMapping("create")
	public void create(@RequestBody Address address) {
		System.out.println("#AddressRestController create: " + address);
		service.insertS(address);
	}
	
	
	//(2) Read( select )
	@GetMapping("read")
	public List<Address> read() {
		List<Address> list = service.listS();
		return list;
	}
	
	//json에서 하나의 데이터를 넘길때는( 보통 숫자에 많이씀 ) read?seq=13 가 아니라 read/13.json 이런 방식으로 넘기니까 
	// 그 데이터를 받으려면 read/{seq} 이런식으로 써주고 @PathVariable Long seq 이렇게 같은 변수명으로 받아야한다.
	@GetMapping("read/{seq}")
	public Address read(@PathVariable Long seq) {
		Address address = service.selectBySeqS(seq);
		return address;
	}
	
	// 여러개를 넘길때는 read.json?na=나&ga=가 이런식으로 넘기고 받을때도 params로 받는다.
	@GetMapping(value="read", params = {"na"})
	public List<Address> read(@RequestParam String name) {
		List<Address> list = service.selectByNameS(name);
		return list;
	}
	
	@DeleteMapping("delete/{seq}")
	public void delete(@PathVariable Long seq) {
		service.deleteS(seq);
	}
}
