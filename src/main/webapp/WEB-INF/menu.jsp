<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="flex md:px-16 px-10 py-4">
    <c:choose>
        <c:when
                test="${authenticatedUser !=null && authenticatedUser.userRole eq 'ADMINISTRATOR'}">
            <%@ include file="adminmenu.jsp"%>
        </c:when>
        <c:when
                test="${authenticatedUser !=null && authenticatedUser.userRole eq 'EMPLOYEE'}">
            <%@ include file="adminmenuemployee.jsp"%>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
</div>