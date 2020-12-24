<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchBtn").on("click", function(){
			var regExIsNumeric = /^[0-9]+$/;
			var deleteBlank = / /gi;

			var searchSelect = $("#searchSelect").val();
			var searchKeyword = $("#searchKeyword").val().replace(deleteBlank, "");
						
			if(searchSelect == 'num' && !searchKeyword.replace(regExIsNumeric, "") == ""){
				alert("검색어를 확인해주세요.\n글번호 검색은 숫자만 가능합니다.");
				return;
			}
			
			var searchJson = {searchField: searchSelect, keyword: searchKeyword};
			
			console.log(searchJson);
			
			$.ajax({
				data: searchJson,
				contentType: 'application/json;charset=UTF-8',
				url: "./mainSearch.ino",
				success: function(data){
					console.log(data);
					$("#boardTable").empty();
					
					var tbody = $("<tbody></tbody>");
					
					if(data.length > 0){
						$.each(data, function(idx, board){
							var tr = $("<tr></tr>");
							var type = $("<td style='width: 55px; padding-left: 30px;' align='center'></td>").html(board.codeType);
							var num = $("<td style='width: 50px; padding-left: 10px;' align='center'></td>").html(board.num);
							var link = $("<a href='./freeBoardDetail.ino?num="+board.num+"'></a>");
							var title = $("<td style='width: 125px;' align='center'></td>").text(board.title);
							link.append(title);
							var name = $("<td style='width: 48px; padding-left: 50px;' align='center'></td>").html(board.name);
							var regdate = $("<td style='width: 100px; padding-left: 95px;' align='center'></td>").html(board.regdate);
							tr.append(type, num, link, name, regdate);
							tbody.append(tr);
						});
					}else{
						var h4 = $("<h4 style='padding: 20px'></h4>").html("검색어에 해당하는 결과가 존재하지 않습니다.");
						var button = $("<button>목록으로</button>").attr("onClick", "location.href='./main.ino'");
						tbody.append(h4, button);
					}
					
					$("#boardTable").append(tbody);
				},
				error: function(request, status, error){
					console.log(status);
					console.log(error);
				}
			});
		});
	});
</script>
</head>
<body>
	<div>
		<h1>자유게시판</h1>
	</div>
	
	<div>
		<select id="searchSelect">
			<option value="num">글번호</option>
			<option value="title">글제목</option>
		</select>
		<input type="text" id="searchKeyword"/>
		<button type="button" id="searchBtn" name="searchBtn">검색</button>
	</div>
	
	<div style="width:650px;" align="right">
		<a href="./freeBoardInsert.ino">글쓰기</a>
	</div>
	<hr style="width: 600px;">
	<div style="padding-bottom: 10px;">
		<table border="1">
			<thead>
				<tr>
					<td style="width: 55px; padding-left: 30px;" align="center">타입</td>
					<td style="width: 50px; padding-left: 10px;" align="center">글번호</td>
					<td style="width: 125px;" align="center">글제목</td>
					<td style="width: 48px; padding-left: 50px;" align="center">글쓴이</td>
					<td style="width: 100px; padding-left: 95px;" align="center">작성일시</td>
				</tr>
			</thead>
		</table>
	</div>
	<hr style="width: 600px;">

	<div id="boardDiv">
		<table id="boardTable" border="1">
			<tbody>
					<c:forEach var="dto" items="${ freeBoardList }">
					<tr>
						<td style="width: 55px; padding-left: 30px;" align="center">${ dto.codeType }</td>
						<td style="width: 50px; padding-left: 10px;" align="center">${ dto.num }</td>
						<td style="width: 125px; align="center"><a href="./freeBoardDetail.ino?num=${ dto.num }">${ dto.title }</a></td>
						<td style="width: 48px; padding-left: 50px;" align="center">${ dto.name }</td>
						<td style="width: 100px; padding-left: 95px;" align="center">${ dto.regdate }</td>
					<tr>
					</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>