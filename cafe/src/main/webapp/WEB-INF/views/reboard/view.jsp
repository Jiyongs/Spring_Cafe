<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp" %> <!-- *** -->
<%@ include file="/WEB-INF/views/commons/logincheck.jsp" %> <!-- *** -->
<%@ include file="/WEB-INF/views/commons/board_common.jsp" %> <!-- *** -->

<script>

$(document).ready(function(){
	
	// 글 쓰기 화면으로 이동
	$(".moveWriteBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/write").submit();
	});

	// 답글 달기 버튼 이벤트
	$(".moveReplyBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		$("#seq").val("${article.seq}");
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/reply").submit();
	});
	
	// 최신 목록 보기 이벤트 (1페이지)
	$(".firstListBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/list").submit();
	});
	
	// 목록 보기 이벤트 (보고있던 페이지)
	$(".listBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/list").submit();
	});
	
	// TODO: 수정, 삭제 해보기####################################################
	// 내 글 수정 페이지로 이동
	$(".moveModifyBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("1");
		$("#key").val("");
		$("#word").val("");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/list").submit();
	});
	
	// 내 글 삭제 이벤트
	$(".moveDeleteBtn").click(function(){
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		
		$("#commonForm").attr("method", "GET").attr("action", "${root}/reboard/list").submit();
	});
	// TODO: 수정, 삭제 해보기####################################################

	
	
	// [댓글스]
	
	
	// 댓글 수정 작성란 보이기
	$(document).on("click", ".mmodifyBtn", function(){

		$(this).parent().parent().next("tr").css("display", "");
		$(this).parent().parent("tr").css("display", "none");
	});
	
	// 댓글 수정 취소 이벤트
	$(document).on("click", ".memoModifyCancelBtn", function(){
		$(this).parent().parent("tr").css("display", "none");
		$(this).parent().parent().prev("tr").css("display", "");
		//$(this).parent().parent("tr").attr("style", "display:none;");
	});

	
	// 댓글 수정 이벤트
	$(document).on("click", ".memoModifyBtn", function(){
		
		$(this).parent().parent().prev("tr").css("display", "");

		var newMcontent = $(this).parent().prev("td").children().val();
		
		$.ajax({
			url : '${root}/memo/' + $(this).parent("td").attr("data-seq") + '/' + $(this).parent("td").attr("data-mseq") +'/' + newMcontent,
			type : 'PUT',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			success : function(response) {
				makeMemoList(response);
			}
		});
		
	
	});

	
	
	// 댓글 삭제 이벤트
	$(document).on("click", ".mdeleteBtn", function(){
		
		$.ajax({
			url : '${root}/memo/' + $(this).parent("td").attr("data-seq") + '/' + $(this).parent("td").attr("data-mseq"),
			type : 'DELETE',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			success : function(response) {
				makeMemoList(response);
				$("mcontent").val('');  // 댓글 작성창 지우기
			}
		});
		
	});
	
	getMemoList(); // 댓글 목록 보여주기
	
	$("#memoBtn").click(function(){
		if('${userInfo == null}' == "true") {
			// 비로그인,
			alert("로그인 하세요!");
		} else {
			var seq = '${article.seq}';
			var mcontent = $("#mcontent").val();
			var param = JSON.stringify({'seq' : seq, 'mcontent' : mcontent});
			if(mcontent.trim().length != 0) {
				$.ajax({
					url : '${root}/memo',
					type : 'POST',
					contentType : 'application/json;charset=UTF-8',
					dataType : 'json',
					data : param,
					success : function(response) {
						makeMemoList(response);
						$("#mcontent").val('');  // 댓글 작성창 지우기
					}
				});
			}
			
		}
	});
	
	function getMemoList(){
		
		$.ajax({
			url : '${root}/memo',
			type : 'GET',
			contentType : 'application/json;charset=UTF-8',
			dataType : 'json',
			data : {'seq' : '${article.seq}'},
			success : function(response) {
				makeMemoList(response);
				$("mcontent").val('');  // 댓글 작성창 지우기
			}
		});
		
	}
	
	function makeMemoList(memos){
		var memocnt = memos.memolist.length;
		var memostr = '';
		for(var i = 0; i < memocnt; i++){
			var memo = memos.memolist[i];
			memostr += '<tr>';
			memostr += '	<td>' + memo.name +'</td>';
			memostr += '	<td style="padding: 10px">';
			memostr += 			memo.mcontent;
			memostr += '	</td>';
			memostr += '	<td width="70" style="padding: 10px">';
			memostr += 		  	memo.mtime
			memostr += '	</td>';
			
			// 원글 작성자 = 댓글 작성자 이면,
			if('${userInfo.id}' == memo.id){
				memostr += '	<td width="70" style="padding: 10px" data-seq="' + memo.seq +'" data-mseq="'+ memo.mseq +'">';
				memostr += '		<input type="button" class="mmodifyBtn" value="수정" />';
				memostr += '		<input type="button" class="mdeleteBtn" value="삭제" />';
				memostr += '	</td>';
				
				memostr += '</tr>';
				
				// 글 수정 클릭 시 , display = visible로 바꾸기
				memostr += '<tr style="display:none;">';
				memostr += '	<td colspan="3" style="padding: 10px">';
				memostr += '		<textarea class="mcontent" cols="160" rows="5">' + memo.mcontent +'</textarea>';
				memostr += '	</td>';
				memostr += '	<td width="100" style="padding: 10px" data-seq="' + memo.seq +'" data-mseq="'+ memo.mseq +'">';
				memostr += '		<input type="button" class="memoModifyBtn" value="글수정"/>';
				memostr += '		<input type="button" class="memoModifyCancelBtn" value="취소"/>';
				memostr += '	</td>';
				memostr += '</tr>';
				//
			
			}else {
				
				memostr += '</tr>';
	
			}
						
			
			memostr += '<tr>';
			memostr += '	<td class="bg_board_title_02" colspan="4" height="1"';
			memostr += '	style="overflow: hidden; padding: 0px"></td>';
			memostr += '</tr>';
		}
		
		$("#mlist").empty();
		$("#mlist").append(memostr);
	}
	

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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<form name="bbsForm" id="bbsbbs" method="post"><input
		type="hidden" name="" value="">
	<tr>
		<td valign="bottom" nowrap>
		<img
			src="${root}/img/board/btn_write_01.gif" class="moveWriteBtn"
			width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기">
		<img
			src="${root}/img/board/btn_reply.gif" class="moveReplyBtn"
			width="40" height="22"
			border="0" align="absmiddle" alt="답글">
	<c:if test="${userInfo.id == article.id}">
		<img
			src="${root}/img/board/btn_modify.gif" class="moveModifyBtn"
			border="0" align="absmiddle" alt="글수정">
		<img
			src="${root}/img/board/btn_delete.gif" class="moveDeleteBtn"
			border="0" align="absmiddle" alt="글삭제">
	</c:if>
		</td>
		
		<td valign="bottom" width="100%" style="padding-left: 4px"></td>
		<td align="right" nowrap valign="bottom">
		<label class="firstListBtn">최신목록</label> <font color="#c5c5c5">|</font>
		<label class="listBtn">목록</label> <font color="#c5c5c5">|</font>

		<a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a></td>
	</tr>
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="5" cellspacing="0" width="100%">
	<tr>
		<td class="bg_board_title_02" colspan="2" height="2"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="28">
		<td class="bg_board_title" colspan="2" style="padding-left: 14px">
		<b><font class="text">${article.subject}</font></b></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr height="26">
		<td width="100%" style="padding-left: 14px"><font class="stext">번호
		:</font> <font class="text_commentnum">${article.seq}</font> &nbsp; <font
			class="stext">글쓴이 :</font> <a href="javascript:;"
			onClick="showSideView();" class="link_board_02">${article.name}</a><br>
		</td>
		<td style="padding-right: 14px" nowrap class="stext">조회 : <font
			class="text_commentnum">${article.hit}</font> &nbsp; 스크랩 : <font
			class="text_commentnum">0</font> &nbsp; 날짜 : <font
			class="text_commentnum">${article.logtime}</font></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<table border="0" cellpadding="15" cellspacing="0" width="100%">
	<tr valign="top">
		<td bgcolor="#ffffff" width="100%" class="text"
			style="padding-bottom: 8px; line-height: 1.3" id="clix_content">



		<P>${article.content}</P>



		</td>
		<td nowrap valign="top" align="right" style="padding-left: 0px">

		</td>
	</tr>
</table>

<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td class="bg_board_title_02" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<!-- 하단 페이징 -->
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="3" height="5" style="padding: 0px"></td>
	</tr>
	<tr valign="top">
		<td nowrap>
		<img
			src="${root}/img/board/btn_write_01.gif" class="moveWriteBtn"
			width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기">
		<img
			src="${root}/img/board/btn_reply.gif" class="moveReplyBtn"
			width="40" height="22"
			border="0" align="absmiddle" alt="답글">
		</td>
		<td style="padding-left: 4px" width="100%"><a href=""
			target="new"><img src="${root}/img/board/btn_print.gif"
			width="30" height="18" border="0" align="absmiddle" alt="인쇄"></a></td>

		<td align="right" nowrap>
		<label class="firstListBtn">최신목록</label>
		<font color="#c5c5c5">|</font>
		<label class="listBtn">목록</label>
		<font color="#c5c5c5">|</font> <a href="javascript:goBbsRead();"><img
			src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
			hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
			href="javascript:goBbsRead();">아랫글<img
			src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
			hspace="3"></a>
		</td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tr>
		<td style="padding: 10px">
		<textarea id="mcontent" cols="160" rows="5"></textarea>
		</td>
		<td width="100" style="padding: 10px">
		<input type="button" id="memoBtn" value="글작성"/>
		
		</td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="2" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
</table>

<br>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td colspan="4" height="5" style="padding: 0px"></td>
	</tr>
	<tr>
		<td class="bg_board_title_02" colspan="4" height="1"
			style="overflow: hidden; padding: 0px"></td>
	</tr>
	<tbody id="mlist">
	
	</tbody>
</table>

<br>

<%@ include file="/WEB-INF/views/commons/template/bottom.jsp" %> <!-- *** -->