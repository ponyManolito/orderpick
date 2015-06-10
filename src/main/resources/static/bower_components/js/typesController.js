homeApp.controller('typeController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.messageSuccess = "";
	$scope.messageError = "";
	$scope.add = function() {
        $scope.viewForm = !$scope.viewForm;
        if ($scope.viewForm){
        	$scope.icon = "glyphicon glyphicon-minus";
        }
        else{
        	$scope.icon = "glyphicon glyphicon-plus";
        }
        $scope.messageSuccess = "";
    	$scope.messageError = "";
    };
    $scope.loadType = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/types/gettype?id="+index).success(function(response) {
    		$scope.newtype = response;
    		$scope.messageSuccess = "";
    		$scope.messageError = "";
    	});
    };
    $scope.addType = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewType = $scope.newtype.id==""||$scope.newtype.id=="0";
        $http.post("/types/addtype",$scope.newtype).success(function(response) {
    		$http.get("/types/getall").success(function(response) {
    			$scope.types = response;
    		});
    		$scope.newtype.id="";
        	$scope.newtype.name="";
        	$scope.newtype.description="";
        	$scope.messageSuccess = 'true';
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
        	$scope.messageError = "true";
	    });
    };
    $scope.reset = function() {
    	$scope.newtype.id="";
    	$scope.newtype.name="";
    	$scope.newtype.description="";
    	$scope.messageSuccess = "";
    	$scope.messageError = "";
    }
    $scope.deleteType = function(index) {
        $http.delete("/types/deletetype/"+index).success(function(response) {
        	$http.get("/types/getall").success(function(response) {
    			$scope.types = response;
    		});
        	$scope.messageSuccess = 'true';
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
        	$scope.messageError = "true";
	    });
    };
	$http.get("/types/getall").success(function(response) {
		$scope.types = response;
		$scope.messageSuccess = "";
    	$scope.messageError = "";
	});
});
