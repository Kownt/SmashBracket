'use strict';

/* Controllers */

var smashBracketControllers = angular.module('smashBracketControllers', []);

smashBracketControllers.controller('UserRegistrationCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.usersRegistered = [];

        //Todo how do I have this just inherit the base?
        $scope.registerUser = function (clickEvent) {
            $http.post('http://127.0.0.1:8080/users?userName=' + $scope.userName).
                    success(function (data) {
                        $scope.usersRegistered.push(data);
                    });
        };
    }]);


