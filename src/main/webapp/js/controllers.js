'use strict';

/* Controllers */

var smashBracketControllers = angular.module('smashBracketControllers', []);

smashBracketControllers.controller('UserRegistrationCtrl', ['$scope', '$http', '$location', 'AuthenticationService',
    function ($scope, $http, $location, AuthenticationService) {
        $scope.usersRegistered = [];

        //TODO can this be rewritten as a promise check the response instead of throwing more functions seems odd.
        $scope.loginUser = function () {
                        $scope.dataLoading = true;
                        AuthenticationService.Login($scope.userName, $scope.password, function (response) {
                                if (response.success) {
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
                url: 'http://127.0.0.1:8080/users?username=' + $scope.userName +
                        '&password=' + $scope.password +
                        '&email=' + $scope.email,
                data: {username: $scope.userName,
                    password: $scope.password,
                    email: $scope.email}
            };


            //Pop ups to explain rules on each box are super cool.
            $scope.dataLoading = true;
            $http(req).
                    success(function () {
                        AuthenticationService.SetCredentials($scope.userName, $scope.password);
                                                $location.path('/');
                    }).error(function (response) {
                $scope.error = response.message;
                                        $scope.dataLoading = false;
            });
        };
    }]);

smashBracketControllers.controller('TournamentBrowserCtrl', ['$scope', '$http', '$location', 'AuthenticationService',
    function ($scope, $http, $location, AuthenticationService) {
        $scope.tournamentListing = [];
        $scope.offset = 0;
        $scope.query = "";

        $scope.dataLoading = true;
        var getTournamentsRequest = {
            method: 'GET',
            url: 'http://127.0.0.1:8080/tournaments?offset=' + $scope.offset +
                    '&size=' + 10 +
                    '&query=' + $scope.query

        };

        $http(getTournamentsRequest).
                success(function (response) {
                    $scope.tournamentListing = response;
                    $scope.offset += 10;
                    $scope.dataLoading = false;
                }).error(function (response) {
            // How to handle error?
            $scope.dataLoading = false;
        });


        $scope.go = function (uuid) {
             $location.path('/tournaments/' + uuid);
        };
    }]);


smashBracketControllers.controller('TournamentCtrl', ['$scope', '$http', '$location', 'AuthenticationService', '$routeParams',
    function ($scope, $http, $location, AuthenticationService, $routeParams) {
        $scope.entrants = [];
        var tournamentUuid = $routeParams.uuid;
        var req = {
            method: 'GET',
            url: 'http://127.0.0.1:8080/tournaments/' + tournamentUuid + '/entrants'
        };

        $http(req).
                success(function (response) {
                    angular.forEach(response, function (entrant) {
                        $scope.entrants.push(entrant);
                    });
                    $scope.dataLoading = false;
                }).error(function (response) {
                    $scope.error = response.message;
                        $scope.dataLoading = false;
                });

                $scope.addEntrant = function () {
                    var addEntrantReq = {
                        method: 'POST',
                        url: 'http://127.0.0.1:8080/entrants?tournamentUuid=' + tournamentUuid
                                + '&tag=' + $scope.tag
                                + '&username=' + $scope.username
                                + '&password=' + $scope.password
                    };
                    $http(addEntrantReq).
                            success(function (response) {
                                $scope.entrants.push(response);
                                $scope.tag = null;
                                $scope.username = null;
                                $scope.password = null;
                                $scope.error = null;
                            }).error(function (response) {
                        $scope.error = response.message;
                    });
                };

                //add handling
            }]);

smashBracketControllers.controller('CreateTournamentCtrl', ['$scope', '$http', '$location', 'AuthenticationService',
    function ($scope, $http, $location, AuthenticationService) {

        //Todo how do I have this just inherit the base?
        //Pretty sure the answer is I don't and pass it in when creating the module.
        $scope.createTournament = function () {

            var req = {
                method: 'POST',
                url: 'http://127.0.0.1:8080/tournaments?name=' + $scope.name +
                        '&description=' + $scope.description +
                        '&maxEntrants=' + $scope.entrantLimit +
                        '&game=' + $scope.game +
                        '&tournamentType=' + $scope.tournamentType +
                        '&startTime=' + new Date($scope.startTime).toUTCString() +
                        '&hostId=1'
            };

            //Pop ups to explain rules on each box are super cool.
            $scope.dataLoading = true;
            $http(req).
                    success(function (response) {
                        $location.path('/tournaments/' + response.id);
                    }).error(function (response) {
                $scope.error = response.message;
                                        $scope.dataLoading = false;
            });
        };

    }]);