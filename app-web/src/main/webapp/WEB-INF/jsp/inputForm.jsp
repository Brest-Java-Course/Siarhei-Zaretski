<html>
    <body>
        <form action="/submitData" method="post">
            <label path="login">Login:</label><input type="text" name="login"/><br/>
            <label path="name">Name:</label><input type="text" name="name"/><br/>
            <input type="submit" name="Submit">
        </form>
        <a href='<spring:url value="/users" />'> <spring:message code="user.create" /></a>
    </body>
</html>