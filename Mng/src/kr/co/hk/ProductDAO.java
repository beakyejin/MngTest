package kr.co.hk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static ProductDAO dao;
	
	public ProductDAO() {}  
	
	public static ProductDAO getInstance(){
		if(dao == null){
			dao = new ProductDAO();
		}
		return dao;
	}
	
	public int getPno(){
		int no = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select nvl(max(p_no), 100000)+1 from product ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				no = rs.getInt(1);
				System.out.println("no: " + no);
			}
			
		} catch (Exception e) {
			System.out.println("getPno 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, rs);
		}
		
		return no;
	}

	public void insertProduct(ProductVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into product "
				+ " (p_no, p_name, p_price, p_info) "
				+ " values"
				+ " (?, ?, ?, ?) ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getP_no());
			ps.setString(2, vo.getP_name());
			ps.setInt(3, vo.getP_price());
			ps.setString(4, vo.getP_info());
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("isnertProduct 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, null);
		}
	}

	public List<ProductVO> getProList() {
		List<ProductVO> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select  "
				+ "		p_no, "
				+ " 	p_name, "
				+ " 	p_price "
				+ " from product "
				+ " order by p_no asc ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ProductVO vo = new ProductVO();
				vo.setP_no(rs.getInt("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_price(rs.getInt("p_price"));
				
				System.out.printf("%d %s %d\n"
						, vo.getP_no(), vo.getP_name(), vo.getP_price());
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("getProList 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, rs);
		}
		
		return list;
	}

	public ProductVO getProDetail(int p_no) {
		ProductVO vo = new ProductVO();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select  "
				+ "		p_no, "
				+ " 	p_name, "
				+ " 	p_price,"
				+ "		p_info  "
				+ " from product "
				+ " where p_no=? ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_no);
			rs = ps.executeQuery();
			
			if(rs.next()){
				vo.setP_no(rs.getInt("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_price(rs.getInt("p_price"));
				vo.setP_info(rs.getString("p_info"));
				
				System.out.printf("%d %s %d %s\n"
						, vo.getP_no(), vo.getP_name(), vo.getP_price(), vo.getP_info());
			}
			
		} catch (Exception e) {
			System.out.println("getProDetail 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, rs);
		}
		
		return vo;
	}

	public void insertSales(SalesVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into sales "
				+ " (s_no, p_no, s_quantity, s_price, s_date, d_chk) "
				+ " values "
				+ " ( "
				+ "		(select nvl(max(s_no),100000)+1 from sales)"
				+ "	, ?, ?, ?, sysdate, 0) ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getP_no());
			ps.setInt(2, vo.getS_quantity());
			ps.setInt(3, vo.getS_price());
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("insertSales 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, null);
		}
	}

	public void updateProduct(ProductVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update product "
				+ " set "
				+ "		p_no=?, "
				+ "		p_name=?, "
				+ "		p_price=?, "
				+ "	 	p_info=? "
				+ " where p_no = ? ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getP_no());
			ps.setString(2, vo.getP_name());
			ps.setInt(3, vo.getP_price());
			ps.setString(4, vo.getP_info());
			ps.setInt(5, vo.getP_no());
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("updateProduct 에러!");
			e.printStackTrace();
		} finally{
			DBConnector.close(con, ps, null);
		}
		
	}

	public List<SalesVO> getSalesList() {
		List<SalesVO> list = new ArrayList<SalesVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select "
					+" 		b.s_no, a.p_name, a.p_price, " 
					+" 		b.s_quantity, b.s_price, "
					+" 		to_char(b.s_date, 'yyyy/mm/dd hh:mm:ss') as s_date "
					+" from product a "
					+" join SALES b "
					+" on a.p_no = b.p_no "
					+" where b.d_chk = 0 " ; 
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SalesVO vo = new SalesVO();
				vo.setS_no(rs.getInt("s_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_price(rs.getInt("p_price"));
				vo.setS_quantity(rs.getInt("s_quantity"));
				vo.setS_price(rs.getInt("s_price"));
				vo.setS_date(rs.getString("s_date"));
				
				list.add(vo);
				
				System.out.printf("%d %s %d %d %d %s\n"
						, vo.getS_no(), vo.getP_name(), vo.getP_price() 
						, vo.getS_quantity(), vo.getS_price(), vo.getS_date());
			}
			
		} catch (Exception e) {
			System.out.println("getSalesList 에러!");
			e.printStackTrace();
		} finally{
			DBConnector.close(con, ps, rs);
		}
		
		return list;
	}

	public void insertDelivery(int s_no) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " {call pro_delivery(?)} ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareCall(sql);
			ps.setInt(1, s_no);
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("insertDelivery 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, null);
		}
	}

	public void updateDelichk(int s_no) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "update sales "
				+ " set "
				+ "		d_chk = 1 "
				+ " where s_no = ? ";
		
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			ps.setInt(1, s_no);
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("updateDelichk 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, null);
		}
		
	}

	public List<DeliveryVO> getDeliveryList() {
		List<DeliveryVO> list = new ArrayList<DeliveryVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select "
				+"		a.d_no, b.s_date, a.d_date, "  
				+" 		a.s_no, c.p_no, c.p_name, "
				+" 		c.p_price, b.s_quantity, b.s_price "
				+" from delivery a "
				+" join sales b "
				+" on a.s_no = b.s_no "
				+" join product c " 	
				+" on b.p_no = c.p_no ";  
	
		try {
			con = DBConnector.getconn();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				DeliveryVO vo = new DeliveryVO();
				vo.setD_no(rs.getInt("d_no"));
				vo.setS_date(rs.getString("s_date"));
				vo.setD_date(rs.getString("d_date"));
				vo.setS_no(rs.getInt("s_no"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_price(rs.getInt("p_price"));
				vo.setS_quantity(rs.getInt("s_quantity"));
				vo.setS_price(rs.getInt("s_price"));
				
				list.add(vo);
				
				System.out.printf("%d %s %s %d %d %s %d %d %d\n", 
						vo.getD_no(), vo.getS_date(), vo.getD_date(), 
						vo.getS_no(), vo.getP_no(), vo.getP_name(), 
						vo.getP_price(), vo.getS_quantity(), vo.getS_price());
			}
			
		} catch (Exception e) {
			System.out.println("getDeliveryList 에러!");
			e.printStackTrace();
		} finally {
			DBConnector.close(con, ps, rs);
		}
		
		return list;
	}

}
