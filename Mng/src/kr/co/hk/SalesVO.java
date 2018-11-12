package kr.co.hk;

public class SalesVO {
	private int s_no;
	private int p_no;
	private int s_quantity;
	private int s_price;
	private String s_date;
	private int d_chk;
	
	/* 다른 테이블 */
	private String p_name;
	private int p_price;
	
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	
	/* 다른 테이블 */
	
	
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getS_quantity() {
		return s_quantity;
	}
	public void setS_quantity(int s_quantity) {
		this.s_quantity = s_quantity;
	}
	public int getS_price() {
		return s_price;
	}
	public void setS_price(int s_price) {
		this.s_price = s_price;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public int getD_chk() {
		return d_chk;
	}
	public void setD_chk(int d_chk) {
		this.d_chk = d_chk;
	}
	
}
