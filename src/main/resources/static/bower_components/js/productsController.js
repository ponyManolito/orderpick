homeApp.controller('productsController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.add = function() {
        $scope.viewForm = !$scope.viewForm;
        if ($scope.viewForm){
        	$scope.icon = "glyphicon glyphicon-minus";
        }
        else{
        	$scope.icon = "glyphicon glyphicon-plus";
        }
    };
    $scope.addProduct = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewProduct = $scope.newproduct.id==""||$scope.newproduct.id=="0";
    	var fd = new FormData();
    	//fd.append('product', JSON.stringify($scope.newproduct));
    	fd.append('name', $scope.newproduct.name);
    	fd.append('description', $scope.newproduct.description);
		fd.append('image', $scope.newproduct.image?$scope.newproduct.image[0]:null);	
    	fd.append('movie', $scope.newproduct.movie?$scope.newproduct.movie[0]:null);
		fd.append('empty', $scope.newproduct.empty?true:false );
    	/*
    	alert($scope.newproduct.name)*/
        $http.post("/products/addproduct",fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).success(function(response) {
    		$http.get("/products/getall").success(function(response) {
    			$scope.products = response;
    		});
    		$scope.newproduct.id="";
        	$scope.newproduct.name="";
        	$scope.newproduct.description="";
    		alert(isNewProduct?"Product inserted successfully":"Product updated successfully");
    	}).error(function(response, status, headers, config){
    		alert(response.message);
	    });
    };
});