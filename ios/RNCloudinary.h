// import RCTBridgeModule
#import <React/RCTBridgeModule.h>
#import <Cloudinary/Cloudinary-Swift.h>

@interface RNCloudinary : NSObject <RCTBridgeModule>
@property (nonatomic, readwrite) CLDCloudinary *cloudinary;
@property (nonatomic, readwrite) NSString *presetName;
@end
