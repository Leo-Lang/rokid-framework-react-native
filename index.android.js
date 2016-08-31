/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

import TTS_demo from './rokid/example/rokid_tts_demo';
import CV_demo from './rokid/example/rokid_cv_demo';
import Confirm_demo from './rokid/example/rokid_confirm_demo';
import Storage_demo from './rokid/example/storage_demo';
import RKReactNative from 'rokid-framework-react-native';

class rkreact extends Component {
  render() {
    return (
        <Storage_demo></Storage_demo>
    );
  }
}

AppRegistry.registerComponent('rokidreact', () => rkreact);
