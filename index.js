// Libraries
import {NativeModules} from 'react-native';

// Native Modules
const { RNCloudinary } = NativeModules;


class Cloudinary {

  // --------------------------------------------------
  // Initialize
  // --------------------------------------------------
  config(cloudName, apiKey, apiSecret, presetName) {
    return RNCloudinary.config(cloudName, apiKey, apiSecret, presetName);
  }

  uploadImage(uri) {
    return RNCloudinary.uploadImage(uri)
  }

}


export default new Cloudinary();
