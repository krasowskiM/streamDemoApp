var testApp = angular.module('testApp', []);

testApp.controller('mainController', function($scope, $http){
    $http.get('/getResponse')
    .then(function(response){
        $scope.defaultResponse = response.data;
    });
});