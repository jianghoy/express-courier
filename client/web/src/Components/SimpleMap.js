import React, { Component } from "react";
import GoogleMapReact from "google-map-react";
const { GOOGLEMAPAPI_KEY } = require("./credentials.json");
console.log(
    GOOGLEMAPAPI_KEY
        ? "API key found."
        : 'cannot find API_KEY, please create a credentials.json file under current folder and add {"API_KEY":YOUR_KEY}'
);
// for tutorial purpose only. <AnyReactComponent> can be any component per request.
const AnyReactComponent = ({ text }) => <div>{text}</div>;

class SimpleMap extends Component {
    static defaultProps = {
        center: {
            lat: 37.78694970170928,
            lng: -122.41420136670445
        },
        zoom: 13
    };

  render() {
    return (
      // Important! Always set the container height explicitly
      <div className="Map" style={{ height: '100vh', width: '100vw' }}>
        <GoogleMapReact
          bootstrapURLKeys={{ key: GOOGLEMAPAPI_KEY }}
          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}
        >
          <AnyReactComponent 
            lat={37.78694970170928}
            lng={-122.41420136670445}
            text="My Marker1"
          />
        </GoogleMapReact>
      </div>
    );
  }
}
 
export default SimpleMap;
