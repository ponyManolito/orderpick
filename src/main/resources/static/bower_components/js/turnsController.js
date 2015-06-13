homeApp.controller('turnController', function($scope, $http) {
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
    $scope.loadTurn = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/turns/getturn?id="+index).success(function(response) {
    		$scope.newturn = response;
    	});
    };
    $scope.addTurn = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewTurn = $scope.turn.id==""||$scope.turn.id=="0";
        $http.post("/turns/addturn",$scope.turn).success(function(response) {
    		$http.get("/turns/getall").success(function(response) {
    			$scope.turns = response;
    		});
    		$scope.newturn.id="";
        	$scope.newturn.number="";
        	$scope.newturn.init_time="";
        	$scope.newturn.finish_time="";
    		alert(isNewTurn?"Turn inserted successfully":"Turn updated successfully");
    	}).error(function(response, status, headers, config){
    		alert(response.message);
	    });
    };
    $scope.reset = function() {
    	$scope.newturn.id="";
    	$scope.newturn.number="";
    	$scope.newturn.init_time="";
    	$scope.newturn.finish_time="";
    }
    $scope.deleteturn = function(index) {
    	var r = confirm("Are you sure that you want to remove?");
    	if (r){
	        $http.delete("/turns/deleteturn/"+index).success(function(response) {
	        	$http.get("/turns/getall").success(function(response) {
	    			$scope.turns = response;
	    		});
	        	alert("Turn deleted successfully");
	    	}).error(function(response, status, headers, config){
	    		alert(response.message);
		    });
    	}
    };
	$http.get("/turns/getall").success(function(response) {
		$scope.turns = response;
	});
});
function turnPagController($scope) {
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
}
homeApp.controller('turnPagController', turnPagController);