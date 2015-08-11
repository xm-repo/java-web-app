<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>History page</title>
</head>

<body>
    
    <p class="navigation">
		Navigation: <a href="../main/main.do">Main</a> / <a href="../legalentities/legalentities.do">Legal entities</a> / <span>History</span>
	</p>
	
	<h1>History</h1>
    <p></p>

    <p></p>
    <c:choose>
        <c:when test="${empty histories}">
        There are no history in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Business name</th>
			        <th>Old business name</th>
			        <th>Date</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${histories}" var="history">			    
			    <c:choose>
			    <c:when test="${flag == 0}">
			        <tr>  
			        <c:set var="flag" value = "1"/> 
			    </c:when>
			    <c:otherwise>
			        <tr class = "alt">
			        <c:set var="flag" value = "0"/> 
			    </c:otherwise>
			    </c:choose>
			        <td><c:out value="${history.id}"/></td>
			        <td><c:out value="${history.legalEntity.businessName}"/></td>
			        <td><c:out value="${history.oldBusinessName}"/></td>
			        <td><c:out value="${history.changeDate}"/></td>
			        
			        <c:choose>
			        <c:when test="${not empty legalEntity}">
			            <td><a href="remove.do?id=${legalEntity.id}&rmid=${history.id}">Delete</a></td>
			        </c:when>
			        <c:otherwise>
			            <td><a href="remove.do?rmid=${history.id}">Delete</a></td>
			        </c:otherwise>
			        </c:choose>
			        		        
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <p></p> 
                    <c:choose>
			        <c:when test="${not empty legalEntity}">
			            <a href="../legalentities/legalentities.do?id=${legalEntity.id}">Back</a>
			        </c:when>
			        <c:otherwise>
			            <a href="../legalentities/legalentities.do">Back</a>
			        </c:otherwise>
			        </c:choose>
			        
    <p></p>
    <a href="../main/main.do">Main</a>
    

</body>	

</html>