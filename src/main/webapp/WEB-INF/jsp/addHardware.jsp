<%-- 
    Document   : addHardware
    Created on : Jan 28, 2017, 6:04:56 PM
    Author     : Mirai
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>
<h1>Add Hardware</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajHardware" value="/addHardware" ></c:url>
        <form:form method="POST" action="${dodajHardware}" modelAttribute="hardware">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="name">
                    Hardware Name
                </form:label>
                <form:input type="name" class="form-control" id="name" placeholder="Hardware name" path="name" />
                
                <form:label path="price">
                    Hardware Price
                </form:label>
                <form:input type="price" class="form-control" id="price" placeholder="Hardware Price" path="price" />
            </div>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty hardwares}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Hardware Name</th>
                        <th>Hardware Price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${hardwares}" var="hard">

                        <tr>
                            <td>${hard.name}</td>
                            <td>${hard.price}</td>
                            <td><a href="<c:url value='/editHardware/${hard.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteHardware/${hard.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>
