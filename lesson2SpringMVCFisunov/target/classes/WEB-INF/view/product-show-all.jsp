<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <h1>Products List</h1>
        <br>

        <ul>
            <c:forEach var="item" items="${products}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </body>
</html>