<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h2>Enter User Details</h2>
    <form:form method="post" action="/submit.do" modelAttribute="user">
        <form:label path="name">Name:</form:label>
        <form:input path="name" />
        <br/>
        <form:label path="email">Email:</form:label>
        <form:input path="email" />
        <br/>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
