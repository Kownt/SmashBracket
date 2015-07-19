'use strict';

/* Controllers */

var smashBracketControllers = angular.module('smashBracketControllers', []);

smashBracketControllers.controller('UserRegistrationCtrl', ['$scope', '$http', '$location', 'AuthenticationService', 
    function ($scope, $http, $location, AuthenticationService) {
        $scope.usersRegistered = [];

        //TODO can this be rewritten as a promise check the response instead of throwing more functions seems odd.
        $scope.loginUser = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.userName, $scope.password, function(response) {
                if(response.success) {
                    AuthenticationService.SetCredentials($scope.userName, $scope.password);
                    $location.path('/');
                } else {
                    $scope.error = response.message;
                    $scope.dataLoading = false;
                }
            });
        
        };
        
        //Todo how do I have this just inherit the base?
        //Pretty sure the answer is I don't and pass it in when creating the module.
        $scope.registerUser = function () {
            
            
            //Do username validation checking here and throw errors back to them if it makes sense.
            //Only after this validation is done should you bother posting 
            
            //Username should check list of usernames to see if its valid
            //Password should check rules after its typed.
            //email address should enforce . @
            //Need a terms of service checkbox.
            
            var req = {
                method: 'POST',
                url: 'http://127.0.0.1:8080/users',
                data: {userName: $scope.userName,
                       password: $scope.password,
                       email: $scope.email}
            };
            
            
            //Pop ups to explain rules on each box are super cool.
            $scope.dataLoading = true;
            $http.post(req).
                    success(function () {
                        AuthenticationService.SetCredentials($scope.userName, $scope.password);
                        $location.path('/');
                    }).error(function(response){
                        $scope.error = response.message;
                        $scope.dataLoading = false;
                    });
        };
    }]);


