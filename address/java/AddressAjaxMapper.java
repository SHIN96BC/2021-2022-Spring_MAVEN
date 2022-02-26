package sbc.addr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import sbc.addr.domain.Address;


public interface AddressAjaxMapper {
	List<Address> list();
	boolean insert(Address address);
	void delete(long seq);
	
	Address selectBySeq(long seq);
	List<Address> selectByName(String name);
}
