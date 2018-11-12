package kr.co.hk;

/*
 *  a.d_no as 배송번호,
    b.s_date as 주문일자, 
    a.d_date as 배송일자, 
    a.s_no as 주문번호,     
    c.p_no as 상품번호,
    c.p_name as 상품명,
    c.p_price as 상품단가,
    b.s_quantity as 주문수량, 
    b.s_price as 합계금액*/
public class DeliveryVO {
	private int d_no;
	private int s_no;
	private String d_date;
	
	/*다른테이블*/
	private String s_date;
	private int p_no;
	private String p_name;
	private int p_price;
	private int s_quantity;
	private int s_price;
	
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
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
	}/*다른테이블*/
	
	public int getD_no() {
		return d_no;
	}
	public void setD_no(int d_no) {
		this.d_no = d_no;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getD_date() {
		return d_date;
	}
	public void setD_date(String d_date) {
		this.d_date = d_date;
	}
	
}
