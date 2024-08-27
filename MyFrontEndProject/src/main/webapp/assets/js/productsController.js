var app = angular.module('ShoppingApp', []);

app.controller('ProductController', function($http) {
	
	var me = this;
		
	me.mvProducts = [];
	
	me.fetchProducts = function() {
		
		
		$http.get('/MyFrontEndProject/json/data/top/purchased/products')
			.then(function(response) {
				me.mvProducts = response.data;
		});
			
			
				
	}
	
});