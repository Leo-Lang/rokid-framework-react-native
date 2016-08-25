#rokid-framework-react-native

## Installation

```
npm i --save rokid-framework-react-native
```

**on Android:**

####1. `android/settings.gradle`:: Add the following snippet

```
include ":rokid-framework-react-native"
project(':rokid-framework-react-native').projectDir = new File(rootProject.projectDir,'../node_modules/rokid-framework-react-native/android/rokid-framework-react-native')
```

####2. `android/app/build.gradle`: Add in dependencies block.

```
compile project(':rokid-framework-react-native')
```

####3. in your `MainActivity` (or equivalent) the FrameAnimReactPackage needs to be added. Add the import at the top:

```
import com.leolang.rokidframework.react.RKReactActivity;
import com.leolang.rokidframework.react.RKReactPackage;
```
####4. In order for React Native to use the package, add it the packages inside of the class extending RKReactActivity.
```
public class MainActivity extends RKReactActivity
```
```
@Override
protected List<ReactPackage> getPackages() {
  return Arrays.<ReactPackage>asList(
	new MainReactPackage(),
	...
	new RKReactPackage()
  );
}

```
## Usage
###js

```
import RKReactNative from './node_modules/rokid-framework-react-native/rokid/rkreact';


```





##sample
   
###android/app & index.android.js