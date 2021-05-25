<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Title</title>
    <style type="text/css">
        table {
            margin: 50px;
            border-collapse: collapse;
            width: 25%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }

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
<jsp:include page="header.jsp"/>

<table class="responsive-table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Teacher</th>
        <th>Faculty</th>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.id}</td>
            <td>${subject.name}</td>
            <td>${subject.teacher}</td>
            <td>${subject.faculty}</td>
        </tr>
    </c:forEach>
</table>

<div style="display: flex; background-color: white">
    <fieldset>
        <h3 style="color: red">${DeleteErrorMessage}</h3>
        <legend>Delete</legend>
        <form action="${pageContext.servletContext.contextPath}/controller?command=delete_subject" method="POST">
            <input name="id" type="text" placeholder="Subject ID to delete"/> <br/>
            <input type="submit" value="Delete"/>
        </form>
    </fieldset>

    <fieldset>
        <h3 style="color: red">${AddErrorMessage}</h3>
        <legend>Add</legend>
        <form action="${pageContext.servletContext.contextPath}/controller?command=add_subject" method="POST">
            <input name="name" type="text" placeholder="Subject name"/> <br/>
            <input name="teacher" type="text" placeholder="Teacher"/> <br/>
            <input name="faculty" type="text" placeholder="Faculty"/> <br/>
            <input type="submit" value="Add"/>
        </form>
    </fieldset>

    <fieldset>
        <h3 style="color: red">${UpdateErrorMessage}</h3>
        <legend>Update</legend>
        <form action="UpdateSubjectServlet" method="POST">
            <input name="id" type="text" placeholder="Subject ID to update"/> <br/>
            <input name="name" type="text" placeholder="New subject name"/> <br/>
            <input name="teacher" type="text" placeholder="New teacher"/> <br/>
            <input name="faculty" type="text" placeholder="New faculty"/> <br/>
            <input type="submit" value="Update"/>
        </form>
    </fieldset>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
