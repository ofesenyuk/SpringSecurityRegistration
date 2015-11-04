<%-- 
    Document   : detail
    Created on : Oct 30, 2015, 9:42:45 AM
    Author     : sf
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="announcement.details"/></title>
        <%@ page isELIgnored="false" %>
    </head>
    <body>
        <form:form method="POST" modelAttribute="announcements">
                <fieldset>
                    <legend><spring:message code="announcement.details"/></legend>
                    <table>
                        <tr>
                            <td>
                                <form:label path="title"><spring:message code="announcement.title" /></form:label>
                            </td>
                            <td>
                                <form:input path="title" value="${announcements.title}"/>
                            </td>
                            <td>
                                <form:errors path="title" element="div"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="username"><spring:message code="announcement.author" /></form:label>
                            </td>
                            <td>
                                <form:label path="username">${announcements.username}</form:label>
                            </td>
                            <td>
                                <form:errors path="title" element="div"/>
                            </td>
                        </tr>
                        <tr>
                            <td><form:label path="header"><spring:message code="announcement.header" /></form:label></td>
                                <td>
                                <form:select path="header">
                                    <form:options items="${headers}" itemValue="value" itemLabel="value" />
                                </form:select>
                            </td>
                            <td>
                                <form:errors path="title" element="div"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="publicationDate"><spring:message code="announcement.date" /></form:label>
                            </td>
                            <td>
                                <form:label path="publicationDate">${announcements.publicationDateTime}</form:label>
                            </td>
                            <td>
                                <form:errors path="title" element="div"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="content"><spring:message code="announcement.content" /></form:label>
                            </td>
                            <td>
                                <form:textarea path="content" value="${announcements.content}"/>
                            </td>
                            <td>
                                <form:errors path="title" element="div"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <c:if test="${pageContext.request.userPrincipal.name == announcements.username}">
                    <table>
                        <tr>
                            <td><input type="submit" value=<spring:message code="announcement.submit" /> /></td>
                            <td><a href="<c:url value='/customer/announcement/search/delete' />"><spring:message code="announcement.delete" /></a></td>
                    
                </c:if>
            </form:form>
    </body>
</html>
