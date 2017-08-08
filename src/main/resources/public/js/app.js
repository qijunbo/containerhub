'use strict';

var appContext = "./" ;

/* App Module */

var dreamApp = angular.module('dreamApp', [
    'ngRoute',
    'ngResource'
]);


dreamApp.config(['$routeProvider', '$locationProvider',   
    function($routeProvider, $locationProvider) {

        $routeProvider.
                when('/ionic', {
                    templateUrl:'partials/ionic.html',
                    controller: 'getChargePointController'
                }).when('/', {
                    templateUrl: 'partials/customers.html',
                    controller: 'costomerCtl'
                }).when('/save/:id', {
                    templateUrl: 'partials/notify.html',
                    controller: 'saveChargePointController'
                });

        $locationProvider.html5Mode(false).hashPrefix('!');
    } ]);

 
