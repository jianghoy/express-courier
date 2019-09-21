/*global google*/
import React, { Component } from "react";
import {
  withGoogleMap,
  withScriptjs,
  GoogleMap,
  DirectionsRenderer
} from "react-google-maps";


export default class Map extends Component {
  state = {
    directions: null
  };

  componentDidMount() {
    const directionsService = new google.maps.DirectionsService();

    const origin = { lat: 37.7879373, lng: -122.4096868 };
    const destination = { lat: 37.8081187, lng: -122.4210084 };

    directionsService.route(
      {
        origin: origin,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING
      },
      (result, status) => {
        if (status === google.maps.DirectionsStatus.OK) {
          this.setState({
            directions: result
          });
        } else {
          console.error(`error fetching directions ${result}`);
        }
      }
    );
  }

  render() {
    const GoogleMapExample = withGoogleMap(props => (
      <GoogleMap
        defaultCenter={{ lat: 37.7503423, lng: -122.5173255 }}
        defaultZoom={12}
      >
        <DirectionsRenderer
          directions={this.state.directions}
        />
      </GoogleMap>
    ));
    
    return (
      <div className="Map">
        <GoogleMapExample
          loadingElement ={<div style={{height: `100%`}}/>}
          containerElement={<div style={{ height: `100%`}} />}
          mapElement={<div style={{ height: `100%` }} />}
        />
      </div>
    );
  }
}
