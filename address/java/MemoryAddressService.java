package sbc.addr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbc.addr.domain.Address;
import sbc.addr.mapper.AddressMapper;

@Service
public class MemoryAddressService implements AddressService{
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<Address> listS(){
		return addressMapper.list();
	}
	@Override
	public boolean insertS(Address address) {
		return addressMapper.insert(address);
	}
	@Override
	public void deleteS(long seq) {
		addressMapper.delete(seq);
	}
}
