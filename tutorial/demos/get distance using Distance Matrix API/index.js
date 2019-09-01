// require('express') is the node.js (ES5) term of importing module(packages).
// Here 'express' means express.js server, which is a tiny node.js backend server I
// often use.
const express = require("express");
// Here 'path' is a module that calculates absolute path. express.js can only serve
// files with absolute path or specified root path(which doesn't make sense here).
const path = require("path");
// Here 'axios' is a module to handle http request
const axios = require("axios")
// Here API_KEY is imported from a private directory (unavailiable from Github)
const {API_KEY} = require('./credentials.json')
console.log(API_KEY?API_KEY:"cannot find API_KEY, please create a credentials.json file under current folder and add {\"API_KEY\":YOUR_KEY}")
// Now app is the express.js server object.
const app = express();

// Set port it listen to onto 3000.
// e.g. TomCat default port is 8080.
const port = 3000;

// Serve static distance_input.html on root endpoint
// __dirname is the directory of this folder(module)
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "/distance_input.html"));
});

app.get("/distance", (req, res) => {
    axios.get('https://maps.googleapis.com/maps/api/distancematrix/json',{
        params : {
            units : 'imperial',
            origins : req.query.origin,
            destinations : req.query.destination,
            key : API_KEY
        }
    })
    .then(response => {
        // API FORMAT:
        // {
        //     "destination_addresses" : [ "New York, NY, USA" ],
        //     "origin_addresses" : [ "Washington, DC, USA" ],
        //     "rows" : [
        //        {
        //           "elements" : [
        //              {
        //                 "distance" : {
        //                    "text" : "225 mi",
        //                    "value" : 361715
        //                 },
        //                 "duration" : {
        //                    "text" : "3 hours 49 mins",
        //                    "value" : 13725
        //                 },
        //                 "status" : "OK"
        //              }
        //           ]
        //        }
        //     ],
        //     "status" : "OK"
        //  }
        // TODO: comment on data
        res.send(response["data"]["rows"][0]["elements"][0]["distance"]["text"])

    }).
    catch(e => console.log(e))
});

// Start server.
app.listen(port, () => console.log(`Example app listening on port ${port}!`));
