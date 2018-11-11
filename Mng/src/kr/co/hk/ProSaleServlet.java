package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/proSale")
public class ProSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("p_no : " + p_no);
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductVO vo = dao.getProDetail(p_no);
		request.setAttribute("vo", vo);
		
		Utils.Dispatcher("상품주문", "proSale", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int s_quantity = Integer.parseInt(request.getParameter("s_quantity"));
		
		System.out.println("p_no" + p_no);
		System.out.println("s_quantity" + s_quantity);
		
		SalesVO vo = new SalesVO();
		vo.setP_no(p_no);
		vo.setS_quantity(s_quantity);
		
		ProductDAO dao = ProductDAO.getInstance();
		//TODO 2시간 5분
	}

}
