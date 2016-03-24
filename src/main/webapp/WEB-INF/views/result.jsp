<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/result" method="POST" id="file-user">
        <c:forEach var="str" items="${str}">
            <table border="0">
                <tr>
                    <td>${str[0]}</td>
                </tr>
                <tr>
                    <td>${str[1]}</td>
                </tr>
                <tr>
                    <td>${str[2]}</td>
                </tr>
                <tr>
                    <td>${str[3]}</td>
                </tr>
                    <td>
                        <input type="submit" value="сохранить в zip">
                    </td>
                </tr>
            </table>
        </c:forEach>
        <%--<div>Result: ${description}</div>--%>
    </form>
</div>