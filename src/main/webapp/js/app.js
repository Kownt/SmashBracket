'use strict';

/* App Module */

var smashBracketApp = angular.module('SmashBracketApp', [
  'ngRoute',
  'smashBracketAnimations',
  'smashBracketControllers',
  'smashBracketFilters',
  'smashBracketServices',
  'smashBracketDirectives'
]);

smashBracketApp.config(['$routeProvider', '$locationProvider',
  function($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
    $routeProvider.
      when('/', {
        templateUrl: 'partials/home.html'
        //controller: 'HomeCtrl'
      }).
      when('/register', {
        templateUrl: 'partials/user-registration.html',
        controller: 'UserRegistrationCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);
