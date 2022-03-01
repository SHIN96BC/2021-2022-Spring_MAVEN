package sbc.addr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sbc.addr.domain.Address;


public interface AddressService {
	List<Address> listS();
	boolean insertS(Address address);
	void deleteS(long seq);
}
