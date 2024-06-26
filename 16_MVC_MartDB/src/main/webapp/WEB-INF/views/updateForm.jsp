<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
updateForm.jsp<br>
<style type="text/css">
		
		.error{
			font-size: 9pt;
			color: red;
			font-weight: bold;
		}
		
	</style>
<% 
		String[] product = {"식품","의류","도서","가구"};
		String[] time = {"9시~12시","12시~15시","15시~18시"};
		String[] approve = {"카드","핸드폰"};
	%>
<h3>마트 상품 구매 내역 수정</h3>
	<form:form commandName="mart" action="update" method="post">
		<input type="hidden" name="num" value="${mart.num }">
		아이디 : <input type="text" name="id" value="${mart.id }">
		<form:errors path="id" cssClass="error"></form:errors>
		<br>
		비번 : <input type="text" name="pw" value="${mart.pw }">
		<form:errors path="pw" cssClass="error"></form:errors>
		<br>
		구매상품 : 
		<c:forEach var="product" items="<%=product %>">
			${product}<input type="checkbox" name="product" value="${product}" <c:if test="${fn:contains(mart.product,product)}"> checked </c:if>>
		</c:forEach>
		<form:errors path="product" cssClass="error"></form:errors>
		<br>
배송시간 : 
		<select name="time">
			<option value="">선택</option>
		<c:forEach var="time" items="<%=time %>">
			<option value="${time}" <c:if test="${mart.time==time}"> selected </c:if>>${time}</option>
		</c:forEach>
		</select>
		<form:errors path="time" cssClass="error"></form:errors>
		<br>
		결제방법 : 
		<c:forEach var="approve" items="<%=approve%>">
			<input type="radio" name="approve" value="${approve}" <c:if test="${mart.approve==approve}"> checked </c:if>>${approve}
		</c:forEach>
		<form:errors path="approve" cssClass="error"></form:errors>
		<br>
		결제 동의합니다. 
		<input type="checkbox" name="agree" value="1" <c:if test="${mart.agree != null}"> checked </c:if>>
		<form:errors path="agree" cssClass="error"></form:errors>
		<br> 
		<input type="submit" value="보내기">
	</form:form>	