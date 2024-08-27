<div class="container">

	<!-- Breadcrumb -->
	<div class="row">

		<div class="col-xs-12">


			<ol class="breadcrumb">

				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${contextRoot}/product/view/all/product">Products</a></li>
				<li class="breadcrumb-item active">${product.name}</li>

			</ol>


		</div>


	</div>

	<div class="row">

		<div class="col-xs-12 col-sm-4">

			<div class="thumbnail">

				<img src="${images}/${product.code}.jpg" class="img img-responsive" />

			</div>

		</div>

		<div class="col-xs-12 col-sm-8">

			<h3>${product.name}</h3>
			<hr />

			<p>${product.description}</p>
			<hr />

			<h4>
				Price: <strong> &#8377; ${product.unitPrice} /-</strong>
			</h4>


			<c:choose>

				<c:when test="${product.quantity < 1}">

					<h6>
						Qty. Available: <span style="color: red">Out of Stock!</span>
					</h6>

				</c:when>
				<c:otherwise>

					<h6>Qty. Available: ${product.quantity}</h6>

				</c:otherwise>

			</c:choose>
			<hr />

			<a href="#"
				class="btn btn-success"> <span
				class="glyphicon glyphicon-shopping-cart"></span> Add to Cart
			</a> </a> <a href="${contextRoot}/product/view/all/product" class="btn btn-primary">Back</a>

		</div>

	</div>


</div>



</div>