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

	var containers = ContainerService.query(function() {
		$scope.containers = containers;
		console.log(containers);
	});

	// one way of the get method
	$scope.get1 = function(containerid) {
		console.log(JSON.stringify(containerid));
		var container = ContainerService.get({
			id : containerid
		}, function() {
			container.id = containerid;
			container.$save();
			console.log(JSON.stringify(container));
			$scope.container = container;
		});

	};
	// another way of the get method
	$scope.get = function(containerid) {
		console.log(JSON.stringify(containerid));
		var container = ContainerService.get({
			id : containerid
		}).$promise.then(function(container) {
			console.log(JSON.stringify(container));
			$scope.container = container;
		});
	};

	$scope.save = function(containerid) {
		$scope.container.id = containerid;
		console.log(JSON.stringify($scope.container));
		ContainerService.save({
			id : containerid
		}, $scope.container, function success(response) {
			console.log("Customer saved:" + JSON.stringify(response));
			$scope.containers.push(response);
		}, function error(errorResponse) {
			alert("Connot connect to server.");
			console.log("Error:" + JSON.stringify(errorResponse));
		});
	};

	$scope.update = function(containerid) {
		console.log(JSON.stringify(containerid));
		ContainerService.update({
			id : containerid
		}, $scope.container, function success(response) {
			console.log("Customer updated:" + JSON.stringify(containerid));

		}, function error(errorResponse) {
			alert("Connot connect to server.");
			console.log("Error:" + JSON.stringify(errorResponse));
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
