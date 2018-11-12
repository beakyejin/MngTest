<%@page import="kr.co.hk.DeliveryVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<DeliveryVO> list = (List<DeliveryVO>)request.getAttribute("list"); %>
<h3>배송 조회</h3>
<%if(list != null && list.size()>0){%>
<table>
	<tr>
		<th>배송번호</th>
		<th>주문일자</th>	
		<th>배송일자</th>
		<th>주문번호</th>
		<th>상품번호</th>
		<th>상품명</th>	
		<th>상품단가</th>
		<th>주문수량</th>
		<th>합계금액</th>
	</tr>
	<%for(DeliveryVO vo : list){ %>
	<tr>
		<td><%=vo.getD_no() %></td>
		<td><%=vo.getS_date() %></td>
		<td><%=vo.getD_date() %></td>
		<td><%=vo.getS_no() %></td>
		<td><%=vo.getP_no() %></td>
		<td><%=vo.getP_name() %></td>
		<td><%=vo.getP_price() %></td>
		<td><%=vo.getS_quantity() %></td>
		<td><%=vo.getS_price() %></td>
	</tr>
	<%} %>
</table>
<%}else{%>
	배송된 상품이 없습니다.
<%}%>