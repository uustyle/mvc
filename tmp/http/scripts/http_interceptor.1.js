angular.module('myApp', [])
  .config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push(
      [ '$q', '$log', '$window', 'SharedService', function ($q, $log, $window, SharedService) {
        return {
          'request': function(config) {

console.log('SharedService', SharedService);
            config.startTime = (new Date()).getTime();
            $log.info('request...');
            $log.info(config);
            return config;
          },
          'requestError': function(rejection) {
            $log.info('requestError...');
            $log.info(rejection);
            return $q.reject(rejection);
          },
          'response': function(response) {
            response.config.endTime = (new Date()).getTime();
            $log.info('Process Time(sec): ' + (response.config.endTime - response.config.startTime) / 1000);

            $log.info('response...');
            $log.info(response);
            return response;
          },
          'responseError': function(rejection) {
            if (rejection.status === 500) {
              $window.alert('$http service failed !');
              location.href = 'top.html';
            }
            $log.info('responseError...');
            $log.info(rejection);
            return $q.reject(rejection);
          }
        };
      }]);
  }])

  .service('SharedService', ['$log', function($log) {
    this.name = '権兵衛';
    this.getMessage = function() {
      return 'こんにちは、' + this.name + 'さん！';
    }
  }])
  
  .controller('MyController', ['$scope', '$http', function($scope, $http) {
    $scope.onclick = function() {
      $http({
        method: 'POST',
        url: 'http_interceptor.php',
        data: { name: $scope.name }
      })
      .success(function(data, status, headers, config){
        $scope.result = data;
      })
      .error(function(data, status, headers, config){
        $scope.result = '!!通信に失敗しました!!';
      });
    };
  }]);
