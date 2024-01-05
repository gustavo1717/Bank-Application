<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | Account Form</title>
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
            <li class="nav-item ">
                <a class="nav-link" href="userForm">User Form </a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="roleForm">Role Form</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="branchForm">Branch Form </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="customerForm">Customer Form</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="accountForm">Account Form <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="transactionForm">Transaction Form</a>
            </li>
        </ul>
    </div>
</nav>

<h1>Account Form</h1>
<f:form id="form" action="saveAccount" modelAttribute="account">
<table class="table table-striped table-bordered">
	<tr>
		<td>Account Id: </td><td> <f:input path="accountId" value="${account.accountId}"/></td>
		<td> <f:errors path="accountId" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Account Customer</td>
		<td>
			<f:select id="customerSelect" path="accountCustomer" value="${account.accountCustomer }">
				<f:option value=""/>
				<c:forEach items="${customers}" var="c">
				
					<f:option value="${c}" label="${c.getCustomerId()}: ${c.getCustomerName()}"/>
				</c:forEach>
			</f:select>
		</td>
	</tr>
	<tr>
		<td>Account Holder:</td>
		<td><f:input id="holder" readonly="true" path="accountHolder" value="${account.accountHolder }"/></td>
		<td><f:errors path="accountHolder" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Account Type: </td>
		<td><f:select path="accountType" value="${account.accountType}">
				<f:options items="${accountTypes}"/>
			</f:select>
		</td>
		<td> <f:errors path="accountType" cssClass="error"/></td>
	</tr>
	
	
		<f:hidden id="currentTimePath" path="accountDateOpen" value="${account.accountDateOpen}"/>
		
	
	
	<tr>
		<td>Account Balance: </td><td> <f:input path="accountBalance" value="${account.accountBalance}"/></td>
		<td> <f:errors path="accountBalance" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Account Branch:</td>
		<td><f:select path="accountBranch" value="${account.accountBranch }">
			<c:forEach items="${ branches}" var="b">
				<c:if test="${b.getBranchId() != 36}">
					<f:option value="${b}" label="${b.getBranchId()}: ${b.getBranchName()}"/>
				</c:if>
			</c:forEach>
		</f:select></td>
		<td><f:errors path="accountBranch" cssClass="error"/></td>
	</tr>
	
</table>
<input id="submitBtn" type="submit" value="submit" >

</f:form>
<c:if test="${accounts.size() > 0 }">
<table border="1">
	<tr>
		<th>Account Id</th><th>Holder</th><th>Type</th><th>Date Of Creation</th><th>Balance</th><th>Branch</th><th>Customer</th><th>Action</th>
	</tr>
	<c:forEach items="${accounts }" var="r">
		<tr>
			<td>${r.getAccountId()}</td>
			<td>${r.getAccountHolder()}</td>
			<td>${r.getAccountType() }</td>
			<td>${r.getAccountDateOpen() }</td>
			<td>${r.getAccountBalance()}</td>
			<c:if test="${r.getAccountBranch() != null }">
			<td>${r.getAccountBranch().getBranchId() }: ${r.getAccountBranch().getBranchName() }</td>
			</c:if>
			<c:if test="${r.getAccountCustomer() != null }">
			<td>${r.getAccountCustomer().getCustomerId() }: ${r.getAccountCustomer().getCustomerName() } </td>
			</c:if>
	
			<td>
			<a href="updateAccount?accountId=${r.getAccountId()}">Update</a>
			|
			<a href="deleteAccount?accountId=${r.getAccountId()}">Delete</a>
		</td>
		</tr>
		
	</c:forEach>
</table>
</c:if>
</div>
	
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	
	
<script>
	$(document).ready(function() {
		$("#submitBtn").click(function(e){
			e.preventDefault();
			
			var now = new Date().toISOString()
			now = now.substring(0, now.indexOf("T"));
			$("#currentTimePath").val(now);
			console.log($("#currentTimePath").val());
			
			$("#form").submit();
		})
		$("#customerSelect").on('click',function(e){
			var txt = $("#customerSelect option:selected").text();
			txt = txt.substring(txt.indexOf(":")+1).trim();
			$("#holder").val(txt);
		})
	})</script>
	
	
</body>
</html>