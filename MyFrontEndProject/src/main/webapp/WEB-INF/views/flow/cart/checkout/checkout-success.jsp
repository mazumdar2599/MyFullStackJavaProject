<%@include file="../../shared/header.jsp"%>
<!-- Page Content -->

<div class="container">
	<div class="alert alert-success">
		<h3 class="text-center">Your Order is Confirmed!!</h3>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="invoice-title">
				<h2>Invoice</h2>
				<h3 class="pull-right">Order # ${orderDetails.id}</h3>
			</div>
			<hr>
			<div class="row">
				<div class="col-xs-6">
					<address>
						<strong>Billed To:</strong><br> ${orderDetails.user.firstName}
						${orderDetails.user.lastName}<br>
						${orderDetails.billing.addressLineOne}<br>
						${orderDetails.billing.addressLineTwo}<br>
						${orderDetails.billing.city} - ${orderDetails.billing.postalCode}<br>
						${orderDetails.billing.state} - ${orderDetails.billing.country}
					</address>
				</div>
				<div class="col-xs-6 text-right">
					<address>
						<strong>Shipped To:</strong><br>
						${orderDetails.user.firstName} ${orderDetails.user.lastName}<br>
						${orderDetails.shipping.addressLineOne}<br>
						${orderDetails.shipping.addressLineTwo}<br>
						${orderDetails.shipping.city} - ${orderDetails.shipping.postalCode}<br>
						${orderDetails.shipping.state} - ${orderDetails.shipping.country}
					</address>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<address>
						<strong>Payment Method:</strong><br> Card Payment <br>
						${orderDetails.user.email}
					</address>
				</div>
				<div class="col-xs-6 text-right">
					<address>
						<strong>Order Date:</strong><br> ${orderDetails.orderDate}<br>
						<br>
					</address>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Order summary</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-condensed">
							<thead>
								<tr>
									<td><strong>Item</strong></td>
									<td class="text-center"><strong>Price</strong></td>
									<td class="text-center"><strong>Quantity</strong></td>
									<td class="text-right"><strong>Totals</strong></td>
								</tr>
							</thead>
							<tbody>
								<!-- foreach ($order->lineItems as $line) or some such thing here -->
								<c:forEach items="${orderDetails.orderItems}" var="orderItem">
									<tr>
										<td>${orderItem.product.name}</td>
										<td class="text-center">&#8377; ${orderItem.buyingPrice}</td>
										<td class="text-center">${orderItem.productCount}</td>
										<td class="text-right">&#8377; ${orderItem.total}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center">
		<a href="${contextRoot}/product/view/all/product"
			class="btn btn-lg btn-warning">Continue Shopping</a>
	</div>
</div>
<%@include file="../../shared/footer.jsp"%>