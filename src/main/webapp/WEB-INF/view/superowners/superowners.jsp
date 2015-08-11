<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Owners page</title>
</head>

<style>

.link {
  fill: none;
  stroke: #666;
  stroke-width: 2px;
}

#licensing {
  fill: black;
}

.link.licensing {
  stroke: red;
}

.link.resolved {
  stroke-dasharray: 0,2 1;
}

circle {
  fill: #ccc;
  stroke: #333;
  stroke-width: 1.5px;
}

text {
  font: 10px sans-serif;
  pointer-events: none;
  text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, 0 -1px 0 #fff, -1px 0 0 #fff;
}


</style>

<body>
    
    <p class="navigation">
		Navigation: <a href="../main/main.do">Main</a> / <span>Owners</span>
	</p>
	
	<h1>Owners</h1>
    <p></p>
	
	<h3>Add legal entity property</h3>
    <p></p>
	<form:form commandName="owner" method="POST" action="superowners.do">
	    <form:hidden path="id"/>
	    <!-- <form:hidden path="physicalPerson"/> -->
	    Legal entity: <form:select path="legalEntity1.id">
	        <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity1.id"/> <br/>
	    Owner: <form:select path="legalEntity2.id" >
             <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label = "(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity2.id"/> <br/>
	    Property: <form:input path="property"/> <form:errors path="property" cssStyle="font-weight: normal; color: red;"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='superowners.do'"/>   
	</form:form>
	
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
			        <tr>  
			        <c:set var="flag" value = "1"/> 
			    </c:when>
			    <c:otherwise>
			        <tr class = "alt">
			        <c:set var="flag" value = "0"/> 
			    </c:otherwise>
			    </c:choose>
			        <td><c:out value="${lowner.id}"/></td>
			        <td><a href="../legalentities/legalentity.do?id=${lowner.legalEntity1.id}">
			            (<c:out value="${lowner.legalEntity1.id}"/>) <c:out value="${lowner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentity.do?id=${owner.legalEntity2.id}">
			            (<c:out value="${lowner.legalEntity2.id}"/>) <c:out value="${lowner.legalEntity2.businessName}"/></a></td>	
			        <td><c:out value="${lowner.property}"/></td> 
			        <td><a href="superowners.do?id=${lowner.id}">Edit</a></td>
			        <td><a href="remove.do?id=${lowner.id}">Delete</a></td>
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <h3>Add physical person property</h3>
    <p></p>
	<form:form commandName="owner" method="POST" action="superowners.do">
	    <form:hidden path="id"/>
	    <!-- <form:hidden path="legalEntity2"/> -->
	    Legal entity: <form:select path="legalEntity1.id">
	        <c:forEach items="${legalentities}" var="legalentity">
	            <form:option value="${legalentity.id}" label="(${legalentity.id}) ${legalentity.businessName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity1.id"/> <br/>
	    Owner: <form:select path="physicalPerson.id" >
             <c:forEach items="${physicalpersons}" var="physicalperson">
	            <form:option value="${physicalperson.id}" label="(${physicalperson.id}) ${physicalperson.fullName}"/>
	        </c:forEach>
	    </form:select> <form:errors path="legalEntity2.id"/> <br/>
	    Property: <form:input path="property"/> <form:errors path="property" cssStyle="font-weight: normal; color: red;"/> <br/>
	    <input type="submit" value="Save"/> 
	    <input type="button" value="Cancel" onClick="location.href='superowners.do'"/>   
	</form:form>
    
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
			        <tr>  
			        <c:set var="flag" value = "1"/> 
			    </c:when>
			    <c:otherwise>
			        <tr class = "alt">
			        <c:set var="flag" value = "0"/> 
			    </c:otherwise>
			    </c:choose>
			        <td><c:out value="${powner.id}"/></td>
			        <td><a href="../legalentities/legalentity.do?id=${powner.legalEntity1.id}">
			            (<c:out value="${powner.legalEntity1.id}"/>) <c:out value="${powner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../physicalpersons/physicalperson.do?id=${powner.physicalPerson.id}">
			            (<c:out value="${powner.physicalPerson.id}"/>) <c:out value="${powner.physicalPerson.fullName}"/></a></td>		
			        <td><c:out value="${powner.property}"/></td>
			        <td><a href="superowners.do?id=${powner.id}">Edit</a></td>
			        <td><a href="remove.do?id=${powner.id}">Delete</a></td> 		 
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
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
	    <input type="button" value="Cancel" onClick="location.href='superowners.do'" />   
	</form:form>
    
    <h1>Business</h1>
    <p></p>
    <c:choose>
        <c:when test="${empty businesses}">
        There are no PP owners in data base
        </c:when>
        <c:otherwise>
            <table>	
                <tr>
			        <th>Id</th>
			        <th>Subj legal entity</th>
			        <th>Obj legal entity</th>
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
			        <td><a href="../legalentities/legalentity.do?id=${business.legalEntity2.id}">
			            (<c:out value="${business.legalEntity2.id}"/>) <c:out value="${business.legalEntity2.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentity.do?id=${business.legalEntity1.id}">
			            (<c:out value="${business.legalEntity1.id}"/>) <c:out value="${business.legalEntity1.businessName}"/></a></td>		
			        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${business.purchaseDate}"/></td>
			        <td><a href="owners.do?bid=${business.id}">Edit</a></td> 
			        <td><a href="remove.do?bid=${business.id}">Delete</a></td>		 
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    
    <hr/>
    <div style="background-color:orange; text-align:left">
        <h1>Structure</h1>
    </div>
    <p></p>
    <script type="text/javascript" src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
    <script type="text/javascript">
               
              var links = [];
               
              <c:forEach items="${leowners}" var="owner">
                   links.push({source: "<c:out value="${owner.legalEntity2.businessName}"/>", target: "<c:out value="${owner.legalEntity1.businessName}"/>", 
                	   prop: "<c:out value="${owner.property}"/>", type: "suit", group: "13"});
              </c:forEach>
              
              <c:forEach items="${ppowners}" var="owner">
                  links.push({source: "<c:out value="${owner.physicalPerson.fullName}"/>", target: "<c:out value="${owner.legalEntity1.businessName}"/>", 
                	  prop: "<c:out value="${owner.property}"/>", type: "resolved", group: "9"});
              </c:forEach>
              
              <c:forEach items="${businesses}" var="business">
                  links.push({source: "<c:out value="${business.legalEntity2.businessName}"/>", target: "<c:out value="${business.legalEntity1.businessName}"/>", 
                	  type: "licensing", group: "13"});
              </c:forEach>
                             
              var nodes = {};

              links.forEach(function(link) {
                  link.source = nodes[link.source] || (nodes[link.source] = {name: link.source, group: link.group});
                  link.target = nodes[link.target] || (nodes[link.target] = {name: link.target, group: link.group});
              });

              var width = 1000,
              height = 1000;

              var force = d3.layout.force()
                  .nodes(d3.values(nodes))
                  .links(links)
                  .size([width, height])
                  .linkDistance(150)
                  .charge(-500)
                  .on("tick", tick)
                  .start();

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

// Per-type markers, as they don't inherit styles.
svg.append("defs").selectAll("marker")
    .data(["suit", "licensing", "resolved"])
  .enter().append("marker")
    .attr("id", function(d) { return d; })
    .attr("viewBox", "0 -5 10 10")
    .attr("refX", 15)
    .attr("refY", -1.5)
    .attr("markerWidth", 6)
    .attr("markerHeight", 6)
    .attr("orient", "auto")
  .append("path")
    .attr("d", "M0,-5L10,0L0,5");
    
     

var path = svg.append("g").selectAll("path")
    .data(force.links())
  .enter().append("path")
    .attr("class", function(d) { return "link " + d.type; })
    .attr("marker-end", function(d) { return "url(#" + d.type + ")"; });

var color = d3.scale.category20();
var circle = svg.append("g").selectAll("circle")
    .data(force.nodes())
    .enter().append("circle")
    .attr("r", function(d) { return d.group; })
    //.attr("r", 10)
    .style("fill", function(d) { return color(d.group); })
    .call(force.drag);

var text = svg.append("g").selectAll("text")
    .data(force.nodes())
  .enter().append("text")
    .attr("x", 8)
    .attr("y", ".31em")
    .text(function(d) { return d.name; });
    
var labels = svg.append("g").selectAll('text')
.data(force.links())
.enter().append('text')
.attr("x", function(d) { return (d.source.y + d.target.y) / 2; }) 
.attr("y", function(d) { return (d.source.x + d.target.x) / 2; }) 
.attr("text-anchor", "middle") 
.text(function(d) {return d.prop;}); 

// Use elliptical arc path segments to doubly-encode directionality.
function tick() {
  path.attr("d", linkArc);
  circle.attr("transform", transform);
  text.attr("transform", transform);
  
  labels.attr("x", function(d) { return (d.source.x + d.target.x) / 2; }) 
  .attr("y", function(d) { return (d.source.y + d.target.y) / 2; })
  
}

function linkArc(d) {
  var dx = d.target.x - d.source.x,
      dy = d.target.y - d.source.y,
      dr = Math.sqrt(dx * dx + dy * dy);
  return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr + " 0 0,1 " + d.target.x + "," + d.target.y;
}

function transform(d) {
  return "translate(" + d.x + "," + d.y + ")";
}
    </script>
    <hr/>
    
    
   <p></p> 
   <a href="../main/main.do">Back</a>
    

</body>	

</html>