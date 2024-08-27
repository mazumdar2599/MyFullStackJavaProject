<div class="row">

	<%@ include file="./shared/sidemenu.jsp"%>
	<!-- /.col-lg-3 -->

	<div class="col-lg-9">

		<div class="row">
			<c:if test="${userclickallproducts == true}" var="data">
				<script>
					window.categoryId = '';
				</script>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
					<li class="breadcrumb-item active">All Products</li>
				</ol>
			</c:if>
			<c:if test="${userclickcategoryproducts == true}" var="data">
				<script>
					window.categoryId = '${category.id}';
				</script>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
					<li class="breadcrumb-item"><a
						href="${contextRoot}/product/view/all/product">Product</a></li>
					<li class="breadcrumb-item active">${category.categoryName}</li>
				</ol>
			</c:if>
		</div>
		<div class="col-sm-12">
			<table id="productTable"
				class="table table-condensed table-striped table-hover">

				<thead>
					<tr>
						<th></th>
						<th>NAME</th>
						<th>BRAND</th>
						<th>UNIT PRICE</th>
						<th>AVAILABLE QUANTITY</th>
						<th></th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th></th>
						<th>NAME</th>
						<th>BRAND</th>
						<th>UNIT PRICE</th>
						<th>AVAILABLE QUANTITY</th>
						<th></th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

