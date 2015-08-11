<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Physical persons page</title>
</head>

<body>
    
    <p class="navigation">
		Navigation: <a href="../main/main.do">Main</a> / <span>Physical persons</span>
	</p>
	
	<h1>Physical persons</h1>
    <p></p>
	
	<h3>Add physical person</h3>
    <p></p>
	<form:form commandName="physicalPerson" method="POST" action="physicalpersons.do">
	    <form:hidden path="id"/>
	    Full name: <form:input path="fullName"/> <form:errors path="fullName" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Contacts: <form:input path="contacts"/> <form:errors path="contacts" cssStyle="font-weight: normal; color: red;"/> <br/>
	    Biography: <form:textarea path="biography" rows="5" cols="30"/> <br/> <form:errors path="biography" cssStyle="font-weight: normal; color: red;"/> <br/> 
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='physicalpersons.do'" />   
	</form:form>

    <c:set var = "p" value = "${physicalPerson}"/>
    
    <p></p>
    <c:choose>
        <c:when test="${empty physicalPersons}">
        There are no physical persons in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Full name</th>
			        <th>Contacts</th>
			    </tr>
		        <c:set var="flag" value = "0"/>
		        <c:forEach items="${physicalPersons}" var="physicalPerson">		    
			    
			    <c:choose>
			    <c:when test="${flag == 0}">
			        <c:choose>
			        <c:when test="${p.id == physicalPerson.id}">
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
			        <c:when test="${p.id == physicalPerson.id}">
			            <tr style="background-color: red"/>
			        </c:when>
			        <c:otherwise>
			            <tr class = "alt">			             
			        </c:otherwise>
			        </c:choose>
			        <c:set var="flag" value = "0"/>
			    </c:otherwise>
			    </c:choose>	
			    
			        <td><c:out value="${physicalPerson.id}"/></td>
			        <td><a href="physicalpersons.do?id=${physicalPerson.id}"><c:out value="${physicalPerson.fullName}"/></a></td>
			        <td><c:out value="${physicalPerson.contacts}"/></td>
			        <td><a href="../transitiveowners/transitiveowners.do?ppid=${physicalPerson.id}">Property</a></td>
			        <td><a href="physicalpersons.do?id=${physicalPerson.id}">Edit</a></td>
			        <td><a href="remove.do?id=${physicalPerson.id}">Delete</a></td>
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
   <p></p> 
   <a href="../main/main.do">Main</a>
    
    
</body>	

</html>