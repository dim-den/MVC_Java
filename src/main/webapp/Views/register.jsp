<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../styles/form-style.css">
    <link rel="stylesheet" href="styles/form-style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<h3 style="color: red">${ErrorMessage}</h3>
<fieldset>
    <legend>Registration</legend>
    <form action="${pageContext.servletContext.contextPath}/controller?command=register" method="POST">
        <input name="login" type="text" placeholder="login"/> <br/>
        <input name="password" type="text" placeholder="password"/> <br/>
        <input type="submit" value="Registration"/>
    </form>
</fieldset>
<form action="${pageContext.servletContext.contextPath}/controller?command=login_page" method="POST">
    <button>To login</button>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>