<div class="container">
	<c:if test="${not empty message}">
		<div class="alert alert-info alert-dismissible " role="alert">
			${message}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${not empty cartlines}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 45%">Product</th>
						<th style="width: 20%">Price</th>
						<th style="width: 7%">Quantity</th>
						<th style="width: 20%" class="text-center">Subtotal</th>
						<th style="width: 7%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cartlines}" var="cartLine">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-4 hidden-xs">
										<img src="${images}/${cartLine.product.code}.jpg"
											alt="${cartLine.product.name}" class="dataTableImg" />
									</div>
									<div class="col-sm-8">
										<h4 class="nomargin">${cartLine.product.name}</h4>
										<p>Brand : ${cartLine.product.brand}</p>

									</div>
								</div>
							</td>
							<td data-th="Price">&#8377; ${cartLine.buyingPrice} /-</td>
							<td data-th="Quantity"><input type="number"
								id="count_${cartLine.id}" class="form-control-plaintext"
								value="${cartLine.productCount}" min="1"></td>
							<td data-th="Subtotal" class="text-center">&#8377;
								${cartLine.total} /-</td>
							<td class="actions" data-th="">
								<button type="button" name="refreshCart"
									class="btn btn-info btn-sm" value="${cartLine.id}">
									<i class="fa fa-refresh">Refresh</i>
								</button> <a href="${contextRoot}/cart/${cartLine.id}/remove"
								class="btn btn-danger btn-sm"> <i class="fa fa-trash-o">Delete</i>
							</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr class="visible-xs">
						<td class="text-center"><strong>Grand Total &#8377;
								${userModel.cart.grandTotal}/-</strong></td>
					</tr>
					<tr>
						<td><a href="${contextRoot}/product/view/all/product"
							class="btn btn-warning"><i class="fa fa-angle-left"></i>
								Continue Shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>

						<td class="hidden-xs text-center"><strong>Final Bill
								&#8377; ${userModel.cart.grandTotal}/-</strong></td>


						<td><a href="${contextRoot}/cart/validate" class="btn btn-success btn-block">Checkout
								<i class="fa fa-angle-right"></i>
						</a></td>
					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>

			<div class="jumbotron">

				<h3 class="text-center">Your Cart is Empty!</h3>

			</div>

		</c:otherwise>
	</c:choose>
</div>