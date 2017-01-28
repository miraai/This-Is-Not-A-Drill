<%-- 
    Document   : addGame
    Created on : Jan 28, 2017, 3:46:01 PM
    Author     : Mirai
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>
<h1>Add Game</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajGame" value="/addGame" ></c:url>
        <form:form method="POST" action="${dodajGame}" modelAttribute="game">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="name">
                    Game Name
                </form:label>
                <form:input type="name" class="form-control" id="name" placeholder="Game name" path="name" />
                <form:label path="price">
                    Game Price
                </form:label>
                <form:input type="price" class="form-control" id="price" placeholder="Game price" path="price" />
            </div>
            <div class="form-group">
                <form:label for="genreId" path="genreId">Game Genre</form:label>

                <form:select id="slcRole" class="form-control" path="genreId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${genres}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty games}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Game Name</th>
                        <th>Game Price</th>
                        <th>Game Genre</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${games}" var="gems">

                        <tr>
                            <td>${gems.name}</td>
                            <td>${gems.price}</td>
                            <td>${gems.genreId.name}</td>
                            <td><a href="<c:url value='/editGame/${gems.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteGame/${gems.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>
