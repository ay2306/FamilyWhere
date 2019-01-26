import numpy as np
import PIL
from PIL import Image
from keras.models import load_model
import dlib
from skimage import io
import pickle

def triplet_loss(y_true, y_pred, alpha = 0.2):
    anchor, positive, negative = y_pred[0], y_pred[1], y_pred[2]
    pos_dist = tf.reduce_sum(tf.square(anchor - positive), axis=-1)
    neg_dist = tf.reduce_sum(tf.square(anchor - negative), axis=-1)
    basic_loss = pos_dist - neg_dist + alpha
    loss = tf.reduce_sum(tf.maximum(basic_loss, 0.0))    
    return loss

FRmodel = load_model('../python/FRmodel.h5')
database = pickle.load('../python/database.pkl')

def test_image(filename):
    image = io.imread(filename)
    face_detector = dlib.get_frontal_face_detector()
    detected_faces = face_detector(image, 1)
    face_frames = [(x.left(), x.top(), x.right(), x.bottom()) for x in detected_faces]
    if len(face_frames) != 0:
        face_rect = face_frames[0]
        face = Image.fromarray(image).crop(face_rect)
        img = face.resize((96,96), Image.ANTIALIAS)
        img.save(filename)
    else:
        img = Image.open(filename)
        img = img.resize((96,96), Image.ANTIALIAS)
        img.save(filename)

def who_is_it(image_path, database, model):
    encoding = img_to_encoding(image_path, model)
    min_dist = 100
    
    distances = []
    for (name, db_enc) in database.items():
        dist = np.linalg.norm(encoding - db_enc)
        distances.append([dist, name])
        if dist < min_dist:
            min_dist = dist
            identity = name
    distances.sort()
    predictions ={}
    for i in distances:
        predictions[str(i+1)] = distances[i][1]
    print("Here are top 3 guesses:")
    # for i in range(4):
    #     print("Guess " + str(i+1) + ": " + distances[i][1] + " (Confidence: " + str(round((1-distances[i][0])* 100,2)) + "%)")
        #print ("it's " + str(identity) + ", the distance is " + str(min_dist))
    #print(distances)    
    return jsonify(predictions)         

def identify(filename):
    test_image(filename)
    who_is_it(filename, database, FRmodel)