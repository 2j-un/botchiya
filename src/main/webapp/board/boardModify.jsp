<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jspstudy.domain.BoardVo" %>
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>글 수정하기</title>
			<script>
				function check() {
				
					var fm = document.frm;
				
					if (fm.subject.value == ""){
						alert("제목을 입력해주세요.");
						fm.subject.focus();
						return;
					} else if (fm.content.value == ""){
						alert("내용을 입력해주세요");
						fm.content.focus();
						return;
					} else if (fm.writer.value == ""){
						alert("작성자를 입력해주세요");
						fm.writer.focus();
						return;
					} 
					
					fm.action = "<%=request.getContextPath()%>/board/boardModifyAction.do";
					fm.method = "post";
					fm.submit();
					
					return;
				}
			</script>
		<style>
			h1 {
				text-align: center;
				margin-top: 50px;
				margin-bottom: 50px;
			}
			.title-name {
				height: 30px;
			}
			
			.text-box {
				padding-bottom: 50px;
				width: 100%;
			}
			
			input[type="text"]{
				width: 100%;
				height: 50px;
			    border-radius: 10px;
			    border: 1px solid #cbcbcb;
			    padding-left: 20px;
			}
			
			table {
				margin: 0 auto;
				width: 1000px;
			}
		</style>
	</head>
		<body>
			<h1>글 수정하기</h1>
			
			<form name="frm">
			<input type="hidden" name="bidx" value="<%=bv.getBidx() %>">
				<table>
				
					<tr>
						<td class="title-name" colspan="2">제목</td>
					</tr>
					<tr>
						<td class="text-box" colspan="2"><input type = "text" name="subject" value="<%=bv.getSubject() %>" placeholder = "제목을 입력해주세요."></td>
					</tr>
					
					<tr>
						<td class="title-name" colspan="2">내용</td>
					</tr>
					<tr>
						<td class="text-box" colspan="2"> <input type = "text" name="content" value="<%=bv.getContent() %>" placeholder = "내용을 입력해주세요."> </td>
					</tr>
					
					<tr>
						<td class="title-name" colspan="2">작성자</td>
					</tr>
					<tr>
						<td class="text-box" colspan="2"><input type = "text" name = "writer" value="<%=bv.getWriter() %>" placeholder = "작성자를 입력해주세요."></td>
					</tr>
					
					<tr>
						<th>
							<input type="button" name="btn" onclick = "check();" value="확인"> <input type="reset" value = "취소">
						</th>
					</tr>
				
				</table>
			</form>
		</body>
</html>