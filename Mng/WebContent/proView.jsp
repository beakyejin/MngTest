<%@page import="kr.co.hk.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<ProductVO> list = (List<ProductVO>)request.getAttribute("list"); %>
<script type="text/javascript">
	function clkMod(p_no) {
		location.href="proMod?p_no="+p_no;
	}
</script>
<h3>상품 조회</h3>
<%if(list!=null && list.size()>0){ %>
<table>
	<tr>
		<th>상품번호</th>
		<th>상품명</th>
		<th>상품단가</th>
		<th>수정</th>
	</tr>
	<%for(ProductVO vo : list){ %>
	<tr>
		<td><%=vo.getP_no() %></td>
		<td>
			<a href="proSale?p_no=<%=vo.getP_no() %>">
				<%=vo.getP_name() %>
			</a>
		</td>
		<td><%=vo.getP_price() %></td>
		<td>
			<input type="button" value="수정" onclick="clkMod(<%=vo.getP_no() %>)" />
		</td>
	</tr>
	<%} %>
</table>
<%} else{%>
	등록된 상품이 없습니다!
<%} %>
