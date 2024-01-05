<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | Role Form</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="../css/main.css" type="text/css"> 

</head>

<body>
<div class="container" align="center">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item ">
                <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="userForm">User Form </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="roleForm">Role Form <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="branchForm">Branch Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="customerForm">Customer Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="accountForm">Account Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="transactionForm">Transaction Form</a>
            </li>
        </ul>
    </div>
</nav>

<h1>Role Form</h1>
<f:form action="saveRole" modelAttribute="role">
<table class="table table-striped table-bordered">
	<tr>
		<td>Role Id: </td><td> <f:input path="roleId" value="${r.roleId}"/></td>
		<td> <f:errors path="roleId" cssClass="error"/></td>
	
	<tr>
		<td>Role Name</td>
		<td><f:input path="roleName"/></td>
		<td><f:errors path="roleName" cssClass="error"/></td>
	</tr>
	<tr>
		<td colspan="3" align="center"><input type="submit" value="submit" ></td>
	</tr>
</table>
</f:form>

<c:if test="${roles.size() > 0 }">
<table>
	<tr>
		<th>Role Id</th><th>Name</th><th>Users</th><th>Action</th>
	</tr>
	<c:forEach items="${roles }" var="r">
		<tr>
			<td>${r.getRoleId()}</td>
			<td>${r.getRoleName()}</td>
			<td>
			<c:forEach items="${r.getUsers() }" var="userRole">
				${userRole.getUsername()}
			</c:forEach>
			</td>
			<td>
			<a href="updateRole?roleId=${r.getRoleId()}">Update</a>
			|
			<a href="deleteRole?roleId=${r.getRoleId()}">Delete</a>
		</td>
		</tr>
		
	</c:forEach>
</table>
</c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	

</body>


</html>
