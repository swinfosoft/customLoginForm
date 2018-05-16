<html>
<head>
<title>Web App</title>
<jsp:include page="resources.jsp"/>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="alert alert-success">
			<h3>Sum is: ${sum}</h3>
		</div>	
	</div>
	<hr/>
	<div class="row">
		<jsp:include page="form.jsp"/>	
	</div>
</div>
</body>
</html>