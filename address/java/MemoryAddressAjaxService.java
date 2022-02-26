package sbc.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbc.addr.domain.Address;
import sbc.addr.mapper.AddressAjaxMapper;

@Service
public class MemoryAddressAjaxService implements AddressAjaxService{
	@Autowired
	private AddressAjaxMapper addressAjaxMapper;
	
	@Override
	public List<Address> listS(){
		return addressAjaxMapper.list();
	}
	@Override
	public boolean insertS(Address address) {
		return addressAjaxMapper.insert(address);
	}
	@Override
	public void deleteS(long seq) {
		addressAjaxMapper.delete(seq);
	}
	@Override
	public Address selectBySeqS(long seq) {
		return addressAjaxMapper.selectBySeq(seq);
	}
	@Override
	public List<Address> selectByNameS(String name) {
		return addressAjaxMapper.selectByName(name);
	}
}
