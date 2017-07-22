//  Created by react-native-create-bridge

#import "RNCloudinary.h"

// import RCTBridge
#if __has_include(<React/RCTBridge.h>)
#import <React/RCTBridge.h>
#elif __has_include(“RCTBridge.h”)
#import “RCTBridge.h”
#else
#import “React/RCTBridge.h” // Required when used as a Pod in a Swift project
#endif

// import RCTEventDispatcher
#if __has_include(<React/RCTEventDispatcher.h>)
#import <React/RCTEventDispatcher.h>
#elif __has_include(“RCTEventDispatcher.h”)
#import “RCTEventDispatcher.h”
#else
#import “React/RCTEventDispatcher.h” // Required when used as a Pod in a Swift project
#endif

//Import libraries

//#import "RNCloudinary-Bridging-Header.h"
//#import <Cloudinary/Cloudinary-Swift.h>

#import <Cloudinary/Cloudinary-Swift.h>

@implementation RNCloudinary
@synthesize bridge = _bridge;


RCT_EXPORT_MODULE();

- (dispatch_queue_t)methodQueue {
  return dispatch_get_main_queue();
}


RCT_EXPORT_METHOD(config:(NSString *)cloudName aPIKey:(NSString *)apiKey apiSecret:(NSString *)apiSecret presetName:(NSString*)presetName) {
  NSString *cloudinaryUrl = [NSString stringWithFormat:@"cloudinary://%@:%@@%@", apiKey, apiSecret, cloudName];
  CLDConfiguration *config = [[CLDConfiguration alloc] initWithCloudinaryUrl:cloudinaryUrl];
  CLDCloudinary *cloudinary = [[CLDCloudinary alloc] initWithConfiguration:config networkAdapter:NULL sessionConfiguration:NULL];
  self.cloudinary = cloudinary;
  self.presetName = presetName;
}

RCT_EXPORT_METHOD(uploadImage:(NSString *)path resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
  NSData *data = [NSData dataWithContentsOfFile:path];
  
  if([[NSFileManager defaultManager] fileExistsAtPath:path]) {
    data = [[NSFileManager defaultManager] contentsAtPath:path];
  } else {
    NSString *code = @"No file";
    NSString *message = @"File not exist.";
    NSError *error = [NSError errorWithDomain:@"RNCloudinary" code:0 userInfo:nil];
    reject(code, message, error);
  }
  
  if (self.cloudinary) {
    [[self.cloudinary createUploader] uploadWithData:data uploadPreset:self.presetName params:NULL progress:^(NSProgress * progress) {
      
    } completionHandler:^(CLDUploadResult * result, NSError * error) {
      NSArray *keys = [NSArray arrayWithObjects:@"result", @"error", nil];
      NSArray *objects = [NSArray arrayWithObjects:@"success", @"", nil];
      NSDictionary *dictionary = [NSDictionary dictionaryWithObjects:objects
                                                             forKeys:keys];
      resolve(dictionary);
    }];
  } else {
    NSString *code = @"not configured";
    NSString *message = @"Cloudinary service is not configured correctly.";
    NSError *error = [NSError errorWithDomain:@"RNCloudinary" code:0 userInfo:nil];
    reject(code, message, error);
  }
}


@end

