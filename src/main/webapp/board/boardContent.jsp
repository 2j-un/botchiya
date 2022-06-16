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
		<title>게시판 내용보기</title>
		<script>
			
		</script>
		<style>
			table {
				margin: 0 auto;
				width: 800px;
			}
			table, td {
			  border : 1px solid black;
			  border-collapse : collapse;
			  text-align: center;
			}
			.title {
				width: 100px;
				height: 50px;
			}
			.title2 {
				width: 100px;
				height: 300px;
			}
			.text-box {
				width: 500px;
				height: 300px;
			}
			h1 {
				text-align: center;
				margin-top: 50px;
				margin-bottom: 50px;
			}
			.btn {
				text-align: right;
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<h1>게시판 내용보기</h1>
		<form name="frm">
			<table>
				<tr>
					<td class="title">제목</td>
					<td><%=bv.getSubject() %></td>
				</tr>
				<tr>
					<td class="title2">내용</td>
					<td class="text-box"><%=bv.getContent() %></td>
				</tr>
				<tr>
					<td>파일 다운로드</td>
					<td>
						<% if (bv.getFilename() != null) {%>
							<img src="<%=request.getContextPath()%>/images<%=bv.getFilename()%>">
							<a href="<%=request.getContextPath()%>/board/fileDownload.do?filename=<%=bv.getFilename()%>"><%=bv.getFilename()%></a>
						<%} %>
					</td>
				</tr>
				<tr>
					<td class="title">작성자</td>
					<td><%=bv.getWriter() %></td>
				</tr>
				<tr>
					<td class="btn" colspan="2">
						<input type="button" name="Modify" value="수정" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.do?bidx=<%=bv.getBidx()%>'">
						<input type="button" name="Delete" value="삭제" onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.do?bidx=<%=bv.getBidx()%>'">
						<input type="button" name="Reply" value="답변" onclick="location.href='<%=request.getContextPath()%>/board/boardReply.do?bidx=<%=bv.getBidx()%>&originbidx=<%=bv.getOriginbidx()%>&depth=<%=bv.getDepth()%>&level_=<%=bv.getLevel_()%>'">
						<input type="button" name="List" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>