package sbc.addr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartDTO {
	private String item;
	private int value;
	
	@Override
	public String toString() {
		return "#GoogleChartDTO item: " + item + ", value: " + value;
	}
}
