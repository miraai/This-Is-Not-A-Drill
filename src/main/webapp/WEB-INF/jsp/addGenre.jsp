<%-- 
    Document   : genre
    Created on : Jan 28, 2017, 2:03:22 AM
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
        <c:url var="dodajGenre" value="/addGenre" ></c:url>
        <form:form method="POST" action="${addGenre}" modelAttribute="genre">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="name">
                    Genre Name
                </form:label>
                <form:input type="name" class="form-control" id="name" placeholder="Genre name" path="name" />
            </div>

            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty genres}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Genre Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${genres}" var="gens">

                        <tr>
                            <td>${gens.name}</td>
                            <td><a href="<c:url value='/editGenre/${gens.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteGenre/${gens.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>