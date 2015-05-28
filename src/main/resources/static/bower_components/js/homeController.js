var homeApp = angular.module('homeApp', ['angularUtils.directives.dirPagination','angularFileUpload']);
homeApp.controller('homeController', function($scope, $http) {
	$scope.iconHomePlus = "glyphicon glyphicon-plus";
	$scope.viewConf = false;
	$scope.view = "/viewusers";
	$scope.tabs = ["active","","","",""];
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
    	$scope.tabs[index] = "active";
    	switch (index) {
			case 0:$scope.view = "/viewusers";break;
			case 1:$scope.view = "/viewtypes";break;
			case 2:$scope.view = "/viewproducts";break;
			case 3:$scope.view = "/viewturns";break;
			case 4:$scope.view = "/viewtables";break;
			case 5:$scope.view = "/viewpaymentsdata";break;
			default:$scope.view = "/viewusers";break;
		}
    };
});