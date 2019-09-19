import React, {Component} from 'react';
import {getPriceAndTime} from '../API/API'
import {Radio, Statistic, Col, Row} from "antd";

class DeliveryOption extends Component {
    state ={
        value: 1,
        type0: null,
        price0: 0,
        type1: null,
        price1: 0,
    };

    onRadioButtonChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };

    componentWillReceiveProps = (nextProps) =>{
        let orig = nextProps.pickUpAddress;
        let dest = nextProps.destinationAddress;
        if (this.checkAddressFilled(nextProps)===true){
            getPriceAndTime(dest,orig,(priceAndTime) => {
                this.setState({
                    price0:priceAndTime[0].price,
                    type0:priceAndTime[0].type,
                    price1:priceAndTime[1].price,
                    type1:priceAndTime[1].type
                })
            })
                // this.setState({
                //     price0: data[0].price,
                //     type0: data[0].type,
                //     price1: data[1].price,
                //     type1: data[1].type
                // });

        }

    };

    checkAddressFilled(nextProps) {
        const differentTitle = this.props.pickUpaddress !== nextProps.pickUpAddress;
        const differentDone = this.props.destinationAddress !== nextProps.destinationAddress;
        return differentTitle && differentDone;
    }



    render() {
        const radioStyle = {
            display: 'block',
            height: '30px',
            lineHeight: '30px',
        };
        return (
            <div className="radio_button">
                <Radio.Group onChange={this.onRadioButtonChange} value={this.state.value}>
                    <Row className="row-selection" gutter={30}>
                        <Col span={10}><Radio className="radio" style={radioStyle} value={1}>Use Car</Radio></Col>
                        <Col span={14} >
                            <Row type="flex"  align="middle" gutter={7}>
                                <Col span={12}><Statistic className="price"  prefix="$" value={this.state.price0} precision={2} /></Col>
                                <Col span={12}><Statistic className="time"  suffix="AM" value="11:24" precision={2} /></Col>
                            </Row>
                        </Col>
                    </Row>
                    <Row className="row-selection" gutter={30}>
                        <Col span={10}><Radio className="radio" style={radioStyle} value={2}>Use Drone</Radio></Col>
                        <Col span={14} >
                            <Row type="flex"  align="middle" gutter={7}>
                                <Col span={12}><Statistic className="price" prefix="$" value={this.state.price1} precision={2} /></Col>
                                <Col span={12}><Statistic className="time"  suffix="PM" value="5:23" precision={2} /></Col>
                            </Row>
                        </Col>

                    </Row>
                </Radio.Group>
            </div>
        );
    }
}

export default DeliveryOption;