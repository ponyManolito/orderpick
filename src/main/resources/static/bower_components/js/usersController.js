homeApp.controller('userController', function($scope, $http) {
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
    		$scope.newuser = response;
    	});
    };
    $scope.addUser = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewUser = $scope.user.id==""||$scope.user.id=="0";
        $http.post("/users/adduser",$scope.user).success(function(response) {
    		$http.get("/users/getall").success(function(response) {
    			$scope.users = response;
    		});
    		$scope.newuser.id="";
        	$scope.newuser.name="";
        	$scope.newuser.password="";
    		alert(isNewUser?"User inserted successfully":"User updated successfully");
    	}).error(function(response, status, headers, config){
    		alert(response.message);
	    });
    };
    $scope.reset = function() {
    	$scope.newuser.id="";
    	$scope.newuser.name="";
    	$scope.newuser.password="";
    }
    $scope.deleteUser = function(index) {
    	var r = confirm("Are you sure that you want to remove?");
    	if (r){
	        $http.delete("/users/deleteuser/"+index).success(function(response) {
	        	$http.get("/users/getall").success(function(response) {
	    			$scope.users = response;
	    		});
	        	alert("User deleted successfully");
	    	}).error(function(response, status, headers, config){
	    		alert(response.message);
		    });
    	}
    };
	$http.get("/users/getall").success(function(response) {
		$scope.users = response;
	});
});
