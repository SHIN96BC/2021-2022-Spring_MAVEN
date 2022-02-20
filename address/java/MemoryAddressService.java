package sbc.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbc.addr.domain.Address;
import sbc.addr.mapper.MemoryAddressMapper;

@Service
public class MemoryAddressService implements AddressService{
	@Autowired
	private MemoryAddressMapper mapper;
	
	@Override
	public List<Address> listS(){
		return mapper.list();
	}
	@Override
	public boolean insertS(Address address) {
		return mapper.insert(address);
	}
	@Override
	public void deleteS(long seq) {
		mapper.delete(seq);
	}
}
