'use strict';

/* App Module */

angular.module('smashBracketServices', []);
var smashBracketApp = angular.module('SmashBracketApp', [
    'ngRoute',
    'ngCookies',
    'smashBracketAnimations',
    'smashBracketControllers',
    'smashBracketFilters',
    'smashBracketServices',
    'smashBracketDirectives'
]);

smashBracketApp.config(['$routeProvider', '$locationProvider', 
    function ($routeProvider, $locationProvider) {
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
                when('/login', {
                    templateUrl: 'partials/user-login.html',
                    controller: 'UserRegistrationCtrl'
                }).
                when('/browse', {
                    templateUrl: 'partials/browse-tournaments.html',
                    controller: 'TournamentBrowserCtrl'
                }).
                when('/tournaments/:uuid', {
                    templateUrl: 'partials/tournaments.html',
                    controller: 'TournamentCtrl'
                }).
                when('/create', {
                    templateUrl: 'partials/create-tournament.html',
                    controller: 'CreateTournamentCtrl'
                }).
                otherwise({
                    redirectTo: '/'
                });
    }]).run(['$rootScope', '$location', '$cookies', '$http',
    function ($rootScope, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.globals || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
        }
    }]);
