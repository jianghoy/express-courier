import React, { Component } from 'react';
import {Card, Input, Menu, Radio, Button} from 'antd';
import PlacesAutocomplete, {
    geocodeByAddress,
    getLatLng,
} from 'react-places-autocomplete';
import { Typography} from 'antd';

const { Title} = Typography;



class InfoCard extends Component {
    state = {
        value: 1,
    };
    onRadioButtonChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };

    handlePickUpAddressChange = pickUpAddress => {
        this.setState({ pickUpAddress });
    };


    handlePickUpAddressSelect = pickUpAddress => {
        this.setState({pickUpAddress});
        geocodeByAddress(pickUpAddress)
            .then(results => getLatLng(results[0]))
            .then(latLng => console.log('Success', latLng))
            .catch(error => console.error('Error', error));

    };
    handleDestinationAddressChange = destinationAddress => {
        this.setState({ destinationAddress });
    };


    handleDestinationAddressSelect = destinationAddress => {
        this.setState({destinationAddress});
        geocodeByAddress(destinationAddress)
            .then(results => getLatLng(results[0]))
            .then(latLng => console.log('Success', latLng))
            .catch(error => console.error('Error', error));

    };


    render() {
        const radioStyle = {
            display: 'block',
            height: '30px',
            lineHeight: '30px',
        };
        return (
            <div className="infoCard">
                <Card className="info-card" bordered={false}>
                    <Title level={2}>Request a Delivery Now</Title>
                    <br />
                    <div className="autocomplete">
                        <PlacesAutocomplete
                            value={this.state.pickUpAddress}
                            onChange={this.handlePickUpAddressChange}
                            onSelect={this.handlePickUpAddressSelect}
                        >
                            { ({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
                                <div>
                                    <Input
                                        allowClear={true}
                                        {...getInputProps({
                                            placeholder: "Add Pick Up Location",
                                            className: 'location-search-input',
                                            autoFocus: true,
                                        })}
                                    />
                                    <Menu className="autocomplete-dropdown-container">
                                        {/*{loading && <div>Loading...</div>}*/}
                                        {suggestions.map(suggestion => {
                                            const className = suggestion.active
                                                ? 'suggestion-item--active'
                                                : 'suggestion-item';
                                            // inline style for demonstration purpose
                                            const style = suggestion.active
                                                ? { backgroundColor: '#fafafa', cursor: 'pointer' }
                                                : { backgroundColor: '#ffffff', cursor: 'pointer' };
                                            return (
                                                <Menu.Item
                                                    {...getSuggestionItemProps(suggestion, {
                                                        className,
                                                        style,
                                                    })}
                                                >
                                                   <span>
                                                    {suggestion.description}
                                                   </span>
                                                </Menu.Item>
                                            );
                                        })}
                                    </Menu>
                                </div>
                            )}
                        </PlacesAutocomplete>
                        <br />
                        <PlacesAutocomplete
                            value={this.state.destinationAddress}
                            onChange={this.handleDestinationAddressChange}
                            onSelect={this.handleDestinationAddressSelect}
                        >
                            { ({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
                                <div>
                                    <Input
                                        allowClear={true}
                                        {...getInputProps({
                                            placeholder: "Add Your Destination",
                                            className: 'location-search-input',
                                            autoFocus: true,
                                        })}
                                    />
                                    <Menu className="autocomplete-dropdown-container">
                                        {/*{loading && <div>Loading...</div>}*/}
                                        {suggestions.map(suggestion => {
                                            const className = suggestion.active
                                                ? 'suggestion-item--active'
                                                : 'suggestion-item';
                                            // inline style for demonstration purpose
                                            const style = suggestion.active
                                                ? { backgroundColor: '#fafafa', cursor: 'pointer' }
                                                : { backgroundColor: '#ffffff', cursor: 'pointer' };
                                            return (
                                                <Menu.Item
                                                    {...getSuggestionItemProps(suggestion, {
                                                        className,
                                                        style,
                                                    })}
                                                >
                                                   <span>
                                                    {suggestion.description}
                                                   </span>
                                                </Menu.Item>
                                            );
                                        })}
                                    </Menu>
                                </div>
                            )}
                        </PlacesAutocomplete>
                    </div>
                    <br />
                    <br />
                    <div className="radio_button">
                        <Radio.Group onChange={this.onRadioButtonChange} value={this.state.value}>
                            <Radio style={radioStyle} value={1}>
                                Use Car
                            </Radio>
                            <Radio style={radioStyle} value={2}>
                                Use Drone
                            </Radio>
                        </Radio.Group>
                    </div>
                    <br />
                    <br />
                    <div className="price">Price Will Show Here</div>
                    <br />
                    <br />
                    <div className="checkout">
                        <Button type="primary" block>
                            Checkout
                        </Button>
                    </div>
                </Card>
            </div>

        );
    }
}

export default InfoCard;