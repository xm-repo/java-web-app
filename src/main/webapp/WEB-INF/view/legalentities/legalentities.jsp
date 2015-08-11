<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Legal entities page</title>
</head>

<body>
    
    <p class="navigation">
		Navigation: <a href="../main/main.do">Main</a> / <span>Legal entities</span>
	</p>
	
	<h1>Legal entities</h1>
    <p></p>
	
	<h3>Add legal entity</h3>
    <p></p>
	<form:form commandName="legalEntity" method="POST" action="legalentities.do">
	    <form:hidden path="id"/>
	    Business name: <form:input path="businessName"/> <form:errors path="businessName" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Foundation date: <form:input path="foundationDate" placeholder="YYYY-MM-DD"/> <form:errors path="foundationDate" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Is legal entity: <form:checkbox path="isLe"/> <form:errors path="isLe" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Miscellaneous: <form:textarea path="miscellaneous" rows="5" cols="30"/> <form:errors path="miscellaneous" cssStyle="font-weight: normal; color: red;"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='legalentities.do'" />   
	</form:form>
	
	<c:set var = "l" value = "${legalEntity}"/>
    
    <p></p>
    <c:choose>
        <c:when test="${empty legalEntities}">
        There are no legal entities in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Business name</th>
			        <th>Foundation date</th>
			        <th>Is legal entity</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${legalEntities}" var="legalEntity">
			    
			    <c:choose>
			    <c:when test="${flag == 0}">
			        <c:choose>
			        <c:when test="${l.id == legalEntity.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr>  		         
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "1"/>
			    </c:when>
			    <c:otherwise>
			        <c:choose>
			        <c:when test="${l.id == legalEntity.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr class = "alt">			             
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "0"/>
			    </c:otherwise>
			    </c:choose>	
			    
			        <td><c:out value="${legalEntity.id}"/></td>
			        <td><a href="legalentities.do?id=${legalEntity.id}"><c:out value="${legalEntity.businessName}"/></a></td>
			        <c:set var="d" value = "${legalEntity.foundationDate}"/>
			        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${legalEntity.foundationDate}"/></td> 
			        <td><c:out value="${legalEntity.isLe}"/></td>
			        <td><a href="../transitiveowners/transitiveowners.do?leid=${legalEntity.id}">Property</a></td>
			        <td><a href="../business/business.do?id=${legalEntity.id}">Business</a></td>
			        <td><a href="../history/history.do?id=${legalEntity.id}">History</a></td>
			        <td><a href="legalentities.do?id=${legalEntity.id}">Edit</a></td>
			        <td><a href="remove.do?id=${legalEntity.id}">Delete</a></td>
			        
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
   <p></p> 
   <a href="../main/main.do">Main</a>
    

</body>	

</html>