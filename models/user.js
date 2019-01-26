var mongoose = require('mongoose');

var userSchema = mongoose.Schema({
  _id: mongoose.Types.ObjectId,
  first_name: String,
  last_name: String,
  password: String,
  email: String,
  phone: String,
  address: String
});

module.exports = {
  "model": mongoose.model('User',userSchema),
  "schema": userSchema
}
