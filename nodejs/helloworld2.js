// node C:\Local\Dev\node\helloworld2.js

var http = require('http');

var i = 0;

var helloworld2 = function (req, res) {
  i++;
  console.log('serving request ' + i);
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello World ' + i + '\n');
};

http.createServer(helloworld2).listen(1337);

console.log('Server running at http://127.0.0.1:1337/');
