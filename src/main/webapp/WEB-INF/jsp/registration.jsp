<%-- 
    Document   : registration
    Created on : Oct 21, 2015, 12:07:47 PM
    Author     : sf
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title><spring:message code="label.registration.title"></spring:message></title>
    </head>
    <body>
        <H1>
            <spring:message code="label.registration.title"></spring:message>
        </H1>
    <form:form modelAttribute="user" action="user/registration" method="POST" enctype="utf8">
        <br>
        <table border="0" border-collapse="collapse" style="width:50%">
            <tr>
                <td>
                    <label><spring:message code="label.user.userName"></spring:message></label>
                </td>
                <td>
                    <form:input path="username" value="" />
                </td>
                <td>
                    <form:errors path="username" element="div"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label><spring:message code="label.user.password"></spring:message></label>
                </td>
                <td>
                    <form:input path="password" value="" type="password" />
                </td>
                <td>
                    <form:errors path="password" element="div" />
                </td>
            </tr>
            <tr>
                <td>
                    <label><spring:message code="label.user.confirmPass"></spring:message></label>
                </td>
                <td>
                    <form:input path="matchingPassword" value="" type="password" />
                </td>
                <td>    
                    <form:errors element="div" />
                </td>
            </tr>
        </table>
        
        <button type="submit">
            <spring:message code="label.registration.submit"></spring:message>
        </button>
    </form:form>
    <br>
    <a href="<c:url value="login" />">
        <spring:message code="label.registration.loginLink"></spring:message>
    </a>
</body>
</html>
