var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$http.get("/orders/getall").success(function(response) {
		$scope.orders = response;
	});
});
/*ordersApp.factory('OrderAll', function($resource) {
   var source = $resource("http://localhost:8080/orders/getall");
   var orders =source.get({},function(){
      //this log shows the data
     console.log (orders); 
    })

    return orders;
});*/

//function GetOrders($scope, OrderAll) {
/*function GetOrders($scope, $http) {
	
    //$scope.orders = [OrderAll];
    //this log shows the promise
    //console.log($scope.orders);
	alert()
	$http({method: 'POST', url: '/orders/getall'}).success(function(data){
		$scope.orders = data; // response data 
	});
}*/