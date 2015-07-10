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

smashBracketApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/SmashBracket', {
        templateUrl: 'partials/home.html'
        //controller: 'HomeCtrl'
      }).
      when('/SmashBracket/register', {
        templateUrl: 'partials/user-registration.html',
        controller: 'UserRegistrationCtrl'
      }).
      otherwise({
        redirectTo: '/SmashBracket'
      });
  }]);
