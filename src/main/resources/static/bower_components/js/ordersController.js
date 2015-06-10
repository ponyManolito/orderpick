homeApp.controller('ordersControl', function($scope, $http) {
	$http.get("/orders/getall").success(function(response) {
		$scope.orders = response;
	});

	$scope.getBill = function(index) {
		$scope.viewForm = true;
		$scope.icon = "glyphicon glyphicon-euro";
		$http.get("/orders/getbill?id=" + index, {
			headers : {
				'AccessKeyId' : 'accesskey'
			},
			responseType : 'arraybuffer'
		}).success(function(data) {
			var file = new Blob([ data ], {
				type : 'application/pdf'
			});
			var fileURL = URL.createObjectURL(file);
			window.open(fileURL);
		});

	};
	$http.get("/tables/getall").success(function(response) {
		$scope.tables = response;
	});
});