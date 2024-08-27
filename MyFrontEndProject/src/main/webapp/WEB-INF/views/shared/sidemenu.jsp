<div class="col-lg-3">
	<h1 class="my-4">Online Shopping</h1>
	<div class="list-group">

		<c:forEach items="${categoryList}" var="category">
		<a href="${contextRoot}/product/view/category/${category.id}/product" >${category.categoryName}</a>
		</c:forEach>

	</div>

</div>