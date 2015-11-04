<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <h1>${title}</h1>
        <h2>${message}<a href="<c:url value='/registration' />">${registrationRef}</a></h2>

    <sec:authorize access="hasRole('ROLE_USER')">
        <!-- For login user -->
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <h2><a href="<c:url value='/customer/announcement/search' />">${announcementsRef}</a> </h2>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                ${userTitle} : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> ${logoutRef}</a>
            </h2>
        </c:if>


    </sec:authorize>        
</body>
</html>