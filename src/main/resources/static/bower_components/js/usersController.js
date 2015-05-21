homeApp.controller('userController', function($scope, $http) {
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
    $scope.loadUser = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/users/getuser?id="+index).success(function(response) {
    		$scope.newuser = response;
    		$scope.messageSuccess = "";
    		$scope.messageError = "";
    	});
    };
    $scope.addUser = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewUser = $scope.newuser.id==""||$scope.newuser.id=="0";
        $http.post("/users/adduser",$scope.newuser).success(function(response) {
    		$http.get("/users/getall").success(function(response) {
    			$scope.users = response;
    		});
    		$scope.newuser.id="";
        	$scope.newuser.name="";
        	$scope.newuser.password="";
        	$scope.messageSuccess = '<span class="alert alert-success" role="alert" ng-show="messageSuccess" th:text="';
        	$scope.messageSuccess += isNewUser?"#{users.inserted.success}":"#{users.updated.success}";
        	$scope.messageSuccess += '"/>';
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		alert(response.message);
    		$scope.messageSuccess = "";
        	$scope.messageError = isNewUser?"#{users.inserted.error}":"#{users.updated.error}";
	    });
    };
    $scope.reset = function() {
    	$scope.newuser.id="";
    	$scope.newuser.name="";
    	$scope.newuser.password="";
    	$scope.messageSuccess = "";
		$scope.messageError = "";
    }
    $scope.deleteUser = function(index) {
        $http.delete("/users/deleteuser/"+index).success(function(response) {
        	$http.get("/users/getall").success(function(response) {
    			$scope.users = response;
    		});
        	$scope.messageSuccess = "#{users.deleted.success}";
    		$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "#{users.deleted.error}";
	    });
    };
	$http.get("/users/getall").success(function(response) {
		$scope.users = response;
		$scope.messageSuccess = "";
		$scope.messageError = "";
	});
});
