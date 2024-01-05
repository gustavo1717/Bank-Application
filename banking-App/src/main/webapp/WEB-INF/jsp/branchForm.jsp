<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | Branch Form</title>
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
            <li class="nav-item ">
                <a class="nav-link" href="roleForm">Role Form</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="branchForm">Branch Form <span class="sr-only">(current)</span></a>
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

<h1>Branch Form</h1>
<f:form action="saveBranch" modelAttribute="branch">
<table class="table table-striped table-bordered">
	<tr>
		<td>Branch Id: </td><td> <f:input type="text" path="branchId" value="${b.branchId}"/></td>
		<td> <f:errors path="branchId" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Branch Name</td>
		<td><f:input type="text" path="branchName"/></td>
		<td><f:errors path="branchName" cssClass="error"/></td>
	</tr>
	</table>
		<h3>Branch Address</h3>
	<table class="table table-striped table-bordered">
	
	<tr>
		<td>Branch Address Line 1</td>
		<td><f:input type="text" path="branchAddress.addressLine1"/></td>
		<td><f:errors path="branchAddress.addressLine1" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Branch Address Line 2</td>
		<td><f:input type="text" path="branchAddress.addressLine2"/></td>
		<td><f:errors path="branchAddress.addressLine2" cssClass="error"/></td>
	</tr>
	<tr>
		<td>City</td>
		<td><f:input type="text" path="branchAddress.city"/></td>
		<td><f:errors path="branchAddress.city" cssClass="error"/></td>
	</tr>
	<tr>
		<td>State</td>
		<td><f:input type="text" path="branchAddress.state"/></td>
		<td><f:errors path="branchAddress.state" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Country</td>
		<td><f:input type="text"  path="branchAddress.country"/></td>
		<td><f:errors path="branchAddress.country" cssClass="error"/></td>
	</tr>
	<tr>
		<td>ZipCode</td>
		<td><f:input type="text" path="branchAddress.zipcode"/></td>
		<td><f:errors path="branchAddress.zipcode" cssClass="error"/></td>
	</tr>

	<tr>
		<td colspan='3' align="center"><input type="submit" value="submit" ></td>
	</tr>
</table>
</f:form>
<c:if test="${branches.size() > 0 }">
<table border="1">
	<tr>
		<th>Branch Id</th><th>Name</th><th>Address</th><th>Branch Accounts</th><th>Action</th>
	</tr>
	<c:forEach items="${branches}" var="b">
		<tr>
			<td>${b.getBranchId()}</td>
			<td>${b.getBranchName()}</td>
			<td>${b.getBranchAddress().getAddressLine1()}
				${b.getBranchAddress().getAddressLine2()}
				${b.getBranchAddress().getCity()},
				${b.getBranchAddress().getState()}
				${b.getBranchAddress().getCountry()}
				${b.getBranchAddress().getZipcode()}
			
			</td>
			<td><c:forEach items="${b.getBranchAccount() }" var="account">
				${account.getAccountId()}<br/>
					${account.getAccountHolder()}<br/>
					${account.getAccountType()}<br/>
					${account.getAccountBalance()}<br/>
					${account.getAccountDateOpen() }<br/>
					${account.getAccountBranch().getBranchName()} (${account.getAccountBranch().getBranchId()})<br/>
			</c:forEach></td>
			<td>
				<a href="updateBranch?branchId=${b.getBranchId()}">Update</a>
				|
				<a href="deleteBranch?branchId=${b.getBranchId()}">Delete</a>
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
