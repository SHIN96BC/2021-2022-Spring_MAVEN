package sbc.addr.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
	private long seq;
	private String name;
	private String addr;
	private Date rdate;
}
