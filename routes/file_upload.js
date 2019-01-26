const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const User = require('../models/user').model;
const mongoose = require('mongoose');
// const formidable = require('formidable');
const fs = require('fs');
var multer = require('multer');
// var upload = multer({dest:'images/'});
router.use(bodyParser.json());
var current_file_name = "";
var storage = multer.diskStorage({
    destination: (req, file, cb) => {
      cb(null, 'images/')
    },
    filename: (req, file, cb) => {
        current_file_name = file.originalname.split('.')[0] +'.'+file.originalname.split('.')[1];   
        cb(null, file.originalname.split('.')[0] +'.'+file.originalname.split('.')[1]);
    }
});
var upload = multer({storage: storage});
var PythonShell = require('python-shell');

router.post("/", upload.single('avatar') ,(req, res, next) => {
    console.log(req.file);
    fs.rename('images/'+current_file_name,'images/test.'+current_file_name.split('.')[1],(err)=>{

    });
    res.statusCode = 200;
    res.setHeader('Content-type', 'application/json');
    res.json({status:"success"});

    PythonShell.run('../python/test.py', options, function (err, data) {
        if (err) res.send(err);
        res.send(data.toString())
});
});


module.exports = router;