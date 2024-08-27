$(function() 
{
	
	//Fetch Active Product
	var $table = $('#productTable');
	
	if ($table.length) 
	{
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/active/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}
		
		$table.DataTable({
			lengthMenu : [ [ 3, 5, 10, -1 ], [ '3 Products', '5 Products', '10 Products', 'ALL Products' ] ],
			pageLength : 5,
			ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
			columns : [
						{
							data:'code',
							mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/rs/images/' + data
											+ '.jpg" class="dataTableImg"/>';
											}
						},
						{
							data : 'name',
						},
						{
							data : 'brand',
						},
						{
							data : 'unitPrice',
							mRender :function(data, type, row)
							{
								return '&#8377; ' + data
							}
						},
						{
							data : 'quantity',
							mRender :function(data, type, row)
							{
								if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;
							}
						},
						{
							data:'id',
							mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/product/view/single/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span>View Details</a> &#160;';
											if (userRole !== 'ADMIN') 
											{
												if (row.quantity < 1) 
													{
														str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
													} 
													else 
													{

														str += '<a href="'
																+ window.contextRoot
																+ '/cart/add/'
																+ data
																+ '/product" class="btn btn-success"><i class="fas fa-shopping-cart">Add To Cart</i></a>';
													}
											} 	
											else 
											{
													str += '<a href="'
															+ window.contextRoot
															+ '/manage/'
															+ data
															+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil">EDIT Product</span></a>';
											}
											return str;
											}
											
											
						}
				]
			
			});
		}
		
		
		//Fetch All Products
		
		var $table = $('#adminProductsTable');

	if ($table.length) {

		var jsonUrl = window.contextRoot + '/json/data/all/products';

		$table
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10', '30', '50', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="'
											+ window.contextRoot
											+ '/rs/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';

								}
							},

							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',
								
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									if (data) {
										str += '<label class="switch"> <input type="checkbox" value="'
												+ row.id
												+ '" checked="checked">  <div class="slider round"> </div></label>';

									} else {
										str += '<label class="switch"> <input type="checkbox" value="'
												+ row.id
												+ '">  <div class="slider round"> </div></label>';
									}

									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil">EDIT</span></a> &#160;';

									return str;
								}
								
							},	
					],
					initComplete : function() {
						var api = this.api();

						api
								.$('.switch input[type = "checkbox"]')
								.on(
										'change',
										function() {

											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var msg = (checked) ? 'You want to activate the product?'
													: 'You want to deactivate the product?';
											var value = checkbox.prop('value');
											console.log(value);
											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation/Deactivation',
														message : msg,
														callback : function(
																confirmed) {
															if (confirmed) {
																var activeurl = window.contextRoot
																		+ '/manage/product/'
																		+ checkbox
																				.prop('value')
																		+ '/activation';
																$
																		.post(
																				activeurl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data,

																							});
																				})

															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														},
													});
										});
					}

				});
	}
	
	
	

	
	$('button[name="refreshCart"]').click(function()
			{
		var cartLineId = $(this).attr('value');
		
		var countField = $('#count_' + cartLineId);
		
		var originalCount = countField.attr('value');
		

		var updatedCount = countField.val();
		
		if(updatedCount != originalCount) 
		{	
			
				
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + updatedCount;
				window.location.href = updateUrl;
			
		}
		});

});