<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>msg</title>
</head>
<body>
	<script>
		alert('${msg}');
		${script}
		location.replace('${pageContext.request.contextPath}'+'${loc}');
	</script>
</body>
</html>