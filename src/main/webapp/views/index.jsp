<html>
<head>
<title>Web App</title>
<jsp:include page="resources.jsp"/>
</head>
<body>
<div class="container">
	<div class="row">
		<jsp:include page="form.jsp"/>
	</div>
	<div class="row">
	<form action="logout" method="post">
			<input type="hidden" 
			name="${_csrf.parameterName}" 
			value="${_csrf.token}"/>
			<button type="submit" 
			class="btn btn-link">Logout</button>
		</form>
	</div>
</div>
</body>
</html>