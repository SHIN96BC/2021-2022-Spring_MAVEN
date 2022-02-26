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

/* 4.3 이하 버전은
@AllargsConstructor
@Controller
@RequestMapping("/address")
public class AddressController {
	private AddressService addressService; 
 */

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired // 스프링 4.3 이상부터 지원
	private AddressService addressService; 
	/* 내가 찾은 방식
	@GetMapping("/list.do")
	public String list(Model model){
		List<Address> addressList = addressService.listS();
		model.addAttribute("addressList", addressList);
		return "address/list";
	}
	@GetMapping("/input.do")
	public String input(){
		return "address/input";
	}
	@PostMapping("/insert.do")
	public String insert(Address address, Model model){
		boolean flag = addressService.insertS(address);
		model.addAttribute("flag", flag);
		return "address/insert";
	}
	@GetMapping("/delete.do")
	public String delete(long seq, Model model){
		addressService.deleteS(seq);
		return "redirect:/address/list.do";
	}*/
	
	// 선생님 방식
	@GetMapping("/list.do")
	public ModelAndView list(){
		List<Address> list = addressService.listS();
		/* 1번 방식
		ModelAndView mv = new ModelAndView;
		mv.setViewName("address/list");
		mv.addObject("list", list);
		return mv;
		*/
		// 2번 방식
		ModelAndView mv = new ModelAndView("address/list", "list", list);
		return mv;
	}
	@GetMapping("/input.do")
	public String input(){
		return "address/input";
	}
	@PostMapping("/insert.do")
	public ModelAndView insert(Address address){
		boolean flag = addressService.insertS(address);
		ModelAndView mv = new ModelAndView("address/insert", "flag", flag);
		return mv;
	}
	@GetMapping("/delete.do")
	public String delete(long seq){
		addressService.deleteS(seq);
		return "redirect:list.do";
	}
}
