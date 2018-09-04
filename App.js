/**
 * LaserCats DIY LaserTurrent cat toy project
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, AppRegistry} from 'react-native';
import BluetoothModule_Droid from './BluetoothModule_Droid';

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            textLoaded: false,
            myText: '',
            adapterStateLoaded: false,
            myAdapterState: ''
        };
    }

    loadText() {
        BluetoothModule_Droid.sayHello( (result) => {
            this.setState( { textLoaded:true, myText:result })
        });
    }

    getAdapterState() {
        BluetoothModule_Droid.getAdapterState( (result) => {
            this.setState({ adapterStateLoaded:true, myAdapterState:result })
        });
    }

    render() {
        let displayText = this.state.textLoaded ? this.state.myText : 'default';
        let adapterState = this.state.adapterStateLoaded ? this.state.myAdapterState : 'defaultAdapterState';
        if( this.state.textLoaded === false ) this.loadText();
        if( this.state.adapterStateLoaded === false ) this.getAdapterState();
        return (
            <View>
                <Text>sayHello: {displayText}</Text>
                <Text>adapterState: {adapterState}</Text>
            </View>
        );    
    }
}

AppRegistry.registerComponent('LaserCats', () => App);
