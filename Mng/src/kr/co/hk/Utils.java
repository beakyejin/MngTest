package kr.co.hk;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {
	public static void Dispatcher(String title, String target, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setAttribute("title", title);
		req.setAttribute("target", target);
		RequestDispatcher rd = req.getRequestDispatcher("template.jsp");
		rd.forward(req, res);
	}
}
