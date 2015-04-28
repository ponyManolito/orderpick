homeApp.controller('tableController', function($scope, $http) {
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
    $scope.loadTable = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/tables/gettable?id="+index).success(function(response) {
    		$scope.newtable = response;
    	});
    };
    $scope.addTable = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewTable = $scope.newtable.id==""||$scope.newtable.id=="0";
        $http.post("/tables/addtable",$scope.newtable).success(function(response) {
    		$http.get("/tables/getall").success(function(response) {
    			$scope.tables = response;
    		});
    		$scope.newtable.id="";
        	$scope.newtable.name="";
        	$scope.newtable.description="";
    		alert(isNewTable?"Table inserted successfully":"Table updated successfully");
    	}).error(function(response, status, headers, config){
    		alert(response.message);
	    });
    };
    $scope.reset = function() {
    	$scope.newtable.id="";
    	$scope.newtable.name="";
    	$scope.newtable.description="";
    }
    $scope.deleteUser = function(index) {
    	var r = confirm("Are you sure that you want to remove?");
    	if (r){
	        $http.delete("/tables/deletetable/"+index).success(function(response) {
	        	$http.get("/tables/getall").success(function(response) {
	    			$scope.tables = response;
	    		});
	        	alert("Table deleted successfully");
	    	}).error(function(response, status, headers, config){
	    		alert(response.message);
		    });
    	}
    };
	$http.get("/tables/getall").success(function(response) {
		$scope.tables = response;
	});
});
