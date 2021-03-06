homeApp.controller('userController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.messageSuccess = "";
	$scope.messageError = "";
	$scope.settings = {selectionLimit: 1};
	$scope.alltypes = [];
	$scope.selectedModel = [];
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
        $http.get("/orderserver/users/getuser?id="+index).success(function(response) {
    		$scope.newuser = response;
    		$scope.selectedModel={id:$scope.newuser.profile};
    		$scope.messageSuccess = "";
    		$scope.messageError = "";
    	});
    };
    $scope.addUser = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewUser = $scope.newuser.id==""||$scope.newuser.id=="0";
    	$scope.duplicatedError ="";
    	$scope.profileError ="";
    	if ($scope.selectedModel==null ||($scope.selectedModel!=null 
    			&&$scope.selectedModel.id =="")){
    		$scope.profileError = "yes";
    	}
    	$scope.newuser.profile = $scope.selectedModel.id;
    	var name =$scope.newuser.name+"";
    	$http.get("/orderserver/users/getusername?name="+name).success(function(response) {
    		$scope.duplicatedError = response.id==true?"Error":"";
    	});
    	if ($scope.duplicatedError =="" && $scope.profileError ==""){
	        $http.post("/orderserver/users/adduser",$scope.newuser).success(function(response) {
	    		$http.get("/orderserver/users/getall").success(function(response) {
	    			$scope.users = response;
	    		});
	    		$scope.newuser.id="";
	        	$scope.newuser.name="";
	        	$scope.newuser.password="";
	        	$scope.messageSuccess = 'true';
	        	$scope.messageError = "";
	    	}).error(function(response, status, headers, config){
	    		alert(response.message);
	    		$scope.messageSuccess = "";
	        	$scope.messageError = "true";
		    });
    	}
    };
    $scope.reset = function() {
    	$scope.newuser.id="";
    	$scope.newuser.name="";
    	$scope.newuser.password="";
    	$scope.messageSuccess = "";
		$scope.messageError = "";
    }
    $scope.deleteUser = function(index) {
        $http.delete("/orderserver/users/deleteuser/"+index).success(function(response) {
        	$http.get("/orderserver/users/getall").success(function(response) {
    			$scope.users = response;
    		});
        	$scope.messageSuccess = "true";
    		$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
	$http.get("/orderserver/users/getall").success(function(response) {
		$scope.users = response;
		$scope.messageSuccess = "";
		$scope.messageError = "";
		$scope.alltypes[0] = {id:"ROLE_ADMIN", label:"Admin"};
		$scope.alltypes[1] = {id:"ROLE_WORKER", label:"Camarero"};
	});
});

function userPagController($scope) {
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
}
homeApp.controller('userPagController', userPagController);
