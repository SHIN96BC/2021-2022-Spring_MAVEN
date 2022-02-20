package sbc.addr.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import sbc.addr.domain.Address;

@Repository
public interface AddressMapper {
	List<Address> list();
	boolean insert(Address address);
	void delete(long seq);
}
