<%-- 
    Document   : addMerchandise
    Created on : Jan 28, 2017, 7:34:02 PM
    Author     : Mirai
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>
<h1>Merchandise</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajMerchandise" value="/addMerchandise" ></c:url>
        <form:form method="POST" action="${dodajMerchandise}" modelAttribute="merchandise">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label for="shopId" path="shopId">Game Shop</form:label>

                <form:select id="slcRole" class="form-control" path="shopId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${shops}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <div class="form-group">
                <form:label for="gameId" path="gameId">Game Name</form:label>

                <form:select id="slcRole" class="form-control" path="gameId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${games}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <div class="form-group">
                <form:label for="hardwareId" path="hardwareId">Game Hardware</form:label>

                <form:select id="slcRole" class="form-control" path="hardwareId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${hardwares}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty merchandises}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Game Shop</th>
                        <th>Game Name</th>
                        <th>Game Hardware</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${merchandises}" var="merch">

                        <tr>
                            <td>${merch.shopId.name}</td>
                            <td>${merch.gameId.name}</td>
                            <td>${merch.hardwareId.name}</td>
                            <td><a href="<c:url value='/editMerchandise/${merch.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteMerchandise/${merch.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>
