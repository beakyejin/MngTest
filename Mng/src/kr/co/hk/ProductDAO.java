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

	public void isnertProduct(ProductVO vo) {
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
}
