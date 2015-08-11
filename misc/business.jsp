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
		Navigation: <a href="../main/main.do">Main</a> / <span>Business</span>
	</p>
	
	<h1>Business</h1>
    
    <h3>Add business</h3>
    <p></p>
	<form:form commandName="business" method="POST" action="business.do">
	    <form:hidden path="id"/>
	    Object legal entity: <form:select path="legalEntity1.id" >
	        <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity1.id" /> <br/>
	    Subject legal entity: <form:select path="legalEntity2.id" >
             <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity2.id" /> <br/>
	    Purchase date: <form:input path="purchaseDate" placeholder="YYYY-MM-DD"/> <form:errors path="purchaseDate" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Miscellaneous: <form:textarea path="miscellaneous" rows="5" cols="30"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='business.do'" />   
	</form:form>
	
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
			        <c:choose>
			        <c:when test="${b.id == business.id}">
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
			        <c:when test="${b.id == business.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr class = "alt">			             
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "0"/>
			    </c:otherwise>
			    </c:choose>	
			    
			        <td><c:out value="${business.id}"/></td>
			        <td><a href="../legalentities/legalentity.do?id=${business.legalEntity2.id}">
			            (<c:out value="${business.legalEntity2.id}"/>) <c:out value="${business.legalEntity2.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentity.do?id=${business.legalEntity1.id}">
			            (<c:out value="${business.legalEntity1.id}"/>) <c:out value="${business.legalEntity1.businessName}"/></a></td>		
			        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${business.purchaseDate}"/></td>
			        <td><a href="business.do?id=${business.id}">Edit</a></td> 
			        <td><a href="remove.do?id=${business.id}">Delete</a></td>		 
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>   

</body>	

</html>