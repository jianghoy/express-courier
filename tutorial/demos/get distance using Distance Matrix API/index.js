// require('express') is the node.js (ES5) term of importing module(packages).
// Here 'express' means express.js server, which is a tiny node.js backend server I 
// often use.
const express = require('express')
// Here 'path' is a module that calculates absolute path. express.js can only serve
// files with absolute path or specified root path(which doesn't make sense here).
const path = require('path')

// Now app is the express.js server object.
const app = express()

// Set port it listen to onto 3000.
// e.g. TomCat default port is 8080.
const port = 3000

// Serve static distance_input.html on root endpoint
// __dirname is the directory of this folder(module)
app.get('/',(req,res) => {
    res.sendFile(path.join(__dirname,'/distance_input.html'))
})

// Start server.
app.listen(port, () => console.log(`Example app listening on port ${port}!`))