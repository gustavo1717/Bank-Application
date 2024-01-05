<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | User Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            <li class="nav-item active">
                <a class="nav-link" href="userForm">User Form <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="roleForm">Role Form</a>
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
	
	
<h1>User Form</h1>
<f:form action="saveUser" modelAttribute="user">
<table class="table table-striped table-bordered">

<div class="form-group">
<tr>
<td>User Id: </td><td> <f:input path="userId" value="${u.userId}"/></td> <td><f:errors path="userId"/></td>
</tr>
</div>
<div class="form-group">
<tr>
<td>Username: </td><td> <f:input path="username" value="${u.username}"/></td> <td><f:errors path="username"/></td>
</tr>
</div>
<div class="form-group">
<tr>
<td>Password: </td><td> <f:input path="password" type="password" value="${u.password}"/></td><td> <f:errors path="password"/></td>
</tr>
</div>
<div class="form-group">
<tr>
<td>Roles: </td>
<td>
<c:forEach items="${roles }" var="r">
	<f:checkbox path="roles" label="${r.getRoleName()}" value="${r.getRoleId() }" />
	
</c:forEach></td>
<td><f:errors path="roles"></f:errors></td>
</tr>
</div>
<tr>
<td colspan="3" align="center"> <input type="submit" value="submit"></td>
</tr>

</table>
</f:form>

<p/>
<p/>

<c:if test="${users.size() > 0 }">
<table border="1">
 <tr>
	<th>User Id</th><th>Name</th><th>Roles</th><th>Action</th>
</tr>
<c:forEach items="${users}" var="u">
	<tr>
		<td>${u.getUserId()}</td>
		<td>${u.getUsername()}</td>
		<td><c:forEach items="${u.getRoles() }" var="r">
			${r.getRoleName() }
		</c:forEach></td>
		<td>
			<a href="updateUser?userId=${u.getUserId()}">Update</a>
			|
			<a href="deleteUser?userId=${u.getUserId()}">Delete</a>
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