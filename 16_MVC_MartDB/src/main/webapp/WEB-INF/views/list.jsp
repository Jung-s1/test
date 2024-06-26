<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
list.jsp<br>

<style>
	table{
		border-collapse: collapse;
		width: 800;
	}
	th{
		text-align: center;
	}
	th,td{
		padding: 10px;
	}
	.err{
		font-size: 9pt;
		color: red;
		font-weight: bold;
	}
</style>
<script type="text/javascript">
function insert(){
	location.href = "form";
}
function allCheck(obj){
	//alert();
	var rcheck = document.getElementsByName("rowcheck");
	
	var check = obj.checked;
	
	if(check){
		for(var i=0; i<rcheck.length; i++) {
			rcheck[i].checked = true;
		}	
	}else {
		for(var i=0; i<rcheck.length; i++) {
			rcheck[i].checked = false;
		}
	}
}//allcheck

function selectdelete(){
	var rowcheck = document.getElementsByName("rowcheck");
	var flag = false;
	for(var i=0;i<rowcheck.length;i++) {
		if(rowcheck[i].checked) {
			flag = true;
		}
	}
	if(flag == false) {
		alert("삭제할 항목을 1개 이상 선택하세요.");
		return;
	}
	document.myform.submit();
}//selectDelete
</script>
<h1>마트 상품 구매 내역</h1>
<form action="deleteAll" name="myform">
<table border="1">
	<tr>
		<th>
			<input type="checkbox" name="allSelect" onClick="allCheck(this)">
		</th>
		<th>번호</th>	
		<th>아이디</th>	
		<th>비밀번호</th>	
		<th>구매상품</th>	
		<th>배송시간</th>	
		<th>결제방법</th>	
		<th>결제동의</th>	
		<th>삭제</th>	
		<th>수정</th>	
	</tr>
	<c:choose>
		<c:when test="${fn:length(lists)==0}">
			<tr>
				<td colspan="10">저장된 데이터가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
		<c:forEach var="i" items="${lists}">
			<tr>
				<td><input type="checkbox" name="rowcheck" value = "${i.num }"></td>
				<td>${i.num}</td>
				<td>${i.id}</td>
				<td>${i.pw}</td>
				<td>${i.product}</td>
				<td>${i.time}</td>
				<td>${i.approve}</td>
				<td>
					<c:if test="${i.agree eq '1'}">동의함</c:if>
				</td>
				<td><a href="delete?num=${i.num}">삭제</a></td>
				<td><a href="updateForm?num=${i.num}">수정</a></td>
			</tr>
		</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="10">
			<a href="form">삽입</a>
			<input type="button" value="삭제" onclick="selectdelete()">
		</td>
	</tr>
</table>
</form>