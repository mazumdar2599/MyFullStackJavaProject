<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-xs-12 offset-md-1 col-md-10">
				<div class="alert alert-success alert-dismissible" role="alert">
					${message}
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
		</c:if>

		<div class="offset-md-1 col-md-10">
			<div class="card">

				<div class="card-heading bg-success text-white">
					<h1>Product Management</h1>
				</div>

				<div class="card-body">


					<sf:form method="post" modelAttribute="newProduct"
						action="${contextRoot}/manage/add/product"
						enctype="multipart/form-data">

						<div class="form-group row">
							<label class="col-md-4 col-form-label" for="name"> Enter
								Product Name </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="brand"> Enter
								Brand Name </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>

						</div>
						<div class="form-group row">
							<label class="col-md-4 col-form-label" for="name"> Enter
								Product Quantity </label>
							<div class="col-md-8">
								<sf:input type="text" path="quantity" id="quantity"
									placeholder="Quantity" class="form-control" />
								<sf:errors path="quantity" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4" for="brand"> Enter
								Unit Price </label>
							<div class="col-md-8">
								<sf:input type="text" path="unitPrice" id="unitPrice"
									placeholder="Unit Price" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4" for="description">
								Enter Product Description </label>
							<div class="col-md-8">
								<sf:textarea class="form-control" path="description" rows="5"
									id="description" placeholder="Enter product description : " />
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group row">
							<label class="col-form-label col-md-4">Upload a file</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4"> Select Supplier </label>
							<div class="col-md-8">
								<sf:select id="category" path="supplierId"
									items="${supplierlist}" itemLabel="firstName" itemValue="id"
									class="form-control">
								</sf:select>

							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4"> Select Category </label>
							<div class="col-md-8">
								<sf:select id="category" path="categoryId"
									items="${categorylist}" itemLabel="categoryName" itemValue="id"
									class="form-control">
								</sf:select>
								<br />
								<div class="text-right">
									<c:if test="${newProduct.id == 0 }">
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-warning btn-sm">ADD
											CATEGORY</button>
									</c:if>
								</div>
							</div>
						</div>

						<div class="form-group row">
							<sf:hidden path="id" />
							<sf:hidden path="code" />
							<sf:hidden path="active" />

							<div class="offset-md-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="submit"
									class="btn btn-primary btn-lg" />
							</div>

						</div>

					</sf:form>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<h1>Available products</h1>
		</div>
		<div class="col-md-12">

			<table id="adminProductsTable"
				class="table table-condensed table-bordered">

				<thead>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>ACTIVATE</th>
						<th>EDIT</th>
					</tr>
				</thead>



				<tfoot>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>ACTIVATE</th>
						<th>EDIT</th>
					</tr>
				</tfoot>


			</table>
		</div>
	</div>







	<div class="modal" id="myCategoryModal" tabindex="-1" role="dialog">

		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel">Add New Category</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">

					<sf:form id="categoryForm" class="form-horizontal"
						modelAttribute="category"
						action="${contextRoot}/manage/add/category" method="POST">

						<div class="form-group row">
							<label class="control-label col-md-4">Name</label>
							<div class="col-md-8 validate">
								<sf:input type="text" path="categoryName" class="form-control"
									placeholder="Category Name" />
							</div>
						</div>

						<div class="form-group row">
							<label class="control-label col-md-4">Description</label>
							<div class="col-md-8 validate">
								<sf:textarea path="description" class="form-control"
									placeholder="Enter category description here!" />
							</div>
						</div>


						<div class="form-group row">
							<div class="col-md-offset-4 col-md-4">
								<input type="submit" name="submit" value="Save"
									class="btn btn-primary" />
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

</div>