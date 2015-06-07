homeApp.controller('productsController', function($scope, $http) {
	$scope.viewForm = false;
	$scope.icon = "glyphicon glyphicon-plus";
	$scope.currentPage = 1;
	$scope.pageSize = 5;
	$scope.messageSuccess = "";
	$scope.messageError = "";
	$scope.showImageName = "";
	$scope.selectedModel = [];
	$scope.settings = {enableSearch: true};
	$scope.$watch('newproduct.image', function () {
		if ($scope.newproduct && $scope.newproduct.image && $scope.newproduct.image[0]){
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
    	$scope.showImageName = "";
    	$scope.icon = "glyphicon glyphicon-minus";
        $http.get("/products/getproduct?id="+index).success(function(response) {
        	$scope.newproduct = response;
    		if (response.imageData){
    			var preview = document.getElementById('loadImage');
    			preview.src = response.imageData;
    			$scope.showImageName = response.imageName;
    		}
    		$scope.selectedModel = [];
    		if ($scope.newproduct.types && $scope.newproduct.types.length>0){
        		for (i in $scope.newproduct.types){
        			$scope.selectedModel[i]={id:$scope.newproduct.types[i]};
    			}
        	}
    		$scope.messageSuccess = "";
    		$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
    $scope.addProduct = function() {
    	$scope.viewForm = false;
    	$scope.icon = "glyphicon glyphicon-plus";
    	var isNewProduct = $scope.newproduct.id==""||$scope.newproduct.id=="0"||$scope.newproduct.id=== undefined;
    	var fd = new FormData();
    	fd.append('id', isNewProduct?"":$scope.newproduct.id);
    	fd.append('name', $scope.newproduct.name);
    	fd.append('price', $scope.newproduct.price);
    	fd.append('description', $scope.newproduct.description);
    	if ($scope.selectedModel && $scope.selectedModel.length>0){
    		var types = new Array();
    		for (i in $scope.selectedModel){
				types[i] = $scope.selectedModel[i].id;
			}
    		fd.append('types', types);
    	}
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
        	$scope.showImageName = "";
        	$scope.messageSuccess = "true";
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		alert(response.message)
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
    $scope.reset = function() {
    	$scope.newproduct.id="";
    	$scope.newproduct.name="";
    	$scope.newproduct.price="";
    	$scope.newproduct.description="";
    	$scope.showImageName = "";
    	$scope.newproduct.image=null;
    	$scope.newproduct.movie=null;
    	$scope.selectedModel = [];
    	$scope.messageSuccess = "";
    	$scope.messageError = "";
    };
    $scope.deleteProduct = function(index) {
        $http.delete("/products/deleteproduct/"+index).success(function(response) {
        	$http.get("/products/getall").success(function(response) {
    			$scope.products = response;
    		});
        	$scope.messageSuccess = "true";
        	$scope.showImageName = "";
        	$scope.messageError = "";
    	}).error(function(response, status, headers, config){
    		$scope.messageSuccess = "";
    		$scope.messageError = "true";
	    });
    };
    $http.get("/products/getall").success(function(response) {
		$scope.products = response;
		$scope.alltypes = [];
		$http.get("/types/getall").success(function(response) {
			for (i in response){
				var type = {id:response[i].id, label:response[i].name};
				$scope.alltypes[i] = type;
			}
			$scope.messageSuccess = "";
			$scope.showImageName = "";
			$scope.messageError = "";
		}).error(function(response, status, headers, config){
			$scope.messageSuccess = "";
			$scope.messageError = "true";
	    });
	});
});