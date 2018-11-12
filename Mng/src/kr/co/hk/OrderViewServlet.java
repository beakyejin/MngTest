package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orderView")
public class OrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao = ProductDAO.getInstance();
		List<SalesVO> list =  dao.getSalesList();
		request.setAttribute("list", list);
		
		Utils.Dispatcher("주문조회", "orderView", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] noArr = request.getParameterValues("s_no");
		
		int i=0;
		for(String a: noArr){
			System.out.println("noArr["+i+"]: " + a);
			i++;
		}
		
		
		//배송테이블에 저장 
		for(int j=0; j<noArr.length; j++){
			ProductDAO dao = ProductDAO.getInstance();
			int s_no = Integer.parseInt(noArr[j]);
			dao.insertDelivery(s_no);
			dao.updateDelichk(s_no);
		}
		
		response.sendRedirect("orderView");
	}

}
