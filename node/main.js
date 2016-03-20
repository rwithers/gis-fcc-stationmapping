var http = require("http");
var mongo = require('mongodb');
var db;
var express = require('express');
var app = express();
var path = require("path");

mongo.MongoClient.connect("mongodb://localhost:27017/test", function(err, database){
	if(err) throw err;
	console.log("connected to db");
	db = database;
	
	app.listen(3000, function (){ 
		console.log('now listening on port 3000');
	});
});

app.get('/', function(req, res){
	res.send('Hello World!');
});

app.get('/food', function(req, res){
	db.collection('food').find().toArray(function(err, items) {
		res.writeHead(200, {'Content-Type': 'text/plain'});
		res.end(JSON.stringify(items));
	})
});

app.get('/fm', function(req, res){
	db.collection('fm').find().toArray(function(err, items) {
		
		res.writeHead(200, {'Content-Type': 'text/plain'});
		res.end(JSON.stringify(items));
	})
});

app.get('/am', function(req, res){
	db.collection('am').find().toArray(function(err, items) {
		
		res.writeHead(200, {'Content-Type': 'text/plain'});
		res.end(JSON.stringify(items));
	})
});

app.get('/points', function(req, res) {
        var poly = JSON.parse(req.query.poly);

        var coordinates = [];

        for (var i = 0; i < poly.length; i++) {
          coordinates.push([poly[i].lng, poly[i].lat]);
        }

        coordinates.push([poly[0].lng, poly[0].lat]);

        var searchGeometry = { 
                                   location: { 
                                     $geoWithin : { 
                                     $geometry : {
                                         "type" : "Polygon",
                                         "coordinates" : [coordinates] 
                                     } 
                              }}}

        console.log(JSON.stringify(searchGeometry)); 

        db.collection('fm').find(searchGeometry).toArray(function(err, items) {
               console.log(items);
	       res.writeHead(200, {'Content-Type': 'text/plain'});
	       res.end(JSON.stringify(items));
        })
});

app.use(express.static('html'));

app.get('/rfplot', function(req, res) {
        res.sendFile(path.join(__dirname + '/html/rfplot/index.html'))
});

app.get('/map', function(req, res){
	res.sendFile(path.join(__dirname + '/html/map.html'))
});
