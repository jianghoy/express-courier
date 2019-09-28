/// <reference path="../_jsdoc/index.d.ts" />

import React, { Component } from "react";
import { Card, Input, Menu, Button, notification, Modal } from "antd";
import PlacesAutocomplete, {
    geocodeByAddress,
    getLatLng
} from "react-places-autocomplete";
import { Typography } from "antd";
import DeliveryOption from "./DeliveryOption";
import Icon from "antd/es/icon";
import { strToTAddress, checkout } from "../API/API";

const { Title } = Typography;

class OrderPanel extends Component {
    state = {
        type: "car",
        displayModal: false
    };

    handlePickUpAddressChange = pickUpInput => {
        this.setState({ pickUpInput });
    };

    handlePickUpAddressSelect = async pickUpAddress => {
        await this.setState({ pickUpAddress });
        this.setState({ pickUpInput: pickUpAddress });
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
        await this.setState({ destinationAddress });
        this.setState({ destinationInput: destinationAddress });
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

    /**
     * @param {TAddress} address
     */
    handleCheckAddress = address => {
        return address.city === "San Francisco";
    };
    handleCheckout = e => {
        /** @type {TOrder} */
        if (!this.props.handleCheckUser()) {
            alert('You haven\'t log in yet!');
            this.setState({displayModal:false});
            return;
        }
        var order = {
            shippingAddress: strToTAddress(this.state.destinationAddress),
            billingAddress: strToTAddress(this.state.pickUpAddress),
            pickUpAddress: strToTAddress(this.state.pickUpAddress),
            type: this.state.type
        };
        checkout(order)
            .then(() => {
                notification.success({
                    message: "Express courier",
                    description: "Thank you! Your order has put."
                });
            })
            .catch(e => {
                notification.error({
                    message: "Express Courier",
                    description:
                        e.message ||
                        "Sorry! Something went wrong. Please try again!"
                });
            });
    };
    handleGetPrice = e => {
        if (!this.state.destinationAddress) {
            notification.error({
                message: "Express Courier",
                description: "Please specify the destination"
            });
            return;
        }

        if (!this.state.pickUpAddress) {
            notification.error({
                message: "Express Courier",
                description: "Please specify the origin pickup address."
            });
            return;
        }

        /** @type {TOrder} */
        var order = {
            shippingAddress: strToTAddress(this.state.destinationAddress),
            billingAddress: strToTAddress(this.state.pickUpAddress),
            pickUpAddress: strToTAddress(this.state.pickUpAddress),
            type: this.state.type
        };

        // check if address is valid
        if (
            !this.handleCheckAddress(order.shippingAddress) ||
            !this.handleCheckAddress(order.pickUpAddress)
        ) {
            notification.error({
                message: "Express Courier",
                description:
                    "Sorry, the delivery service is only in San Francisco."
            });
            return;
        }

        this.setState({ displayModal: true });
    };

    handleCloseModal = () => {
        this.setState({ displayModal: false });
    };
    /**
     * handle select drone type in order panel
     * @param {boolean} bool true is car, false is drone
     */
    handleDroneType(bool) {
        this.setState({
            type: bool ? "car" : "drone"
        });
    }

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
                                                    <Icon
                                                        type="close"
                                                        onClick={
                                                            this
                                                                .handlePickUpClear
                                                        }
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
                            <Button type="primary" icon="compass" />
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
                                                    <Icon
                                                        type="close"
                                                        onClick={
                                                            this
                                                                .handleDestinationClear
                                                        }
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

                    <br />
                    <div className="checkout">
                        <Button type="primary" onClick={this.handleGetPrice}>
                            Get Price
                        </Button>
                    </div>
                </Card>
                {this.state.displayModal ? (
                    <Modal
                        title="Please select a delivery option"
                        visible={true}
                        onCancel={this.handleCloseModal}
                        onOk={this.handleCheckout}
                    >
                        <DeliveryOption
                            pickUpAddress={this.state.pickUpAddress}
                            destinationAddress={this.state.destinationAddress}
                            droneTypeSelection={this.handleDroneType.bind(this)}
                        />
                    </Modal>
                ) : null}
            </div>
        );
    }
}

export default OrderPanel;
