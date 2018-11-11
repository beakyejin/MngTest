<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>상품세부정보 및 주문하기</h3>
<table>
	<tr>
		<th>상품명</th>
		<td>${vo.p_name}</td>
	</tr>
	<tr>
		<th>상품단가</th>
		<td>${vo.p_price}</td>
	</tr>
	<tr>
		<th>상품정보</th>
		<td>${vo.p_info}</td>
	</tr>
</table>
<form action="proSale" method="post">
	<input type="hidden" name="p_no" value="${vo.p_no}">
	주문수량
	<input type="number" name="s_quantity" value="1">
	<input type="submit" value="주문">
</form>