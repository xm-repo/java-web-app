<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<title>Tansitive owners page</title>
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
	
	<h1>Property</h1>
    <p></p>
    
    <c:choose>
    <c:when test="${not empty legalEntity}">
	    <h3>Legal entity</h3>
	    <table>	
        <tr>
		     <th>Id</th>
			 <th>Business name</th>
			 <th>Foundation date</th>
			 <th>Is legal entity</th>
	    </tr>
	    <tr>			    
			<td><c:out value="${legalEntity.id}"/></td>
			<td><a href="../legalentities/legalentities.do?id=${legalEntity.id}"><c:out value="${legalEntity.businessName}"/></a></td>
			<c:set var="d" value = "${legalEntity.foundationDate}"/>
		    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${legalEntity.foundationDate}"/></td> 
			<td><c:out value="${legalEntity.isLe}"/></td>
        </tr>
	    </table>
	</c:when>
	</c:choose>
		      
	<c:choose>
	<c:when test="${not empty physicalPerson}">
	    <h3>Physical person</h3>
	    <table>	
        <tr>
		    <th>Id</th>
			<th>Full name</th>
			<th>Contacts</th>
	    </tr>
	    <tr>			    
			<td><c:out value="${physicalPerson.id}"/></td>
			<td><a href="../physicalpersons/physicalpersons.do?id=${physicalPerson.id}"><c:out value="${physicalPerson.fullName}"/></a></td>
			<td><c:out value="${physicalPerson.contacts}"/></td>
        </tr>
	    </table>
    </c:when>
    </c:choose>
    
    <H1></H1>
    
    <c:set var="ff" value = "true"/>
    <c:forEach items="${owners}" var="lowner">
    <c:choose>
        <c:when test="${empty lowner.physicalPerson}">
            <c:set var="ff" value = "false"/>
        </c:when>
    </c:choose>
    </c:forEach>
	
    <c:choose>
        <c:when test="${(empty owners) or ff}">
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
		        <c:forEach items="${owners}" var="lowner">
		        
		        
		        <c:choose>
		        <c:when test="${empty lowner.physicalPerson}">
			    
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
			        <td><a href="../legalentities/legalentities.do?id=${lowner.legalEntity1.id}">
			            (<c:out value="${lowner.legalEntity1.id}"/>) <c:out value="${lowner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentities.do?id=${lowner.legalEntity2.id}">
			            (<c:out value="${lowner.legalEntity2.id}"/>) <c:out value="${lowner.legalEntity2.businessName}"/></a></td>	
			        <td><c:out value="${lowner.property}"/></td> 
			    </tr>
			    
			    </c:when>
			    </c:choose>
			    
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <c:set var="ff" value = "true"/>
    <c:forEach items="${owners}" var="lowner">
    <c:choose>
        <c:when test="${empty lowner.legalEntity2}">
            <c:set var="ff" value = "false"/>
        </c:when>
    </c:choose>
    </c:forEach>
    
    <p></p>
    <c:choose>
        <c:when test="${(empty owners) or ff}">
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
		        <c:forEach items="${owners}" var="powner">
			    
			    <c:choose>
			    <c:when test="${empty powner.legalEntity2}">
			    
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
			        <td><a href="../legalentities/legalentities.do?id=${powner.legalEntity1.id}">
			            (<c:out value="${powner.legalEntity1.id}"/>) <c:out value="${powner.legalEntity1.businessName}"/></a></td>
			        <td><a href="../physicalpersons/physicalpersons.do?id=${powner.physicalPerson.id}">
			            (<c:out value="${powner.physicalPerson.id}"/>) <c:out value="${powner.physicalPerson.fullName}"/></a></td>		
			        <td><c:out value="${powner.property}"/></td>		 
			    </tr>
			    
			    </c:when>
			    </c:choose>
			    
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose>
    
    <!--  <h1>Business</h1>
    <p></p>
    <c:choose>
        <c:when test="${empty businesses}">
        There are no business in data base
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
			        <td><a href="../legalentities/legalentities.do?id=${business.legalEntity2.id}">
			            (<c:out value="${business.legalEntity2.id}"/>) <c:out value="${business.legalEntity2.businessName}"/></a></td>
			        <td><a href="../legalentities/legalentities.do?id=${business.legalEntity1.id}">
			            (<c:out value="${business.legalEntity1.id}"/>) <c:out value="${business.legalEntity1.businessName}"/></a></td>		
			        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${business.purchaseDate}"/></td>	 
			    </tr>
		        </c:forEach>
	        </table>
        </c:otherwise>
    </c:choose> -->
    
    <hr/>
    <div style="background-color:orange; text-align:left">
        <h1>Structure</h1>
    </div>
    <p></p>
    <script type="text/javascript" src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
    <script type="text/javascript">
               
              var links = [];
              var nodes = {};
              
              var color = d3.scale.category20();
              
              <c:choose>
		      <c:when test="${not empty legalEntity}">
		          nodes[<c:out value="${legalEntity.id}"/>] = {name: "<c:out value="${legalEntity.businessName}"/>", size: "13", color: "red"};
		      </c:when>
		      </c:choose>
		      
		      <c:choose>
		      <c:when test="${not empty physicalPerson}">
		          nodes["p" + <c:out value="${physicalPerson.id}"/>] = {name: "<c:out value="${physicalPerson.fullName}"/>", size: "9", color: "red"};
		      </c:when>
		      </c:choose>
               
              <c:forEach items="${owners}" var="owner">
                  <c:choose> 
                  <c:when test="${empty owner.physicalPerson}">
                  
                      nodes[<c:out value="${owner.legalEntity2.id}"/>] = nodes[<c:out value="${owner.legalEntity2.id}"/>]
                          || {name: "<c:out value="${owner.legalEntity2.businessName}"/>", size: "13", color: color("13")}; 
                      nodes[<c:out value="${owner.legalEntity1.id}"/>] = nodes[<c:out value="${owner.legalEntity1.id}"/>]
                          || {name: "<c:out value="${owner.legalEntity1.businessName}"/>", size: "13", color: color("13")};
                  
                      <c:choose>
                      <c:when test="${owner.property == 100}"> 
                  
                      links.push({source: nodes[<c:out value="${owner.legalEntity2.id}"/>], target: nodes[<c:out value="${owner.legalEntity1.id}"/>], type: "licensing"});    
                      
                      </c:when>
                      <c:otherwise>
                      
                      links.push({source: nodes[<c:out value="${owner.legalEntity2.id}"/>], target: nodes[<c:out value="${owner.legalEntity1.id}"/>], 
                    	  value: "<c:out value="${owner.property}"/>", type: "suit"});  
                  
                      </c:otherwise>
                      </c:choose>
                                                                    
                  </c:when>                                  
                  </c:choose> 
              </c:forEach>
              
              <c:forEach items="${owners}" var="owner">                            
                  <c:choose> 
                  <c:when test="${empty owner.legalEntity2}"> 
                  
                  nodes["p" + <c:out value="${owner.physicalPerson.id}"/>] = nodes["p" + <c:out value="${owner.physicalPerson.id}"/>]
                      || {name: "<c:out value="${owner.physicalPerson.fullName}"/>", size: "9", color: color("9")}; 
                  nodes[<c:out value="${owner.legalEntity1.id}"/>] = nodes[<c:out value="${owner.legalEntity1.id}"/>]
                      || {name: "<c:out value="${owner.legalEntity1.businessName}"/>", size: "13", color: color("13")};
                      
                  links.push({source: nodes["p" + <c:out value="${owner.physicalPerson.id}"/>], target: nodes[<c:out value="${owner.legalEntity1.id}"/>], 
                      value: "<c:out value="${owner.property}"/>", type: "resolved"});  
              
                  </c:when>                              
                  </c:choose> 
              </c:forEach>

              var width = 1000,
              height = 1000;

              var force = d3.layout.force()
                  .nodes(d3.values(nodes))
                  .links(links)
                  .size([width, height])
                  .linkDistance(150)
                  .charge(-500)
                  //.gravity(.08)
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

var circle = svg.append("g").selectAll("circle")
    .data(force.nodes())
    .enter().append("circle")
    .attr("r", function(d) { return d.size; })
    //.attr("r", 10)
    .style("fill", function(d) 
    { 
    	return d.color;    	         
    })
    //.on("click", click)
    //.on("dblclick", dblclick)
    .call(force.drag);
    
    function click(d) {
	    if(d3.event.defaultPrevented) { return; };
	    d3.select(this).style("fill", "red");
	   return;
    }
    function dblclick(d) {
	    if(d3.event.defaultPrevented) { return; };
	    d3.select(this).style("fill", function(d) {return d.color; });
	   return;
    }

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
.text(function(d) {return d.value;}); 

              
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
                    <c:choose>
			        <c:when test="${not empty legalEntity}">
			            <a href="../legalentities/legalentities.do?id=${legalEntity.id}">Back</a>
			        </c:when>
			        <c:otherwise>
			             <c:choose>
			            <c:when test="${not empty physicalPerson}">
			                <a href="../physicalpersons/physicalpersons.do?id=${physicalPerson.id}">Back</a>
			            </c:when>
			            <c:otherwise>
			                <a href="../main/main.do">Back</a>
			            </c:otherwise>
			            </c:choose>
			        </c:otherwise>
			        </c:choose>
			        
    <p></p>
    <a href="../main/main.do">Main</a>
    

</body>	

</html>