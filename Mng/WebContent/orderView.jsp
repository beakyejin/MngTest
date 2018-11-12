<%@page import="kr.co.hk.SalesVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<SalesVO> list = (List<SalesVO>)request.getAttribute("list"); %>
<form name="frm" action="orderView" method="post">
	<h3>주문 조회</h3>
	<% if(list!=null && list.size()>0){%>
	<table>
		<tr>
			<th>주문번호</th>
			<th>상품명</th>
			<th>상품단가</th>
			<th>주문수량</th>
			<th>합계금액</th>
			<th>주문일자</th>	
		</tr>
		<%for(SalesVO vo : list){ %>
		<tr>
			<td>
				<input type="checkbox" name="s_no" value="<%=vo.getS_no() %>">
				<%=vo.getS_no() %>
			</td>
			<td><%=vo.getP_name() %></td>
			<td><%=vo.getP_price() %></td>
			<td><%=vo.getS_quantity() %></td>
			<td><%=vo.getS_price() %></td>
			<td><%=vo.getS_date() %></td>
		</tr>
		<%} %>
	</table>
	<div class="btn">
		<input type="submit" value="배송완료">
	</div>
	<%} %>
</form>    