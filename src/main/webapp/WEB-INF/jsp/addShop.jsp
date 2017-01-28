<%-- 
    Document   : addShop
    Created on : Jan 28, 2017, 5:20:23 PM
    Author     : Mirai
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>
<h1>Add Genre</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajShop" value="/addShop" ></c:url>
        <form:form method="POST" action="${dodajShop}" modelAttribute="shop">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="name">
                    Shop Name
                </form:label>
                <form:input type="name" class="form-control" id="name" placeholder="Genre name" path="name" />
                
                <form:label path="address">
                    Shop Address
                </form:label>
                <form:input type="address" class="form-control" id="address" placeholder="Genre Address" path="address" />
            </div>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty shops}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Shop Name</th>
                        <th>Shop Address</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${shops}" var="sho">

                        <tr>
                            <td>${sho.name}</td>
                            <td>${sho.address}</td>
                            <td><a href="<c:url value='/editShop/${sho.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteShop/${sho.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>
