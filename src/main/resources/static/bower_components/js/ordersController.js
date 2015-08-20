homeApp.controller('ordersControl', function($scope, $http) {
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.socket = new SockJS('/orderserver/insertorder');
	$scope.stompClient = Stomp.over($scope.socket);
	$scope.stompClient.connect({}, function() {
		$scope.stompClient.subscribe('/orderserver/topic/orders', function(result){
        	$scope.orders = response;
        });
    }, function(error) {
        alert(error)
    });
	$http.get("/orderserver/orders/getallalive").success(function(response) {
		$scope.orders = response;
	});
});