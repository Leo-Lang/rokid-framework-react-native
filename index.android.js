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

import Sysinfo_demo from './rokid/example/rokid_sysinfo_demo';
import TTS_demo from './rokid/example/rokid_tts_demo';
import CV_demo from './rokid/example/rokid_cv_demo';
import Confirm_demo from './rokid/example/rokid_confirm_demo';
import Storage_demo from './rokid/example/storage_demo';
import Mobile_demo from './rokid/example/rokid_mobile_demo';
import Event_demo from './rokid/example/rokid_event_demo';
import RKReactNative from 'rokid-framework-react-native';


class rkreact extends Component {
  render() {
    return (
        <Event_demo></Event_demo>
    );
  }
}

AppRegistry.registerComponent('rokidreact', () => rkreact);
