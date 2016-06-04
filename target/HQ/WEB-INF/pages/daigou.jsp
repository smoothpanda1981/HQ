<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title></title>
	<style>
		.header {
			color: #36A0FF;
			font-size: 27px;
			padding: 10px;
		}

		.bigicon {
			font-size: 35px;
			color: #36A0FF;
		}
	</style>
	<%@ include file="header.jsp"  %>
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
<%@ include file="menus.jsp"  %>
<%--<div class="container">--%>
<%--<h1>${message}</h1>--%>
<%--<br>--%>

<%--<p>--%>
<%--Text Text Text Text Text <br>--%>
<%--Text Text Text Text Text <br>--%>
<%--Text Text Text Text Text <br>--%>
<%--Text Text Text Text Text <br>--%>
<%--Text Text Text Text Text <br>--%>
<%--</p>--%>

<%--<form:form method="post" modelAttribute="customer">--%>
<%--Email :   <form:input path="email" /><br>--%>
<%--Content : <form:textarea path="content" rows="5" cols="50" /><br>--%>
<%--<input type="submit" />--%>
<%--</form:form>--%>


<%--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->--%>
<%--<script src="js/jquery-1.11.1.min.js"></script>--%>
<%--<!-- Include all compiled plugins (below), or include individual files as needed -->--%>
<%--<script src="js/bootstrap.min.js"></script>--%>
<%--</div>--%>

<div class="container">
	<div class="panel-group" id="accordion">
		<div class="faqHeader">General questions</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Is account registration required?</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					Account registration at <strong>PrepBootstrap</strong> is only required if you will be selling or buying themes.
					This ensures a valid communication channel for all parties involved in any transactions.
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTen">Can I submit my own Bootstrap templates or themes?</a>
				</h4>
			</div>
			<div id="collapseTen" class="panel-collapse collapse">
				<div class="panel-body">
					A lot of the content of the site has been submitted by the community. Whether it is a commercial element/template/theme
					or a free one, you are encouraged to contribute. All credits are published along with the resources.
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven">What is the currency used for all transactions?</a>
				</h4>
			</div>
			<div id="collapseEleven" class="panel-collapse collapse">
				<div class="panel-body">
					All prices for themes, templates and other items, including each seller's or buyer's account balance are in <strong>USD</strong>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">
					<form:form class="form-horizontal" method="post" modelAttribute="customer">
						<fieldset>
							<legend class="text-center header">Contact us</legend>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
								<div class="col-md-8">
									<form:input path="fname" name="name" type="text" placeholder="First Name" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-user bigicon"></i></span>
								<div class="col-md-8">
									<form:input path="lname" name="name" type="text" placeholder="Last Name" class="form-control" />
								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-8">
									<form:input path="email" name="email" type="text" placeholder="Email Address" class="form-control" />
								</div>
							</div>

							<div class="forms-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-phone-square bigicon"></i></span>
								<div class="col-md-8">
									<form:input path="phone" name="phone" type="text" placeholder="Phone" class="form-control" />
								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i class="fa fa-pencil-square-o bigicon"></i></span>
								<div class="col-md-8">
									<form:textarea class="form-control" path="content" name="content" placeholder="Enter your massage for us here. We will get back to you within 2 business days." rows="7"></form:textarea>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-12 text-center">
									<input type="submit" class="btn btn-primary btn-lg" value="Submit" />
								</div>
							</div>
						</fieldset>
					</form:form>

					<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
					<script src="js/jquery-1.11.1.min.js"></script>
					<!-- Include all compiled plugins (below), or include individual files as needed -->
					<script src="js/bootstrap.min.js"></script>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"  %>
</body>
</html>