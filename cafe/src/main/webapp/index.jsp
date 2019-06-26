<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.cafe.member.model.MemberDto"%>
<%
response.sendRedirect(request.getContextPath() + "/boardadmin/boardmenu");

// 임시 로그인 처리
MemberDto memberDto = new MemberDto();
memberDto.setId("shzy232");
memberDto.setName("신지영");
memberDto.setEmail("shzy232@naver.com");

session.setAttribute("userInfo", memberDto);
%>