<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 게시판에서 공통으로 쓸 form (파라미터 값 전송 시, 한글깨짐 방지를 위함)-->
<c:set var="bcode" value="${parameter.bcode}"/>
<c:set var="pg" value="${parameter.pg}"/>
<c:set var="key" value="${parameter.key}"/>
<c:set var="word" value="${parameter.word}"/>
<c:set var="seq" value="${seq}"/>
<form id="commonForm">
	<input type="hidden" name="bcode" id="bcode">
	<input type="hidden" name="pg" id="pg">
	<input type="hidden" name="key" id="key">
	<input type="hidden" name="word" id="word">
	<input type="hidden" name="seq" id="seq">

</form>