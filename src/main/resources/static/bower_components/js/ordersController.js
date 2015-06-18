homeApp.controller('ordersControl', function($scope, $http) {
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.socket = new SockJS('/insertorder');
	$scope.stompClient = Stomp.over($scope.socket);
	$scope.stompClient.connect({}, function() {
		$scope.stompClient.subscribe('/topic/orders', function(result){
        	$scope.orders = response;
        });
    }, function(error) {
        alert(error)
    });
	$http.get("/orders/getallalive").success(function(response) {
		$scope.orders = response;
	});
});