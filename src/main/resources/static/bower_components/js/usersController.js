var usersApp = angular.module('usersApp', ['angularUtils.directives.dirPagination']);
usersApp.controller('userController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.add = function() {
        $scope.viewForm = !$scope.viewForm;
        if ($scope.viewForm){
        	$scope.icon = "glyphicon glyphicon-minus";
        }
        else{
        	$scope.icon = "glyphicon glyphicon-plus";
        }
    };
    $scope.loadUser = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/users/getuser?id="+index).success(function(response) {
    		$scope.user = response;
    	});
    };
	$http.get("/users/getall").success(function(response) {
		$scope.users = response;
	});
});
