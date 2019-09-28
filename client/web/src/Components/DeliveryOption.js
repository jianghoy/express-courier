import React, { Component } from "react";
import { getPriceAndTime } from "../API/API";
import { Radio, Statistic, Col, Row, Typography, Tooltip } from "antd";
import LoadingIndicator from "./LoadingIndicator";
const { Text } = Typography;
// this is only for display purpose, the price will not be passed back to backend
class DeliveryOption extends Component {
    state = {
        value: "car",
        carPrice: 0,
        dronePrice: 0,
        loading: true
    };

    onRadioButtonChange = e => {
        console.log("radio checked", e.target.value);
        this.setState({
            value: e.target.value
        });
        this.props.droneTypeSelection(this.state.value !== "car");
    };

    componentDidMount = () => {
        getPriceAndTime(
            this.props.destinationAddress,
            this.props.pickUpAddress,
            priceAndTime => {
                this.setState({
                    carPrice: priceAndTime[0].price,
                    dronePrice: priceAndTime[1].price,
                    loading: false
                });
            }
        );
    };

    // checkAddressDiff(nextProps) {
    //     const differentTitle =
    //         this.props.pickUpaddress !== nextProps.pickUpAddress;
    //     const differentDone =
    //         this.props.destinationAddress !== nextProps.destinationAddress;
    //     return differentTitle && differentDone;
    // }

    render() {
        const radioStyle = {
            display: "block",
            height: "30px",
            lineHeight: "30px"
        };

        if (this.state.loading) {
            return <LoadingIndicator />;
        }

        return (
            <div className="radio_button">                
                <Radio.Group
                    onChange={this.onRadioButtonChange}
                    value={this.state.value}
                >
                    <Row className="row-selection" gutter={30}>
                        <Col span={8}>
                            <Tooltip
                                title="Autonomous electrical cars are slower but have no size and weight restriction;
                        drones are faster but can only deliver goods within 10 pounds."
                            >
                                <Text strong> type? </Text>
                            </Tooltip>
                        </Col>
                        <Col span={8}>
                            <Text strong> price </Text>
                        </Col>
                        <Col span={8}>
                            <Text strong> estimated delivery </Text>
                        </Col>
                    </Row>
                    <Row className="row-selection" gutter={30}>
                        <Col span={8}>
                            <Radio
                                className="radio"
                                style={radioStyle}
                                value={"car"}
                            >
                                Use Car
                            </Radio>
                        </Col>
                        <Col span={8}>
                            <Statistic
                                className="price"
                                prefix="$"
                                value={this.state.carPrice}
                                precision={2}
                            />
                        </Col>
                        <Col span={8}>
                            <Statistic
                                className="time"
                                suffix="AM"
                                value="11:24"
                                precision={2}
                            />
                        </Col>
                    </Row>

                    <Row className="row-selection" gutter={30}>
                        <Col span={8}>
                            <Radio
                                className="radio"
                                style={radioStyle}
                                value={"drone"}
                            >
                                Use Drone
                            </Radio>
                        </Col>
                        <Col span={8}>
                            <Statistic
                                className="price"
                                prefix="$"
                                value={this.state.dronePrice}
                                precision={2}
                            />
                        </Col>
                        <Col span={8}>
                            <Statistic
                                className="time"
                                suffix="PM"
                                value="5:23"
                                precision={2}
                            />
                        </Col>
                    </Row>
                </Radio.Group>
            </div>
        );
    }
}

export default DeliveryOption;
