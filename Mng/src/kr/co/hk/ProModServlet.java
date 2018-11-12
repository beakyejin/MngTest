package kr.co.hk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/proMod")
public class ProModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductVO vo =  dao.getProDetail(p_no);
		request.setAttribute("vo", vo);
		
		Utils.Dispatcher("상품 수정", "proMod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		String p_name = request.getParameter("p_name");
		int p_price = Integer.parseInt(request.getParameter("p_price"));
		String p_info = request.getParameter("p_info");
		
		System.out.println("p_no: " + p_no);
		System.out.println("p_name: " + p_name);
		System.out.println("p_price: " + p_price);
		System.out.println("p_info: " + p_info);
		
		ProductVO vo = new ProductVO();
		vo.setP_no(p_no);
		vo.setP_name(p_name);
		vo.setP_price(p_price);
		vo.setP_info(p_info);
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.updateProduct(vo);
		
		request.setAttribute("p_no", p_no);
		request.setAttribute("vo", vo);
		request.setAttribute("msg", "수정이 완료되었습니다.");
		Utils.Dispatcher("상품 수정", "proMod", request, response);
		
	}

}
