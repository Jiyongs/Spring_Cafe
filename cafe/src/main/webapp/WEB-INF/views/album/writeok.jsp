<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp" %> <!-- *** -->
<%@ include file="/WEB-INF/views/commons/board_common.jsp" %> <!-- *** -->

<c:if test="${errorMsg != null}">
<script>
	alert("${errorMsg}");
	document.location.href="${root}/index.jsp";
</script>
</c:if>

<script>

$(document).ready(function(){
	
	// 작성 글 확인 이벤트
	$("#viewBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		$("#seq").val("${seq}");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/view").submit();
	});

	// 목록 보기 이벤트
	$("#listBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/list").submit();
	});
	
	
});

</script>

<table width="100%" cellpadding="6" cellspacing="2" border="0"
	bgcolor="#ffffff" style="border: #e1e1e1 solid 1px">
	<tr>
		<td class="bg_board_title" width="100%"><img
			src="${root}/img/board/icon_arrow_08.gif" width="3" height="11"
			border="0" align="absmiddle" hspace="6" vspace="6"> <b>게시판</b>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#e1e1e1"
			style="overflow: hidden; padding: 0px;"></td>
	</tr>
	<tr>
		<td class="bg_menu" width="100%" style="padding: 25px" height="35"
			align="center"><b>게시물이 등록되었습니다.</b><br>
		<br>

		<div align="center">
		<img
			src="${root}/img/board/b_wirtecf.gif" id="viewBtn"
			width="91" height="21" border="0" align="absmiddle" alt="작성한 글 확인" hspace="10">

		<img src="${root}/img/board/poll_listbu1.gif" id="listBtn"
			width="62" height="21" border="0" align="absmiddle" alt="목록보기"
			hspace="10">
		</td>
	</tr>
</table>
<br>

<%@ include file="/WEB-INF/views/commons/template/bottom.jsp" %> <!-- *** -->