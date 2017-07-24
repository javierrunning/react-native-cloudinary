
# react-native-cloudinary

## Getting started

`$ npm install react-native-cloudinary --save`

### Mostly automatic installation

`$ react-native link react-native-cloudinary`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-cloudinary` and add `RNCloudinary.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCloudinary.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`

4. Import Cloudinary SDK
    Open Terminal and navigate to `ios` directory.

    If your project is not initialized as a git repository, run the command:

    ```bash
    $ git init
    ```

    To add cloudinary as a git submodule, run the command:

    ```bash
    $ git submodule add https://github.com/cloudinary/cloudinary_ios.git
    ```

    - Drag `Cloudinary.xcodeproj` into the Project Navigator of your application's Xcode project. It should appear under your application's blue project icon.
    - Select `Cloudinary.xcodeproj` and make sure the deployment target matches that of your application target.
    - Select your application project. Under 'TARGETS' select your application, open the 'General' tab, click on the `+` button under the 'Embedded Binaries' and Select 'Cloudinary.framework'.

5. Import Cloudinary SDK
    Open Terminal and navigate to `ios` directory.
    ```bash
    $ git init
    ```
    - Add Alamofire as a git by running the following command:
    ```bash
    $ git submodule add https://github.com/Alamofire/Alamofire.git
    ```
    - Open the new `Alamofire` folder, and drag the `Alamofire.xcodeproj` into the Project Navigator of your application's Xcode project.

        > It should appear nested underneath your application's blue project icon. Whether it is above or below all the other Xcode groups does not matter.

    - Select the `Alamofire.xcodeproj` in the Project Navigator and verify the deployment target matches that of your application target.
    - Next, select your application project in the Project Navigator (blue project icon) to navigate to the target configuration window and select the application target under the "Targets" heading in the sidebar.
    - In the tab bar at the top of that window, open the "General" panel.
    - Click on the `+` button under the "Embedded Binaries" section.
    - You will see two different `Alamofire.xcodeproj` folders each with two different versions of the `Alamofire.framework` nested inside a `Products` folder.

        > It does not matter which `Products` folder you choose from, but it does matter whether you choose the top or bottom `Alamofire.framework`.

    - Select the top `Alamofire.framework` for iOS and the bottom one for OS X.

        > You can verify which one you selected by inspecting the build log for your project. The build target for `Alamofire` will be listed as either `Alamofire iOS`, `Alamofire macOS`, `Alamofire tvOS` or `Alamofire watchOS`.

    - And that's it!

      > The `Alamofire.framework` is automagically added as a target dependency, linked framework and embedded framework in a copy files build phase which is all you need to build on the simulator and a device.


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNCloudinaryPackage;` to the imports at the top of the file
  - Add `new RNCloudinaryPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-cloudinary'
  	project(':react-native-cloudinary').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-cloudinary/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-cloudinary')
  	```

## Usage
```javascript
import RNCloudinary from 'react-native-cloudinary';

1. Config
   RNCloudinary.config(CLOUD_NAME, API_KEY, API_SECRET, PRESET_NAME);
2. Upload image
    Use the Uri file path for uploading the image to the Cloudinary cloud service.
    RNCloudinary.uploadImage(filePath).then(data => {
      ...
    })
    .catch(err => {
      ...
    });

// TODO: What to do with the module?
```
  