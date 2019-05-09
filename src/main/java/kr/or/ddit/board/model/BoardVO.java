package kr.or.ddit.board.model;

public class BoardVO {
	
	private String board_code;
	private String board_name;
	private String act_state;
	
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getAct_state() {
		return act_state;
	}
	public void setAct_state(String act_state) {
		this.act_state = act_state;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_code=" + board_code + ", board_name=" + board_name + ", act_state=" + act_state + "]";
	}
}
