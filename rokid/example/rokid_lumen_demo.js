import React, {
    AppRegistry,
    Component,
    Animated,
    Easing,
    View,
    StyleSheet,
    Text,
    Image,
    PanResponder,
    DeviceEventEmitter,
    AppState,
    BackAndroid,
    ListView
} from 'react-native';


//imports rokid modules
import RKReactNative from '../rkreact';

//var of status lable
var gestureDetected = false;


class LumenDemo extends React.Component {


    componentDidMount() {
	console.log("laoliang componentDidMount");

	//set rokid intent listener
	this.keyboardHideObserver = DeviceEventEmitter.addListener('RNIntent', (intent) => {

                console.log("lumen example nlp intent:" + intent);
		//decode intent, which is a json string 
                var nlp = JSON.parse(intent);
            });

	
	//var RKReactEventManager = React.NativeModules.ReactEventManager;
        //RKReactEventManager.notifyEventChannelReady(true);
    }

    render() {
      return (
	//set a default view to show basic information of this example on screen
        <View style={styles.container}>
          <Text style={styles.welcome}>
            This is a rokid lumen (r2self's light control) demo!
          </Text>
        </View>
      );
    }


}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 26,
    textAlign: 'center',
    margin: 10,
  },

});
// export class Lumen_demo as a seperate module, so in index.android.js, call module
// name,then the whole class is executed
module.exports = Lumen_demo;
