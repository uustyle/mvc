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

this.addPane = function() {
  };

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


    $scope.onclick3 = function() {
      console.log("onclick3");

console.log("httptmp", httptmp);

httptmp = $http;
console.log("httptmp", httptmp);

var worker = new Worker("scripts/worker_test.js");
worker.onmessage = function(event) {//ワーカーから受け取り
    var BB = event.data;
    console.log("BB", BB);
    BB.obj = this.addPane;
    console.log(this);
    worker.postMessage("run");



}
worker.postMessage("start");
var d1 = new Date();
while (true) {
  const d2 = new Date();
  if (d2 - d1 > 5000) {
    break;
  }
}


    };


    $scope.onclick4 = function() {
      console.log("onclick4");

console.log(new Date());

var worker = new Worker("scripts/worker_test2.js");
worker.onmessage = function(event) {//ワーカーから受け取り
    var BB = event.data;
    console.log("BB", BB);
console.log(new Date());  

}
worker.postMessage("start");


      $http({
        method: 'POST',
        url: 'http_request.php',
        data: { name: $scope.name }
      })
      .success(function(data, status, headers, config){
console.log("success");
console.log(new Date());
var d1 = new Date();
while (true) {
  const d2 = new Date();
  if (d2 - d1 > 5000) {
    break;
  }
}
        $scope.result = data;
      })
      .error(function(data, status, headers, config){
console.log("error");
        $scope.result = '!!通信に失敗しました!!';
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
