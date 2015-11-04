<%-- 
    Document   : search
    Created on : Oct 29, 2015, 12:40:55 PM
    Author     : sf
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="GET" modelAttribute="announcementSearchCriteria">
    <fieldset>
        <legend><spring:message code="announcement.searchcriteria"/></legend>
        <table>
            <tr>
                <td><form:label path="author"><spring:message code="announcement.author" /></form:label></td>
                <td>
                    <form:select path="author" >
                        <form:option value="all"><spring:message code="announcement.allAuthors"/></form:option>
                        <form:option value="my"><spring:message code="announcement.onlyMine"/></form:option>
                        <form:options items="${authors}" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="category"><spring:message code="announcement.category" /></form:label></td>
            <td>
                <form:select path="category">
                    <form:option value="all"><spring:message code="announcement.allCategories"/></form:option>
                    <form:options items="${categories}" itemValue="value" itemLabel="value" />
                </form:select>
            </td>
            </tr>
        </table>
    </fieldset>
    <button id="search"><spring:message code="button.search"/></button>
</form:form>

<c:if test="${not empty announcementsList}">
    <table>
        <tr>
            <th><spring:message code="announcement.title"/></th>
            <th><spring:message code="announcement.author"/></th>
            <th><spring:message code="announcement.date"/></th>
            <th><spring:message code="announcement.category" /></th>
            <th></th>
        </tr>
        <c:forEach items="${announcementsList}" var="announcement">
            <tr>
                <td>
                    <a href="<c:url value="/customer/announcement/detail/${announcement.id}"/>">${announcement.title}</a></td>
                <td>${announcement.username}</td>
                <td>${announcement.publicationDate}</td>
                <td>${announcement.header}</td> 
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="<c:url value="create" />">
        <spring:message code="announcement.create"></spring:message>
</a>