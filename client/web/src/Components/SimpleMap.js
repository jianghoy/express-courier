import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
const {GOOGLEMAPAPI_KEY} = require('./credentials.json')
console.log(GOOGLEMAPAPI_KEY?"API key found.":"cannot find API_KEY, please create a credentials.json file under current folder and add {\"API_KEY\":YOUR_KEY}")
const AnyReactComponent = ({ text }) => <div>{text}</div>;
 
class SimpleMap extends Component {
  static defaultProps = {
    center: {
      lat: 59.95,
      lng: 30.33
    },
    zoom: 11
  };

  render() {
    return (
      // Important! Always set the container height explicitly
      <div style={{ height: '100vh', width: '100%' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key: GOOGLEMAPAPI_KEY }}
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}
        >
          <AnyReactComponent
            lat={59.955413}
            lng={30.337844}
            text="My Marker"
          />
        </GoogleMapReact>
      </div>
    );
  }
}
 
export default SimpleMap;
