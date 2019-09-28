import React, { Component } from "react";
import { getPriceAndTime } from "../API/API";
import { Radio, Statistic, Col, Row } from "antd";

// this is only for display purpose, the price will not be passed back to backend
class DeliveryOption extends Component {
    state = {        
        value: "car",
        carPrice: 0,
        dronePrice: 0
    };

    onRadioButtonChange = e => {
        console.log("radio checked", e.target.value);
        this.setState({
            value: e.target.value
        });
        this.props.droneTypeSelection(this.state.value !== "car")
    };

    componentWillReceiveProps = nextProps => {
        let orig = nextProps.pickUpAddress;
        let dest = nextProps.destinationAddress;
        if (this.checkAddressDiff(nextProps) === true) {
            getPriceAndTime(dest, orig, priceAndTime => {
                this.setState({
                    carPrice: priceAndTime[0].price,
                    dronePrice: priceAndTime[1].price,
                });
            });
        }
    };

    checkAddressDiff(nextProps) {
        const differentTitle =
            this.props.pickUpaddress !== nextProps.pickUpAddress;
        const differentDone =
            this.props.destinationAddress !== nextProps.destinationAddress;
        return differentTitle && differentDone;
    }

    render() {
        const radioStyle = {
            display: "block",
            height: "30px",
            lineHeight: "30px"
        };
        return (
            <div className="radio_button">
                <Radio.Group
                    onChange={this.onRadioButtonChange}
                    value={this.state.value}
                >
                    <Row className="row-selection" gutter={16}>
                        <Col span={10}>
                            <Radio
                                className="radio"
                                style={radioStyle}
                                value={"car"}
                            >
                                Use Car
                            </Radio>
                        </Col>
                        <Col span={14}>
                            <Row type="flex" align="middle" gutter={7}>
                                <Col span={12}>
                                    {/* <Statistic
                                        className="price"
                                        prefix="$"
                                        value={this.state.carPrice}
                                        precision={2}
                                    /> */}
                                    <span>${this.state.carPrice}</span>
                                </Col>
                                <Col span={12}>
                                    <Statistic
                                        className="time"
                                        suffix="AM"
                                        value="11:24"
                                        precision={2}
                                    />
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                    <Row className="row-selection" gutter={30}>
                        <Col span={10}>
                            <Radio
                                className="radio"
                                style={radioStyle}
                                value={"drone"}
                            >
                                Use Drone
                            </Radio>
                        </Col>
                        <Col span={14}>
                            <Row type="flex" align="middle" gutter={7}>
                                <Col span={12}>
                                    <Statistic
                                        className="price"
                                        prefix="$"
                                        value={this.state.dronePrice}
                                        precision={2}
                                    />
                                </Col>
                                <Col span={12}>
                                    <Statistic
                                        className="time"
                                        suffix="PM"
                                        value="5:23"
                                        precision={2}
                                    />
                                </Col>
                            </Row>
                        </Col>
                    </Row>
                </Radio.Group>
            </div>
        );
    }
}

export default DeliveryOption;
