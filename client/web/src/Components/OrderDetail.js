import React, { Component } from "react";
import { Descriptions, Button } from 'antd';

const BtnComponent = (props) =>
    <div className="orderDetail">
        <Descriptions
            title="Order Detail"
            bordered
            column={{ xxl: 1, xl: 1, lg: 1, md: 1, sm: 1, xs: 1}}
        >
            <Descriptions.Item label="Product Id"></Descriptions.Item>
            <Descriptions.Item label="Type(Car/Drone)"></Descriptions.Item>
            <Descriptions.Item label="Data & Time"></Descriptions.Item>
            <Descriptions.Item label="Amount"></Descriptions.Item>
            <Descriptions.Item label="From Where"></Descriptions.Item>
            <Descriptions.Item label="To Where"></Descriptions.Item>
            <Descriptions.Item label="Card Info"></Descriptions.Item>
            <Descriptions.Item label="Product Description"></Descriptions.Item>
        </Descriptions>
        <Button className="btn" type="primary" onClick={props.onClose}>close</Button>
    </div>;

class OrderDetail extends Component {

    state = {
        showOrderDetail: true
    }

    closeChild = () => {
        this.props.handleClose(false)
    };

    render() {
        return (
            <div>
                {this.state.showOrderDetail && <BtnComponent onClose={this.closeChild} />}
            </div >
        )
    }
}

export default OrderDetail;