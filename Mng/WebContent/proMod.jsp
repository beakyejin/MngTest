<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function clkSubmit() {
		var frm = document.frm;
		
		if(frm.p_no.value == ""){
			alert("상품번호를 입력해주세요!");
			frm.p_no.focus();
			return false;
		}else if(frm.p_name.value == ""){
			alert("상품명을 입력해주세요!");
			frm.p_name.focus();
			return false;
		}else if(frm.p_price.value == ""){
			alert("상품단가를 입력해주세요!");
			frm.p_price.focus();
			return false;
		}else if(frm.p_info.value == ""){
			alert("상품정보를 입력해주세요!");
			frm.p_info.focus();
			return false;
		}
		return true;
	}
	
	var msg = "${msg}";
	if(msg != ""){
		alert("${msg}");
		location.href="proView";
	}
</script>
<h3>상품 수정</h3>
<form name="frm" action="proMod" method="post" onsubmit="return clkSubmit()"> 
	<table>
		<tr>
			<th>상품번호</th>
			<td>
				<input type="text" name="p_no" value="${vo.p_no}">  
			</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>
				<input type="text" name="p_name" value="${vo.p_name}">  
			</td>
		</tr>
		<tr>
			<th>상품단가</th>
			<td>
				<input type="text" name="p_price" value="${vo.p_price}">  
			</td>
		</tr>
		<tr>
			<th>상품정보</th>
			<td>
				<input type="text" name="p_info" value="${vo.p_info}">   
			</td>
		</tr>
	</table>
	<div class="btn">
		<input type="submit" value="수정">
	</div>
</form>