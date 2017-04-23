self.obj = {};

self.onmessage = function(event) {
//     var AA = event.data; // event.data
//     //ここに処理を記述する

//     if (event.data == "start") {
//         var results = AA;
//         postMessage({"ret":"0","data":self.obj});
//     }
// console.log(self.obj);

    setTimeout('mes()',1000)

}

self.mes = function() {
  console.log("3秒経ちました！");
  console.log(new Date());
  setTimeout('self.mes()',1000);
  postMessage({"ret":"0","data":self.obj});
}
