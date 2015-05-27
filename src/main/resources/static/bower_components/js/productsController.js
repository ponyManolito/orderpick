homeApp.controller('productsController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.messageSuccess = "";
	$scope.messageError = "";
	$scope.showImageName = "";
	$scope.$watch('newproduct.image', function () {
		if ($scope.newproduct.image && $scope.newproduct.image[0]){
			var reader  = new FileReader();
			$scope.showImage = "";
			$scope.showImageName = "";
			reader.onloadend = function () {
				var preview = document.querySelector('img');
				preview.src = reader.result;
			}
        	reader.readAsDataURL($scope.newproduct.image[0]);
		}
    });
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
    $scope.loadProduct = function(index) {
    	$scope.viewForm = true;
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/products/getproduct?id="+index).success(function(response) {
    		$scope.aux = response;
    		$scope.newproduct.id=response.id;
        	$scope.newproduct.name=response.name;
        	$scope.newproduct.price=response.price;
        	$scope.newproduct.description=response.description;
    		if (response.imageData){
    			var preview = document.getElementById('loadImage');
    			preview.src = response.imageData;
    			$scope.showImageName = response.imageName;
    		}
    		$scope.messageSuccess = "";
    		$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		alert(response.message)
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
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
        	$scope.messageSuccess = "true";
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
    $scope.reset = function() {
    	$scope.newproduct.id="";
    	$scope.newproduct.name="";
    	$scope.newproduct.price="";
    	$scope.newproduct.description="";
    	$scope.newproduct.image=null;
    	$scope.newproduct.movie=null;
    	$scope.messageSuccess = "";
    	$scope.messageError = "";
    };
    $scope.deleteProduct = function(index) {
        $http.delete("/products/deleteproduct/"+index).success(function(response) {
        	$http.get("/products/getall").success(function(response) {
    			$scope.products = response;
    		});
        	$scope.messageSuccess = "true";
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
    $http.get("/products/getall").success(function(response) {
		$scope.products = response;
		$scope.messageSuccess = "";
		$scope.messageError = "";
	});
});