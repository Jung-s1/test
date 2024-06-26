<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
insertForm.jsp<br>
<style type="text/css">
		
		.err{
			font-size: 9pt;
			color: red;
			font-weight: bold;
		}
		
	</style>
<form:form commandName="mart" action="insert" method="get">
		아이디 : <input type="text" name="id" value="${mart.id}"> <form:errors path="id" cssClass="err"/> <br>
    	비번 : <input type="password" name="pw" value="${mart.pw}"> <form:errors path="pw" cssClass="err"/> <br>
    	
    	구매상품 : 
    	<input type="checkbox" name="product" value="식품" <c:if test="${fn:contains(mart.product, '식품')}">checked</c:if>> 식품 
    	<input type="checkbox" name="product" value="의류" <c:if test="${fn:contains(mart.product, '의류')}">checked="checked"</c:if>> 의류 
    	<input type="checkbox" name="product" value="도서" <c:if test="${fn:contains(mart.product, '도서')}">checked="checked"</c:if>> 도서 
    	<input type="checkbox" name="product" value="가구" <c:if test="${fn:contains(mart.product, '가구')}">checked="checked"</c:if>> 가구 
    	<form:errors path="product" cssClass="err"/>
    	<br>
배송시간 : 
    	<select name="time">
    		<option value="" <c:if test="${mart.time == ''}">selected="selected"</c:if>>선택</option>
    		<option value="9시~12시" <c:if test="${mart.time == '9시~12시'}">selected</c:if>>9시~12시</option>
    		<option value="12시~15시" <c:if test="${mart.time == '12시~15시'}">selected="selected"</c:if>>12시~15시</option>
    		<option value="15시~18시" <c:if test="${mart.time == '15시~18시'}">selected="selected"</c:if>>15시~18시</option>
    	</select> 
    	<form:errors path="time" cssClass="err"/>
    	<br>
    	
    	결제방법 : 
    	<input type="radio" name="approve" value="카드" <c:if test="${mart.approve == '카드'}">checked="checked"</c:if>> 카드
    	<input type="radio" name="approve" value="핸드폰" <c:if test="${mart.approve == '핸드폰'}">checked="checked"</c:if>> 핸드폰 
    	<form:errors path="approve" cssClass="err"/>
    	<br>

    	결제 동의합니다. <input type="checkbox" name="agree" value="1" <c:if test="${mart.agree == '1'}">checked="checked"</c:if>> 
    	<form:errors path="agree" cssClass="err"/>
    	<br>
    	
    	<input type="submit" value="보내기">
    	<input type="button" value="목록보기" onClick="location='list'">
    </form:form>    	