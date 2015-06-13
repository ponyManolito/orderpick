homeApp.controller('ordersControl', function($scope, $http) {
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.socket = new SockJS('/insertorder');
	$scope.stompClient = Stomp.over($scope.socket);
	$scope.stompClient.connect({}, function() {
        stompClient.subscribe('/topic/orders', function(result){
        	$scope.orders = response;
        });
    }, function(error) {
        alert(error)
    });
});