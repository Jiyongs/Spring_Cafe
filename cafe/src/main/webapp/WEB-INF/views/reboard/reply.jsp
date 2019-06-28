<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp" %> <!-- *** -->
<%@ include file="/WEB-INF/views/commons/logincheck.jsp" %> <!-- *** -->

<script>
$(document).ready(function(){
	$("#writeBtn").click(function(){
		if($("#subject").val() == "") {
			alert("제목을 입력해주세요.");
			return;
		} else if ($("#content").val() == "") {
			alert("내용을 입력해주세요.");
			return;
		} else {
			$("#writeForm").attr("action", "${root}/reboard/reply").submit();
		}
	});
});
</script>

<!-- title -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="${root}/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 글을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="630">
	<tr>
		<td><img src="${root}/img/board/icon_arrow_04.gif" width="4"
			height="11" border="0" align="absmiddle" vspace="4"></td>
		<td width="100%" style="padding-left: 4px"><b>답글쓰기</b></td>
	</tr>
	<tr>
		<td width="630" colspan="2" height="2" class="bg_board_title_02"></td>
	</tr>
</table>
<br>

<form id="writeForm" name="writeForm" method="post" action=""
	style="margin: 0px">
<div id="attach_file_hdn"></div>

<!-- *********************** 페이지 이동 시마다 이동해야 하는 것 (절대 끊기면 안 됨!!!) *********************** -->
<input type="hidden" name="bcode" value="${parameter.bcode}">
<input type="hidden" name="pg" value="${parameter.pg}">
<input type="hidden" name="key" value="${parameter.key}">
<input type="hidden" name="word" value="${parameter.word}">

<input type="hidden" name="ref" value="${article.ref}">			<!-- 원글의 ref -->
<input type="hidden" name="lev" value="${article.lev}">			<!-- 원글의 lev -->
<input type="hidden" name="step" value="${article.step}">		<!-- 원글의 step -->
<input type="hidden" name="pseq" value="${article.seq}">		<!-- 원글의 seq -->
<!-- ************************************************************************************** -->

<table border="0" cellpadding="5" cellspacing="0" width="630"
	style="table-layout: fixed">

	<tr valign="top">
		<td width="95" nowrap style="padding-left: 8px; padding-top: 10px">
			<img src="${root}/img/board/e_dot.gif" width="4" height="4" border="0" align="absmiddle">
			<b>제목</b></td>
		<td colspan="5">
			<input name="subject" id="subject" type="text"
			size="76" maxlength="150" class="inp_02" style="width: 300px"
			value="Re : ${article.subject}">
			<img src="${root}/img/board/i_info.gif" width="12"
			height="11" border="0" align="absmiddle" vspace="8"
			style="margin: 3 3 0 6">
			<font class="stext">최대 한글 75자, 영문 150자</font><br>
	</tr>
	<tr>
		<td width="620" nowrap style="padding-left: 8px; padding-top: 10px"
			colspan="5"><img src="${root}/img/board/e_dot.gif" width="4"
			height="4" border="0" align="absmiddle">
			<b>글내용</b>
			<!-- *** textarea는 엔터, 띄어쓰기 다 적용되어 나타나게 됨! *** -->
				<textarea name="content" class="inp_02" cols="67" rows="25" scrollbars="no">
---------------------------------- [원글] ----------------------------------

${article.content}
				</textarea>
		</td>
	</tr>
</table>
<table width="630" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" height="1"></td>
	</tr>
</table>

<table border="0" cellpadding="2" cellspacing="0" width="630">
	<tr>
		<td height="10" style="padding: 0px"></td>
	</tr>
	<tr>
		<td align="center">
		<img id = "writeBtn"
			src="${root}/img/board/btn_register.gif" width="42" height="21"
			border="0" name="register" value="" alt="등록">
		<a href="javascript:history.back();">
			<img
			src="${root}/img/board/b_cancel.gif" width="42" height="21"
			border="0" name="cencel" value="" alt="취소">
		</a>
		</td>
	</tr>
</table>
</form>
<br>
<br>

<%@ include file="/WEB-INF/views/commons/template/bottom.jsp" %> <!-- *** -->