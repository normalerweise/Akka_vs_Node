var cluster = require('cluster');
var http = require('http');
var numCPUs = require('os').cpus().length;

if (cluster.isMaster) {
  // Fork workers.
  for (var i = 0; i < numCPUs; i++) {
    cluster.fork();
  }

  cluster.on('exit', function(worker, code, signal) {
    console.log('worker ' + worker.process.pid + ' died');
  });
} else {
	http.createServer(function (req, res) {
		res.writeHead(200, {
			'Content-Type': 'text/html'
		});
		res.end("<html> \
	               <body> \
	                 <h1>Hello World! I'm node.js :-)</h1> \
	                </body> \
	              </html>");
	}).listen(9091);
}

console.log('Server running at http://127.0.0.1:9091/');
