<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/main.css" type="text/css"> 

<style>
.jumbotron {
	background-image: url("data:image/jpeg;base64,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");
	text-align: left;
	color: white;
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
}
.jumbotron h1 {
	font-size: 72px;
}
.rounded-pill {
	text-align: center;
	width: 25%;
	margin-left: 66px;
	margin-right: 66px;
	margin-top:20px;
	background-color: white
}
.rounded-pill a {
	color: #333;
}
</style>

</head>
<body>

<div class="container" align="center">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">GH Bank</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
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


<div class="jumbotron">
	<h1>Bank</h1>
	<br/>
	<div class="row">
		<div class="col-sm border rounded-pill">
			<h2><a href="userForm">Open an account</a></h2>
		</div>
		<div class="col-sm">
		</div>
		<div class="col-sm">
		</div>
	</div>
</div>
	<br/>
	<br/>
	
	<div class="row">
		<
		<div class="col-sm">
			<h3>Rewards!</h3>
			<p>This is exciting text about the financial world.</p>
			<p>This is exciting text about the financial world.</p>
			<p>This is exciting text about the financial world.</p>
		
		</div>
		
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	

</body>
</html>