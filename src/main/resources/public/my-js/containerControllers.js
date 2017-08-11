'use strict';

/* Controllers */

dreamApp.controller('containerCtl', function($scope, $resource) {

	// var ContainerService = $resource( appContext + 'container/:id' );

	var ContainerService = $resource(appContext + 'container/:id', {
		id : '@_id'
	});

	var container = ContainerService.get({
		id : 2
	}, function() {
		$scope.container = container;
		console.log(JSON.stringify(container));
	});

	$scope.containers = new Array();
	var response = ContainerService.query(function() {
		$scope.containers = response.body;
		console.log(response);
	});

	// one way of the get method
	$scope.get1 = function(containerid) {
		console.log(JSON.stringify(containerid));
		var response = ContainerService.get({
			id : containerid
		}, function() {
			response.body.id = containerid;
			response.$save();
			console.log(JSON.stringify(response));
			$scope.container = response.body;
		});

	};
	// another way of the get method
	$scope.get = function(containerid) {
		$scope.imgcss = "sr-only "
		console.log(JSON.stringify(containerid));
		var container = ContainerService.get({
			id : containerid
		}).$promise.then(function(response) {
			console.log(JSON.stringify(response));
			$scope.container = response.body;
			
			if (response.header.errorCode == 0) {
				$scope.msgcss = "fade  "
			} else {
				$scope.msgcss = "alert alert-danger "
				$scope.message = response.header.message;
			}
		});
	};

	$scope.save = function(containerid) {
		console.log(JSON.stringify(containerid));
		$scope.imgcss = ""
		$scope.container = null;
		ContainerService.save({
			id : containerid
		}, $scope.container, function success(response) {
			console.log("Command executed:" + JSON.stringify(response));
			$scope.container = response.body;
			$scope.imgcss = "sr-only"
			if (response.header.errorCode == 0) {
				$scope.msgcss = "fade  "
			} else {
				$scope.msgcss = "alert alert-danger "
				$scope.message = response.header.message;
			}

			// $scope.containers.push(response.body);
		}, function error(errorResponse) {
			console.log("Error:" + JSON.stringify(errorResponse));
			$scope.msgcss = "alert alert-danger "
			$scope.message = JSON.stringify(errorResponse);
		});
	};

	$scope.remove = function(container) {
		var containerid = container.id;
		console.log(JSON.stringify(containerid));
		alert("You are going to remove :" + containerid);
		ContainerService.remove({
			id : containerid
		}, {}, function success(response) {
			console.log("Customer removed:" + JSON.stringify(containerid));
			var index = $scope.containers.indexOf(container);
			$scope.containers.splice(index, 1);

		}, function error(errorResponse) {
			alert("Connot connect to server.");
			console.log("Error:" + JSON.stringify(errorResponse));
		});
	};

	$scope.onEditClick = function(container) {
		console.log("Editting container:" + JSON.stringify(container));
		$scope.container = container;
	}

	$scope.onNewButtonClick = function() {
		$scope.container = {
			"id" : $scope.containers.length + 1,
			"birthday" : new Date()
		};
		console.log("Adding container:" + JSON.stringify($scope.container));
	}

});
