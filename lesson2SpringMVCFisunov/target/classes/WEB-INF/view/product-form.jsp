<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="processForm" modelAttribute="product">
            Title: <form:input path="title" />
            <br>
            Cost:
            <form:select path="cost">
                <form:option value="100" label="100" />
                <form:option value="200" label="200" />
                <form:option value="300" label="300" />
                <form:option value="400" label="400" />
            </form:select>
            <br>
            ID: <form:input path="id" />
            <br>
            <input type="submit" value="ADD" />
        </form:form>

        <form:form action="showAll">
            <input type="submit" value="ShowAll" />
        </form:form>
    </body>
</html>
