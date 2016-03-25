<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form action="/user/result" method="GET" id="file-user">
        <c:forEach var="str" items="${str}">
            <table border="0">
                <tr>
                    <td>${str[0]}</td>
                </tr>
                <tr>
                    <td><pre>${str[1]}</pre></td>
                </tr>
                <tr>
                    <td>${str[2]}</td>
                </tr>
                <tr>
                    <td><pre>${str[3]}</pre></td>
                </tr>
                    <td>
                        <input type="submit" value="сохранить в zip">
                    </td>
                </tr>
            </table>
        </c:forEach>
    </form>
</div>