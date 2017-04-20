
importScripts("./config.js");

self.obj = {};

self.onmessage = function(event) {

console.log("httptmp", httptmp);
    var AA = event.data; // event.data
    //ここに処理を記述する

    if (event.data == "start") {
        var results = AA;
        postMessage({"ret":"0","data":self.obj});
    }
console.log(self.obj);

}

