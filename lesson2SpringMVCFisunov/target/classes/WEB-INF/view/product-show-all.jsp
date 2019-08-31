<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <h1>Products List</h1>
        <br>

        <ul>
            <c:forEach var="product" items="${products}">
                <li>ID: ${product.getId()} Title: ${product.getTitle()} Cost: ${product.getCost()}}</li>
            </c:forEach>
        </ul>
    </body>
</html>