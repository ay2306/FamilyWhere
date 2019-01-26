var mongoose = require('mongoose');

var entitySchema = mongoose.Schema({
  _id: mongoose.Types.ObjectId,
  first_name: String,
  last_name: String,
  image_name: String,
  parent_id: String
});

module.exports = {
  "model": mongoose.model('Entity',entitySchema),
  "schema": entitySchema
}
