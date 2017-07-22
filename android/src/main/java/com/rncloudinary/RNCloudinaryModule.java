
package com.rncloudinary;

import android.net.Uri;
import android.util.Log;

import com.cloudinary.ProgressCallback;
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
      this.mCloudinary.uploader().unsignedUpload(inputStream, this.mPresetName, this.mConfig, new ProgressCallback() {
        public void onProgress(long bytesUploaded, long totalBytes) {
          if (bytesUploaded >= totalBytes) {
            if (_this.isResolved == false) {
              _promise.resolve("success");
              _this.isResolved = true;
            }
          } else {

          }
        }
      }) ;
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