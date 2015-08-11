<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Property structure main page</title>
</head>

<body>
	
    <form:form commandName="findSmth" method="POST" action="main.do">
	    Physical person: <form:input path="physicalPerson"/> <form:errors path="physicalPerson" cssStyle="font-weight: normal; color: red;"/>
	    <input type="submit" value="Find"/>   
	</form:form>
    
    <h3><a href="../physicalpersons/physicalpersons.do">Physical persons</a></h3>
    
    <form:form commandName="findSmth" method="POST" action="main.do">
	    Legal entity: <form:input path="legalEntity"/> <form:errors path="legalEntity" cssStyle="font-weight: normal; color: red;"/>
	    <input type="submit" value="Find"/>   
	</form:form>
    
    <h3><a href="../legalentities/legalentities.do">Legal entities</a></h3>
    
    <h3><a href="../owners/owners.do">Property</a></h3>
    
    <!--  <h3><a href="../history/history.do">History</a></h3> -->
    
    <h3><a href="../transitiveowners/transitiveowners.do">Transitive property</a></h3>

    <h3><a href="../superowners/superowners.do">TEMP</a></h3>
    
    <h3><a href="remove.do">clear db</a></h3>


</body>	

</html>