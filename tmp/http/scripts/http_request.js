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

console.log(new Date());
var d1 = new Date();
while (true) {
  const d2 = new Date();
  if (d2 - d1 > 5000) {
    break;
  }
}

        $scope.result = '!!通信に失敗しました!!';
      });

    };





    $scope.onclick5 = function() {

      console.log("onclick5");

    this.hello = function(id, $http) {

while(1){
console.log($http);      
console.log("hello", new Date());
      console.log("[" + id + "] " + "hello,<br />");
      Concurrent.Thread.sleep(id * 1000);

      console.log("[" + id + "] " + "hello,<br />");
}


    };

    this.hello2 = function(id) {

      this.mes = function() {
        console.log("3秒経ちました！");
        console.log(new Date());
        // setTimeout('mes()',1000);
      };

      console.log("hello2 start", new Date());

      var i = 0;
      while(1){
            console.log("[" + id + "] " + "hello,<br />");
            Concurrent.Thread.sleep(id * 1000);
      // Concurrent.Thread.yield();
      // setTimeout('mes()',1000)
            console.log("[" + id + "] " + "hello,<br />");

            i ++;
            if (i >= 10) {
              break;
            }
      }
      console.log("hello2 end", new Date());


    };


    this.hello3 = function(id) {

var d1 = new Date();
while (true) {
  // Concurrent.Thread.yield();
  var d2 = new Date();
  if (d2 - d1 > 5000) {
    break;
  }
}


    };


// var json = JSON.stringify($http, this.replacer());

    // Concurrent.Thread.create(this.hello, 1, $http);
    // Concurrent.Thread.create(this.hello, 2, $http);
    // Concurrent.Thread.create(this.hello, 3, $http);
    Concurrent.Thread.create(this.hello2, 1);
    Concurrent.Thread.create(this.hello3, 1);
    // Concurrent.Thread.create(hello3, 1);

console.log("start", new Date());

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
  // Concurrent.Thread.yield();
  var d2 = new Date();
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


    $scope.onclick6 = function() {
this.aaa = "aaa";
var thread = new Thread(function(a){
  console.log(a);
  console.log(a,this.$http);
  
  return a;
});

thread.once($http).done(function(d){
  console.log(d);    // -> 3
});

    };


    $scope.onclick7 = function() {

var num_threads = 2;
var MT = new Multithread(num_threads);      
var funcInADifferentThread = MT.process(
  function(a, b,http) { 
    console.log(http);
    return a + b; 
  },
  function(r) { console.log(r) }
);

funcInADifferentThread(1, 2, http);

    };




  }]);

  
function doubler(num) {
    // the return value becomes the resolve of the promise
    return num * 2;
}

function hello3(id) {

while(1){
console.log("hello2", new Date());
      console.log("[" + id + "] " + "hello,<br />");
      Concurrent.Thread.sleep(id * 1000);
      console.log("[" + id + "] " + "hello,<br />");
}

// function mes() {
//   console.log("3秒経ちました！");
//   console.log(new Date());
//   setTimeout('mes()',1000);
// }


    };
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
