<script src="${js}/angular.js"></script>

<!-- DataTable Bootstrap Script -->
<script src="${js}/productsController.js"></script>
<div class="container" ng-app="ShoppingApp"
	ng-controller="ProductController as pCtrl">
	<div class="row" ng-init="pCtrl.fetchProducts()">

		<%@ include file="./shared/sidemenu.jsp"%>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">

			<div class="row">
				<div class="col-xs-12">
					<h3>Our Most Purchased Products</h3>
					<hr />
				</div>
			</div>
			
			<div class="row is-table-row">
                	
                    <div class="col-sm-4" ng-repeat="product in pCtrl.mvProducts">                    	
                        <div class="thumbnail">
                            <img ng-src="${images}/{{product.code}}.jpg" alt="{{product.name}}" class="landingImg">
                            <h5>{{product.name}}</h5>
                            <hr/>
                            <div class="caption">
                                <h4 class="pull-right">&#8377; {{product.unitPrice}}</h4>
                                
                                <a ng-href="${contextRoot}/product/view/single//{{product.id}}/product" class="btn btn-primary pull-right">View</a>
                            </div>
                        </div>
                        
                    </div>


                </div>

		</div>


	</div>

</div>