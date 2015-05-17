homeApp.controller('productsController', function($scope, $http) {
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
    $scope.loadProduct = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/productss/getproduct?id="+index).success(function(response) {
    		$scope.newproduct = response;
    	});
    };
    $scope.addProduct = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewProduct = $scope.newproduct.id==""||$scope.newproduct.id=="0";
    	var fd = new FormData();
    	fd.append('name', $scope.newproduct.name);
    	fd.append('price', $scope.newproduct.price);
    	fd.append('description', $scope.newproduct.description);
		fd.append('image', $scope.newproduct.image?$scope.newproduct.image[0]:null);	
    	fd.append('movie', $scope.newproduct.movie?$scope.newproduct.movie[0]:null);
		fd.append('empty', $scope.newproduct.empty?true:false );
        $http.post("/products/addproduct",fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).success(function(response) {
    		$http.get("/products/getall").success(function(response) {
    			$scope.products = response;
    		});
    		$scope.newproduct.id="";
        	$scope.newproduct.name="";
        	$scope.newproduct.price="";
        	$scope.newproduct.description="";
        	$scope.newproduct.image=null;
        	$scope.newproduct.movie=null;
    		alert(isNewProduct?"Product inserted successfully":"Product updated successfully");
    	}).error(function(response, status, headers, config){
    		alert(response.message);
	    });
    };
    $scope.reset = function() {
    	$scope.newproduct.id="";
    	$scope.newproduct.name="";
    	$scope.newproduct.price="";
    	$scope.newproduct.description="";
    	$scope.newproduct.image=null;
    	$scope.newproduct.movie=null;
    };
    $scope.deleteProduct = function(index) {
    	var r = confirm("Are you sure that you want to remove?");
    	if (r){
	        $http.delete("/products/deleteproduct/"+index).success(function(response) {
	        	$http.get("/products/getall").success(function(response) {
	    			$scope.products = response;
	    		});
	        	alert("Product deleted successfully");
	    	}).error(function(response, status, headers, config){
	    		alert(response.message);
		    });
    	}
    };
    $http.get("/products/getall").success(function(response) {
		$scope.products = response;
	});
});