<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Property page</title>
</head>


<body>
    
    <p class="navigation">
		Navigation: <a href="../main/main.do">Main</a> / <span>Property</span>
	</p>
	
	<h1>Owners</h1>
    <p></p>
	
	<h3>Add legal entity property</h3>
    <p></p>
	<form:form commandName="owner" method="POST" action="owners.do">
	    <form:hidden path="id"/>
	    Legal entity: <form:select path="legalEntity1.id">
	        <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity1.id" cssStyle="font-weight: normal; color: red;"/> <br/>
	    LE owner: <form:select path="legalEntity2.id" >
            <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity2.id" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Property: <form:input path="property"/> <form:errors path="property" cssStyle="font-weight: normal; color: red;"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='owners.do'"/>   
	</form:form>
	
	<c:set var = "o" value = "${owner}"/>
	
    <p></p>
    <c:choose>
        <c:when test="${empty leowners}">
        There are no LE owners in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Legal entity</th>
			        <th>Owner</th>
			        <th>Property</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${leowners}" var="lowner">
			    
			    <c:choose>
			    <c:when test="${flag == 0}">
			        <c:choose>
			        <c:when test="${o.id == lowner.id}">
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
			        <c:when test="${o.id == lowner.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr class = "alt">			             
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "0"/>
			    </c:otherwise>
			    </c:choose>	
			    
			        <td><c:out value="${lowner.id}"/></td>
			        <td><a href="../legalentities/legalentities.do?id=${lowner.legalEntity1.id}">
			            (<c:out value="${lowner.legalEntity1.id}"/>) <c:out value="${lowner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentities.do?id=${lowner.legalEntity2.id}">
			            (<c:out value="${lowner.legalEntity2.id}"/>) <c:out value="${lowner.legalEntity2.businessName}"/></a></td>	
			        <td><c:out value="${lowner.property}"/></td> 
			        <td><a href="owners.do?id=${lowner.id}">Edit</a></td>
			        <td><a href="remove.do?id=${lowner.id}">Delete</a></td>
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <h3>Add physical person property</h3>
    <p></p>
	<form:form commandName="owner" method="POST" action="owners.do">
	    <form:hidden path="id"/>
	    Legal entity: <form:select path="legalEntity1.id">
	        <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label="(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity1.id" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Owner: <form:select path="physicalPerson.id" >
             <c:forEach items="${physicalpersons}" var="physicalperson">
	            <form:option value="${physicalperson.id}" label="(${physicalperson.id}) ${physicalperson.fullName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="physicalPerson.id" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Property: <form:input path="property"/> <form:errors path="property" cssStyle="font-weight: normal; color: red;"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='owners.do'"/>   
	</form:form>
	
	<c:set var = "o" value = "${owner}"/>
    
    <p></p>
    <c:choose>
        <c:when test="${empty ppowners}">
        There are no PP owners in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Legal entity</th>
			        <th>Owner</th>
			        <th>Property</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${ppowners}" var="powner">
			    
			    <c:choose>
			    <c:when test="${flag == 0}">
			        <c:choose>
			        <c:when test="${o.id == powner.id}">
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
			        <c:when test="${o.id == powner.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr class = "alt">			             
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "0"/>
			    </c:otherwise>
			    </c:choose>	
			    
			        <td><c:out value="${powner.id}"/></td>
			        <td><a href="../legalentities/legalentities.do?id=${powner.legalEntity1.id}">
			            (<c:out value="${powner.legalEntity1.id}"/>) <c:out value="${powner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../physicalpersons/physicalpersons.do?id=${powner.physicalPerson.id}">
			            (<c:out value="${powner.physicalPerson.id}"/>) <c:out value="${powner.physicalPerson.fullName}"/></a></td>		
			        <td><c:out value="${powner.property}"/></td>
			        <td><a href="owners.do?id=${powner.id}">Edit</a></td>
			        <td><a href="remove.do?id=${powner.id}">Delete</a></td> 		 
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <p></p> 
    <a href="../main/main.do">Main</a>
    
    
</body>	

</html>