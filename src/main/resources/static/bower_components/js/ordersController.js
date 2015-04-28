homeApp.controller('ordersControl', function($scope, $http) {
	$http.get("/orders/getall").success(function(response) {
		$scope.orders = response;
	});
});