<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DF Bank | Bank Transaction Form</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/main.css" type="text/css"> 



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$("#submitBtn").click(function(e){
			e.preventDefault();
			
			
			var now = new Date().toISOString()
			$("#currentTimePath").val(now);
			console.log($("#currentTimePath").val());
			
			console.log($("#transactionType").val() === "NEW_ACCOUNT")
			
			$("#form").submit();
			
			if($("#transactionType").val() === "DEPOSIT"){
				$("#fromAccount").hide();
				$("#toAccount").show();
			} if($("#transactionType").val() === "WITHDRAWAL") {
				$("#fromAccount").show();
				$("#toAccount").hide();
			} if($("#transactionType").val() === "TRANSFER") {
				$("#fromAccount").show();
				$("#toAccount").show();
			} if($("#transactionType").val() === "NEW_ACCOUNT"){
		
				$("#fromAccount").show();
				$("#toAccount").hide();
			}
			
		})
		$("#transactionType").on('change', function(){
			console.log(this.value)
			if(this.value === "DEPOSIT"){
				$("#fromAccount").hide();
				$("#toAccount").show();
			} if(this.value === "WITHDRAWAL") {
				$("#fromAccount").show();
				$("#toAccount").hide();
			} if(this.value === "TRANSFER") {
				$("#fromAccount").show();
				$("#toAccount").show();
			} if(this.value === "NEW_ACCOUNT"){
				$("#fromAccount").show();
				$("#toAccount").hide();
			}
		})
		$("#fromAccount select").on('change', function(){
			var selected = $(this).val();
			
		    $("#toAccount select option").prop('disabled', false);

			
			if(selected){
				$("#toAccount select option[value='" + selected + "']").prop('disabled', true);
				$("#toAccount select").val("");
			}
			
			if($("#toAccount select").val() === selected) {
				$("#toAccount select").val("");
			}
		})
	})
</script>
<style>
body {
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; /* You can replace this with any font of your choice */
    font-size: 16px;
    line-height: 1.5;
    color: #333;

    /* Background and layout */
    background-color: #f5f5f5; /* Light gray background */
    width: 100%;
    overflow-x: hidden; /* Prevent horizontal scrolling */

    /* Spacing */
    padding: 20px;
}
.container{
	max-width: 1200px;
	margin: 0 auto; 
	background-color: #ffffff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
.error{
    color:red;
    font-style:italic;
    font-weight:bold;
}
</style>
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
                <a class="nav-link" href="branchForm">Branch Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="customerForm">Customer Form</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="accountForm">Account Form</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="transactionForm">Transaction Form <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>

<h1>Transaction Form</h1>
<f:form id="form" action="saveBankTransaction" modelAttribute="bankTransaction">
<table class="table table-striped table-bordered">
		
			
				
			
				<f:hidden path="bankTransactionId"/>
			
			<tr>
			<td>
				Transaction Type
			</td>
			<td>
				<f:select id="transactionType" path="transactionType" items="${transactionTypes}"/>
			</td>
			<td> <f:errors path="transactionType"/></td>
		</tr>
		
		<tr id="fromAccount" >
			<td>
				From Account
			</td>
			<td>
				<f:select path="bankTransactionFromAccount" >
				<f:option value=""/>
					<c:forEach items="${accounts}" var="account">
			<f:option value="${account}">${account.getAccountHolder() }: ${account.getAccountId() } (${account.getAccountBalance()})</f:option>					</c:forEach>
				</f:select>
			</td>
			<td> <f:errors path="bankTransactionFromAccount"/></td>
		</tr>
		
		<tr id="toAccount" >
			<td>
				To Account
			</td>
			<td>
				<f:select path="bankTransactionToAccount">
					<f:option value=""/>
					<c:forEach items="${accounts}" var="account">
						<f:option value="${account}">${account.getAccountHolder() }: ${account.getAccountId() } (${account.getAccountBalance()})</f:option>
					</c:forEach>
				</f:select>
				
			</td>
			<td> <f:errors path="bankTransactionToAccount"/></td>
		</tr>
		
		<tr>
			<td>
				Amount
			</td>
			<td>
				<f:input path="transactionAmount"/>
			</td>
			<td> <f:errors path="transactionAmount"/></td>
		</tr>
		
				<f:hidden id="currentTimePath" path="bankTransactionDateTime"/>
			
		<tr>
			<td>
				Comments
			</td>
			<td>
				<f:input path="comments"/>
			</td>
			<td> <f:errors path="comments"/></td>
		</tr>
	</table>
	<input type="submit" value="submit" id="submitBtn"/>
</f:form>

<c:if test="${transactions.size() > 0 }">
	<table border="1">
		<tr>
			<th>Transaction ID</th><th>To</th><th>From</th><th>Amount</th><th>Type</th><th>Time</th><th>Comments</th>
		</tr>
		<c:forEach items="${transactions}" var="u">
			<tr>
				<td>${u.getBankTransactionId()}</td>
				<td>${u.getBankTransactionToAccount().getAccountId()}:${u.getBankTransactionToAccount().getAccountHolder() }</td>
				<td>${u.getBankTransactionFromAccount().getAccountId()}:${u.getBankTransactionFromAccount().getAccountHolder() }</td>
				<td>${u.getTransactionAmount()}</td>
				<td>${u.getTransactionType()}</td>
				<td>${u.getBankTransactionDateTime()}</td>
				<td>${u.getComments() }</td>
				
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