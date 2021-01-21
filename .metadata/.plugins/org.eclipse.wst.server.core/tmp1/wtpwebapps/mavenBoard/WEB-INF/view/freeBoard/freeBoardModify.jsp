<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function resetForm(){
	$("#title").val("");
	$("#content").val("");
}

$(function(){
	
	$("#modifyBtn").click(function(e){
		
		e.preventDefault();
		
		var deleteBlank = / /gi;
		
		var title = $("#title").val().replace(deleteBlank, "");
		var content = $("#content").val().replace(deleteBlank, "");
		
		if ( title == "" || content == "" ){
			alert("�����̳� ������ ����� ���� �����ϴ�.");
			return;
		}
		
		var inputData = $("#modifyForm").serialize();
		
		$.ajax({
			data: inputData,
			type: "POST",
			url: "./freeBoardModifyPro.ino",
			success: function(data){
				var re = data.re;
				var num = data.num;
				var list = data.list;
				
				if(re > 0){
					alert("�Խù��� �����Ǿ����ϴ�!");
					location.href="/mavenBoard/main.ino";
				}else{
					alert("�Խù� ������ �����߽��ϴ�!\n�Խù� ���� ���� �ʹ� ���� �� �ֽ��ϴ�.");
				}
			}
		});
	});
	
});
</script>
</head>
<body>

	<div>
		<h1>�����Խ���</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">����Ʈ��</a>
	</div>
	<hr style="width: 600px">

	<form id="modifyForm" method="POST">
		
		<input type="hidden" name="num" value="${ freeBoardDto.num }"/>
		<table border="1">
			<tbody>
				<tr>
					<td style="width: 150px;" align="center">Ÿ�� :</td>
					<td style="width: 400px;">
						<select id="codeTypeSelect" name="codeType">
							<option value="01" <c:if test="${ freeBoardDto.codeType eq '01'}">selected</c:if>>����</option>
							<option value="02" <c:if test="${ freeBoardDto.codeType eq '02' }">selected</c:if>>�͸�</option>
							<option value="03" <c:if test="${ freeBoardDto.codeType eq '03' }">selected</c:if>>QnA</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">�̸� :</td>
					<td style="width: 400px;"><input type="text" name="name" value="${ freeBoardDto.name }" readonly/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">���� :</td>
					<td style="width: 400px;"><input type="text" id="title" name="title" value="${ freeBoardDto.title }"/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">���� :</td>
					<td style="width: 400px;"><textarea id="content" name="content" rows="25" cols="65" >${ freeBoardDto.content }</textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td></td>
					<td align="right">
					<input type="submit" value="�����ϱ�" id="modifyBtn">
					<input type="button" value="�ٽþ���" onClick="resetForm();">
					<input type="button" value="���" onclick="location.href='./main.ino'">
					&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</tfoot>
		</table>

	</form>
</body>
</html>