import React, { Component } from "react";
import { Card, Input, Menu, Radio, Button } from "antd";
import PlacesAutocomplete, {
    geocodeByAddress,
    getLatLng
} from "react-places-autocomplete";
import { Typography } from "antd";
import DeliveryOption from "./DeliveryOption";
import Icon from "antd/es/icon";

const { Title } = Typography;

class OrderPanel extends Component {
    state = {
        value: 1
    };

    handlePickUpAddressChange = pickUpInput => {
        this.setState({ pickUpInput });
    };

    handlePickUpAddressSelect = async pickUpAddress => {
        // TODO: send pickUpAddress to back end
        await this.setState({ pickUpAddress });
        //this.setState({ pickUpInput: pickUpAddress });
        // console.log(this.state.pickUpAddress);
        geocodeByAddress(pickUpAddress)
            .then(results => getLatLng(results[0]))
            .then(latLng => console.log("Success", latLng))
            .catch(error => console.error("Error", error));
    };
    handleDestinationAddressChange = destinationInput => {
        this.setState({ destinationInput });
    };

    handleDestinationAddressSelect = async destinationAddress => {
        // TODO: send destinationAddress to back end
        await this.setState({ destinationAddress });
        //this.setState({ destinationInput: destinationAddress });
        // console.log(this.state.destinationAddress);
        geocodeByAddress(destinationAddress)
            .then(results => getLatLng(results[0]))
            .then(latLng => console.log("Success", latLng))
            .catch(error => console.error("Error", error));
    };

    handlePickUpClear = e => {
        this.setState({
            pickUpAddress: "",
            pickUpInput: ""
        });
    };
    handleDestinationClear = e => {
        this.setState({
            destinationAddress: "",
            destinationInput: ""
        });
    };

    /* Turn PlacesAutocomplete into a component, make code easier to read*/
    render() {
        return (
            <div className="infoCard">
                <Card className="info-card" bordered={false}>
                    <Title level={2}>Request a Delivery Now</Title>
                    <div className="autocomplete">
                        <div className="PickUp">
                            <PlacesAutocomplete
                                value={this.state.pickUpInput}
                                onChange={this.handlePickUpAddressChange}
                                onSelect={this.handlePickUpAddressSelect}
                            >
                                {({
                                    getInputProps,
                                    suggestions,
                                    getSuggestionItemProps,
                                    loading
                                }) => (
                                    <div className="autocomplete-input">
                                        <Input
                                            suffix={
                                                <div>
                                                    <Icon type="close"
                                                        onClick={ this.handlePickUpClear}
                                                    />
                                                </div>
                                            }
                                            {...getInputProps({
                                                placeholder:
                                                    "Add Pick Up Location",
                                                className:
                                                    "location-search-input",
                                                autoFocus: true
                                            })}
                                        />
                                        <Menu className="autocomplete-dropdown-container">
                                            {/*{loading && <div>Loading...</div>}*/}
                                            {suggestions.map(suggestion => {
                                                const className = suggestion.active
                                                    ? "suggestion-item--active"
                                                    : "suggestion-item";
                                                // inline style for demonstration purpose
                                                const style = suggestion.active
                                                    ? {
                                                          backgroundColor:
                                                              "#fafafa",
                                                          cursor: "pointer"
                                                      }
                                                    : {
                                                          backgroundColor:
                                                              "#ffffff",
                                                          cursor: "pointer"
                                                      };
                                                return (
                                                    <Menu.Item
                                                        {...getSuggestionItemProps(
                                                            suggestion,
                                                            {
                                                                className,
                                                                style
                                                            }
                                                        )}
                                                    >
                                                        <span>
                                                            {
                                                                suggestion.description
                                                            }
                                                        </span>
                                                    </Menu.Item>
                                                );
                                            })}
                                        </Menu>
                                    </div>
                                )}
                            </PlacesAutocomplete>
                            <Button type="primary"  icon="compass" />
                        </div>
                        <div className="Destination">
                            <PlacesAutocomplete
                                value={this.state.destinationInput}
                                onChange={this.handleDestinationAddressChange}
                                onSelect={this.handleDestinationAddressSelect}
                            >
                                {({
                                    getInputProps,
                                    suggestions,
                                    getSuggestionItemProps,
                                    loading
                                }) => (
                                    <div className="autocomplete-input">
                                        <Input
                                            suffix={
                                                <div>
                                                    <Icon type="close"
                                                        onClick={ this.handleDestinationClear}
                                                    />

                                                </div>
                                            }
                                            {...getInputProps({
                                                placeholder:
                                                    "Add Your Destination",
                                                className:
                                                    "location-search-input",
                                                autoFocus: true
                                            })}
                                        />
                                        <Menu className="autocomplete-dropdown-container">
                                            {/*{loading && <div>Loading...</div>}*/}
                                            {suggestions.map(suggestion => {
                                                const className = suggestion.active
                                                    ? "suggestion-item--active"
                                                    : "suggestion-item";
                                                // inline style for demonstration purpose
                                                const style = suggestion.active
                                                    ? {
                                                          backgroundColor:
                                                              "#fafafa",
                                                          cursor: "pointer"
                                                      }
                                                    : {
                                                          backgroundColor:
                                                              "#ffffff",
                                                          cursor: "pointer"
                                                      };
                                                return (
                                                    <Menu.Item
                                                        {...getSuggestionItemProps(
                                                            suggestion,
                                                            {
                                                                className,
                                                                style
                                                            }
                                                        )}
                                                    >
                                                        <span>
                                                            {
                                                                suggestion.description
                                                            }
                                                        </span>
                                                    </Menu.Item>
                                                );
                                            })}
                                        </Menu>
                                    </div>
                                )}
                            </PlacesAutocomplete>
                            <Button type="primary" icon="enter" />
                        </div>
                    </div>

                    <DeliveryOption
                        pickUpAddress={this.state.pickUpAddress}
                        destinationAddress={this.state.destinationAddress}
                    />
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

export default OrderPanel;