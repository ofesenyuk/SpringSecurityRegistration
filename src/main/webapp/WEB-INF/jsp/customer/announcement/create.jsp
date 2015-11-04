<%-- 
    Document   : create
    Created on : Nov 2, 2015, 4:08:26 PM
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
        <%@ page session="false"%>
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
                    <td><form:label path="username"><spring:message code="announcement.author" /></form:label></td>
                    <td>
                        <form:label path="username">${pageContext.request.userPrincipal.name}</form:label>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><form:label path="header"><spring:message code="announcement.header" /></form:label></td>
                    <td>
                        <form:select path="header">
                            <form:options items="${categories}" itemValue="value" itemLabel="value" />
                            <form:option value="new"><spring:message code="announcement.newHeader"/></form:option>
                        </form:select>
                        
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><form:label path="publicationDate"><spring:message code="announcement.date" /></form:label></td>
                    <td>
                        <form:label path="publicationDate">${announcements.publicationDateTime}</form:label>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><form:label path="content"><spring:message code="announcement.content" /></form:label></td>
                    <td><form:textarea path="content" value="${announcements.content}"/></td>
                    <td><form:errors path="content" element="div"/></td>
                </tr>
                <tr>
                    <td><form:label path="newHeader"><spring:message code="announcement.new.header" /></form:label></td>
                    <td><form:input path="newHeader" value="${announcements.newHeader}"/></td>
                    <td><form:errors path="newHeader" element="div"/></td>
                </tr>
            </table>
        </fieldset>
        <input type="submit" value=<spring:message code="announcement.submit" /> />
    </form:form>
</body>
</html>