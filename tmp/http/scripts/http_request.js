angular.module('myApp', ['ngWebworker'])
  .config(['$httpProvider',function($httpProvider) {
    // WebworkerProvider.setHelperPath("./worker_wrapper.js");

    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.defaults.transformRequest.push(function(data) {
      data = JSON.parse(data);
      var query = [];
      for(var key in data) {
        query.push(encodeURIComponent(key) + "="
          + encodeURIComponent(data[key]));
      };
      return query.join("&");
    })
  }])
//   .config(function(WebworkerProvider) {
//     console.log("worker_wrapper");
//     WebworkerProvider.setHelperPath("./worker_wrapper.js");
//     //WebworkerProvider.setUseHelper(true);
// })



  .controller('MyController', ['$scope', '$http', 'Webworker',function($scope, $http, Webworker) {
    $scope.onclick = function() {
      console.log("onclick1");
      
      $http({
        method: 'POST',
        url: 'http_request.php',
        data: { name: $scope.name }
      })
      .success(function(data, status, headers, config){
        $scope.result = data;
      })
      .error(function(data, status, headers, config){
        $scope.result = '!!通信に失敗しました!!';
      });
    };



    $scope.onclick2 = function() {
      console.log("onclick2");
var myWorker = Webworker.create(doubler);
myWorker.run($scope.value).then(function(result) {
    alert("Answer: " + result);
});

    };


  }]);

  
function doubler(num) {
    // the return value becomes the resolve of the promise
    return num * 2;
}

/*
  .controller('MyController',
    ['$scope', '$http', '$httpParamSerializerJQLike',
    function($scope, $http, $httpParamSerializerJQLike) {
    $scope.onclick = function() {
      $http({
        method: 'POST',
        headers: {
          'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
        },
        transformRequest: $httpParamSerializerJQLike,
        url: 'http_request.php',
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
*/
