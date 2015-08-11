<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://oldworld.genericdao.googlecode.com/tags" prefix="m" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Business page</title>
</head>

<body>
    
    <p class="navigation">
        Navigation: <a href="../main/main.do">Main</a> / <a href="../legalentities/legalentities.do">Legal entities</a> / <span>Business</span>
	</p>
	
	<h1>Business</h1>
	
	<c:set var = "b" value = "${business}"/>
    
    <p></p>
    <c:choose>
        <c:when test="${empty businesses}">
        There are no business in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Subject legal entity</th>
			        <th>Object legal entity</th>
			        <th>Purchase date</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${businesses}" var="business">
			    
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
			    
			        <td><c:out value="${business.id}"/></td>
			        <td><a href="../legalentities/legalentities.do?id=${business.legalEntity2.id}">
			            (<c:out value="${business.legalEntity2.id}"/>) <c:out value="${business.legalEntity2.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentities.do?id=${business.legalEntity1.id}">
			            (<c:out value="${business.legalEntity1.id}"/>) <c:out value="${business.legalEntity1.businessName}"/></a></td>		
			        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${business.purchaseDate}"/></td>
			        <!--  <td><a href="business.do?id=${business.id}">Edit</a></td>  --> 
			        
			        <c:choose>
			        <c:when test="${not empty legalEntity}">
			            <td><a href="remove.do?id=${legalEntity.id}&rmid=${business.id}">Delete</a></td>
			        </c:when>
			        <c:otherwise>
			            <td><a href="remove.do?rmid=${business.id}">Delete</a></td>
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