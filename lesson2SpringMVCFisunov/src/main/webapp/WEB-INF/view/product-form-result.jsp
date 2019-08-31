<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <h1>Product Form Result</h1>
        <br>
        Title: ${product.getTitle()}
        <br>
        Cost: ${product.getCost()}
        <br>
        ID: ${product.getId()}
    </body>
</html>