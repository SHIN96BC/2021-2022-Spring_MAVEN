package sbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerachResult {
	private long ps;
	private long cp;
	private long totalpagecount;
	private long totalcount;
	private long nowmaxpage;
	private long nowminpage;
	private long max;
	private long min;
	private String columname;
	private String likekey;
}
