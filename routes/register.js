const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const User = require('../models/user').model;
const mongoose = require('mongoose');
router.use(bodyParser.json());


router.get("/", (req, res, next) => {
    const user = new User({
        _id: new mongoose.Types.ObjectId,
        first_name: req.query['first_name'],
        last_name: req.query['last_name'],
        password: req.query['password'],
        email: req.query['email'],
        phone: req.query['phone']
    });
    user.save()
        .then(Result => {
            res.statusCode = 200;
            res.setHeader('Content-type', 'application/json');
            res.json({
                status: "Success",
            });
        })
        .catch(err => {
            res.statusCode = 500;
            res.setHeader('Content-type', 'application/json');
            res.json({
                status: "fail",
            });
        });
});


router.get("/entity", (req, res, next) => {
    const user = new User({
        _id: new mongoose.Types.ObjectId,
        first_name: req.query['first_name'],
        last_name: req.query['last_name'],
        image_name: "",
        parent_id: req.query['parent_id']
    });
    user.save()
        .then(Result => {
            res.statusCode = 200;
            res.setHeader('Content-type', 'application/json');
            res.json({
                status: "Success",
                created_id: Result._id
            });
        })
        .catch(err => {
            res.statusCode = 500;
            res.setHeader('Content-type', 'application/json');
            res.json({
                status: "fail"
            });
        });
    });
    
    router.get('/getAll', (req, res, next) => {
        User.find({}, (err, Result) => {
            if (err) {
                res.statusCode = 500;
                res.setHeader('Content-type', 'application/json');
                res.json({
                    status: "fail",
                });
            }else{
                res.statusCode = 200;
                res.setHeader('Content-type', 'application/json');
                res.json({
                    status: "Success",
                    result: Result
                });
            }
        });
    });

module.exports = router;