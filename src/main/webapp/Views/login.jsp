<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorize</title>
    <style type="text/css">
        fieldset {
            margin: 50px;
            max-width: 25%;
            background-color: #eeeeee;
        }

        legend {
            background-color: gray;
            color: white;
            padding: 5px 10px;
        }

        input[type=text], select {
            width: auto;
            padding: 12px 20px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit], button {
            width: 100px;
            background-color: #4CAF50;
            color: white;
            padding: 8px;
            margin: 8px 30px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        div {
            background-color: #f2f2f2;
            padding: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<h3 style="color: red">${ErrorMessage}</h3>
<fieldset>
    <legend>Authorize</legend>
    <form action="${pageContext.servletContext.contextPath}/controller?command=login" method="POST">
        <input name="login" type="text" placeholder="login"/> <br/>
        <input name="password" type="text" placeholder="password"/> <br/>
        <input type="submit" value="login"/>
    </form>
</fieldset>
<form action="${pageContext.servletContext.contextPath}/controller?command=register_page" method="POST">
    <button> To registration </button>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>
