
package com.rncloudinary;

import android.net.Uri;
import android.util.Log;

import com.cloudinary.ProgressCallback;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.cloudinary.Cloudinary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RNCloudinaryModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private Cloudinary mCloudinary = null;
  private String mPresetName = "";
  private Map mConfig = null;
  private boolean isResolved = false;

  public RNCloudinaryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public static WritableMap toWritableMap(Map<String, Object> map) {
    WritableMap writableMap = Arguments.createMap();
    Iterator iterator = map.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry pair = (Map.Entry) iterator.next();
      Object value = pair.getValue();

      if (value == null) {
        writableMap.putNull((String) pair.getKey());
      } else if (value instanceof Boolean) {
        writableMap.putBoolean((String) pair.getKey(), (Boolean) value);
      } else if (value instanceof Double) {
        writableMap.putDouble((String) pair.getKey(), (Double) value);
      } else if (value instanceof Integer) {
        writableMap.putInt((String) pair.getKey(), (Integer) value);
      } else if (value instanceof String) {
        writableMap.putString((String) pair.getKey(), (String) value);
      }

      iterator.remove();
    }

    return writableMap;
  }

  @Override
  public String getName() {
    return "RNCloudinary";
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void config(String cloudName, String apiKey, String secretKey, String presetName) {
    Map config = new HashMap();
    config.put("cloud_name", cloudName);
    config.put("api_key", apiKey);
    config.put("api_secret", secretKey);
    this.mCloudinary = new Cloudinary(config);
    this.mPresetName = presetName;
    this.mConfig = config;
  }

  @SuppressWarnings("unused")
  @ReactMethod
  public void uploadImage(String path, Promise promise) {
    final RNCloudinaryModule _this = this;
    _this.isResolved = false;
    final Promise _promise = promise;
    try {

      Uri myFileUri = Uri.parse(path);
      InputStream inputStream = this.reactContext.getContentResolver().openInputStream(myFileUri);
      Map uploadResult = this.mCloudinary.uploader().unsignedUpload(inputStream, this.mPresetName, this.mConfig);
      WritableMap res = RNCloudinaryModule.toWritableMap(uploadResult);
      _promise.resolve(res);
      _this.isResolved = true;
    } catch (RuntimeException e) {
      String code = "Error";
      String message = "Error";
      Throwable error = new Throwable(message);
      _promise.reject(code, message, error);
    } catch (IOException e) {
      String code = "Error";
      String message = "Error";
      Throwable error = new Throwable(message);
      _promise.reject(code, message, error);
    }


  }

}
