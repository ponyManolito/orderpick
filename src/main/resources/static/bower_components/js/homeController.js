var homeApp = angular.module('homeApp', ['angularUtils.directives.dirPagination','angularFileUpload','angularjs-dropdown-multiselect']);
homeApp.controller('homeController', function($scope, $http) {
	$scope.iconHomePlus = "glyphicon glyphicon-plus";
	$scope.viewConf = false;
	$scope.viewSettings = false;
	$scope.view = "/orderserver/viewusers";
	$scope.tabs = ["active-menu","","","",""];
	$scope.imageProfile = "../orderserver/static/bower_components/img/";
	$http.get("/orderserver/users/isadmin").success(function(response) {
		$scope.viewSettings = response;
		$scope.imageProfile +=response?"user_admin.png":"find_user.png";
	}).error(function(response, status, headers, config){
		alert(response.message);
    });
	$scope.showConf = function() {
        $scope.viewConf = !$scope.viewConf;
        if ($scope.viewConf){
        	$scope.iconHomePlus = "glyphicon glyphicon-minus";
        }	
        else{
        	$scope.iconHomePlus = "glyphicon glyphicon-plus";
        }
    };
    $scope.clickTab = function(index) {
    	for (i = 0, len = $scope.tabs.length; i < len; ++i) {
    		$scope.tabs[i]="";
    	}
    	$scope.tabs[index] = "active-menu";
    	switch (index) {
			case 0:$scope.view = "/orderserver/viewusers";break;
			case 1:$scope.view = "/orderserver/viewtypes";break;
			case 2:$scope.view = "/orderserver/viewproducts";break;
			case 3:$scope.view = "/orderserver/viewturns";break;
			case 4:$scope.view = "/orderserver/viewtables";break;
			case 5:$scope.view = "/orderserver/viewpaymentsdata";break;
			default:$scope.view = "/orderserver/viewusers";break;
		}
    };
});