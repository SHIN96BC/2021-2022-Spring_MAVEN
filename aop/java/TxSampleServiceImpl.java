package sbc.addr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import sbc.addr.mapper.TxSample1Mapper;
import sbc.addr.mapper.TxSample2Mapper;

@Log4j
@Service
public class TxSampleServiceImpl implements TxSampleService {
	@Autowired
	private TxSample1Mapper mapper1;
	@Autowired
	private TxSample2Mapper mapper2;
	
	@Transactional // 이 메소드안에 있는 것들중에 하나라도 에러가 발생하면 전부 롤백시킨다.
	@Override
	public void addDatas(String data) {
		log.info("#TxSampleServiceImpl addDatas() 1");
		mapper1.insertCol1(data);
		log.info("#TxSampleServiceImpl addDatas() 2");
		mapper2.insertCol2(data);
		log.info("#TxSampleServiceImpl addDatas() 3");
	}

}
