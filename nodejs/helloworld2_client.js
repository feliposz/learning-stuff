// node C:\Local\Dev\node\helloworld2_client.js

var http = require('http');

var options = {
    hostname: '127.0.0.1', 
    port: 1337, 
    path: '/', 
    method: 'GET'
}

var handler = function (res) {
    //console.dir(res);
}

console.log('Request loop');
for (var i = 1; i < 3; i++) {
    console.log('Request #' + i);
    http.get('http://127.0.0.1:1337', handler);
}

console.log(__dirname);
console.log(__filename);
