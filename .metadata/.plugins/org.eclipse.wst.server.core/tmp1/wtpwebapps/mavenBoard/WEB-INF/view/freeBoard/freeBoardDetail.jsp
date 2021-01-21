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
function clickDelete(){
	var num = $("#num").val();
	var re = confirm("������ �Խù��� ������ �Ұ����մϴ�.\n������ �����Ͻðڽ��ϱ�?");
	if(re){
		$.ajax({
			data: num,
			type: "GET",
			url: "./freeBoardDelete.ino?num="+num,
			success: function(data){
				var re = data.re;
				var list = data.list;
				if(re > 0){
					alert("�Խù��� �����Ǿ����ϴ�!");
					location.href="/mavenBoard/main.ino";
				}else{
					alert("�Խù� ������ �����߽��ϴ�!");
				}
			}
		})
	}
};
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

	<form name="insertForm">
		<input type="hidden" id="num" name="num" value="${ freeBoardDto.num }" />
		<table border="1">
			
			<tbody>
				<tr>
					<td style="width: 150px;" align="center">Ÿ�� :</td>
					<td style="width: 400px;"><input type="text" name="codeType" value="${ freeBoardDto.codeType }" readonly/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">�̸� :</td>
					<td style="width: 400px;"><input type="text" name="name" value="${ freeBoardDto.name }" readonly/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">���� :</td>
					<td style="width: 400px;"><input type="text" name="title"  value="${ freeBoardDto.title }" readonly/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">�ۼ�����</td>
					<td style="width: 400px;"><input type="text" name="regdate"  value="${ freeBoardDto.regdate }" readonly/></td>
				</tr>
				<tr>
					<td style="width: 150px;"align="center">���� :</td>
					<td style="width: 400px;"><textarea style="resize: none;" name="content" rows="25" cols="65" readonly >${ freeBoardDto.content }</textarea></td>
				</tr>
			</tbody>
			
			<tfoot>
				<tr>
					<td></td>
					<td align="right">
						<input type="button" value="����" onclick="location.href='./freeBoardModify.ino?num=${ freeBoardDto.num }'">
						<input type="button" value="����" onClick="clickDelete();">
						<input type="button" value="���" onclick="location.href='./main.ino'">
						&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>