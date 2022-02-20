package sbc.addr.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sbc.addr.domain.Address;

@Repository
public class MemoryAddressMapper implements AddressMapper{
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<Address> list(){
		List<Address> address = addressMapper.list();
		return address;
	}
	@Override
	public boolean insert(Address address) {
		return addressMapper.insert(address);
	}
	@Override
	public void delete(long seq) {
		addressMapper.delete(seq);
	}
}
