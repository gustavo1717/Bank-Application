<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | Customer Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css" type="text/css"> 


<style>


form{
	margin-bottom: 20px;
}

.radio-container {
	display: flex;
	justify-content: space-between;
}

.radio-buttons + label{
	flex: 1;
	align-items: center;
	margin-bottom: 0px;
}


</style>
</head>

<body>

<!-- The Modal -->



<div class="container" align="center">

<!-- Navbar -->
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
            <li class="nav-item">
                <a class="nav-link" href="userForm">User Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="roleForm">Role Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="branchForm">Branch Form</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="customerForm">Customer Form <span class="sr-only">(current)</span></a>
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

<h1>Customer Form</h1>


<f:form action="saveCustomer" modelAttribute="customer">
<table class="table table-striped table-bordered">
<tbody>
	<tr>
		<td>Customer Id: </td><td> <f:input path="customerId" value="${r.customerId}"/></td>
		<td> <f:errors path="customerId" cssClass="error"/></td>
	
	<tr>
		<td>Name</td>
		<td><f:input path="customerName"/></td>
		<td><f:errors path="customerName" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Gender</td>
		<td>
		<div class="radio-container">
		<f:radiobutton cssClass="radio-buttons" label="Male" path="customerGender" value="Male"/>
		<f:radiobutton cssClass="radio-buttons" label="Female" path="customerGender" value="Female"/>

		<f:radiobutton cssClass="radio-buttons" label="Other" path="customerGender" value="Other"/></td>
		</div>
		<td><f:errors path="customerGender" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Date of Birth</td>
		<td><f:input path="customerDob"/></td>
		<td><f:errors path="customerDob" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Phone Number</td>
		<td><f:input path="customerMobileNum"/></td>
		<td><f:errors path="customerMobileNum" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Real ID</td>
		<td><f:input path="realId"/></td>
		<td><f:errors path="realId" cssClass="error"/></td>
	</tr>
</tbody>
</table>
<table class="table table-striped table-bordered">
<tbody>
	
		<h3>Customer Address</h3>
	
	<tr>
		<td>Address Line 1</td>
		<td><f:input path="customerAddress.addressLine1"/></td>
		<td><f:errors path="customerAddress.addressLine1" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Address Line 2</td>
		<td><f:input path="customerAddress.addressLine2"/></td>
		<td><f:errors path="customerAddress.addressLine2" cssClass="error"/></td>
	</tr>
	<tr>
		<td>City</td>
		<td><f:input path="customerAddress.city"/></td>
		<td><f:errors path="customerAddress.city" cssClass="error"/></td>
	</tr>
	<tr>
		<td>State</td>
		<td><f:input path="customerAddress.state"/></td>
		<td><f:errors path="customerAddress.state" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Country</td>
		<td><f:input path="customerAddress.country"/></td>
		<td><f:errors path="customerAddress.country" cssClass="error"/></td>
	</tr>
	<tr>
		<td>ZipCode</td>
		<td><f:input path="customerAddress.zipcode"/></td>
		<td><f:errors path="customerAddress.zipcode" cssClass="error"/></td>
	</tr>

	<tr>
		<td>User Id</td>
		<td>

		<f:select path="user">
			<c:forEach items="${users}" var="u">
				<f:option value="${u }">${u.getUserId()}: ${u.getUsername()}</f:option>
				
			</c:forEach>
		</f:select>
		</td>
		
	</tr>

	</tbody>
</table>
<input id="submitBtn" type="submit" value="submit" >

</f:form>

<c:if test="${customers.size() > 0 }">
<table class="table table-striped table-bordered">
	<tr>
		<th>Customer Id</th><th>Name</th><th>Gender</th><th>DOB</th><th>Phone Number</th><th>Real ID</th><th>Address</th><th>User</th><th>Accounts</th><th>Action</th>
	</tr>
	<c:forEach items="${customers }" var="r">
		<tr>
			<td>${r.getCustomerId()}</td>
			<td>${r.getCustomerName()}</td>
			<td>${r.getCustomerGender()}</td>
			<td>${r.getCustomerDob()}</td>
			<td>${r.getCustomerMobileNum()}</td>
			<td>${r.getRealId()}</td>
			<td>${r.getCustomerAddress().getAddressLine1()}
				${r.getCustomerAddress().getAddressLine2()}
				${r.getCustomerAddress().getCity()},
				${r.getCustomerAddress().getState()}
				${r.getCustomerAddress().getCountry()}
				${r.getCustomerAddress().getZipcode()}
				</td>
			<td>${r.getUser().getUsername() }</td>
			<td>
				<c:forEach items="${r.getCustomerAccounts()}" var="account">
					${account.getAccountId()}<br/>
					${account.getAccountHolder()}<br/>
					${account.getAccountType()}<br/>
					${account.getAccountBalance()}<br/>
					${account.getAccountDateOpen() }<br/>
					${account.getAccountBranch().getBranchName()} (${account.getAccountBranch().getBranchId()})<br/>
				</c:forEach>
				
			</td>
	
			<td>
			<a href="updateCustomer?customerId=${r.getCustomerId()}">Update</a>
			|
			<a href="deleteCustomer?customerId=${r.getCustomerId()}">Delete</a>
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
