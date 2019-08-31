// require('express') is the node.js (ES5) term of importing module(packages).
// Here 'express' means express.js server, which is a tiny node.js backend server I 
// often use.
const express = require('express')

// Now app is the express.js server object.
const app = express()

// Set port it listen to onto 3000.
// e.g. TomCat default port is 8080.
const port = 3000