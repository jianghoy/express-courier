import React, { Component } from "react";
import { Descriptions } from 'antd';

class OrderDetail extends Component {

    render() {
        return (
            <div className="orderDetail">
                <Descriptions
                    title="Order Detail"
                    bordered
                    column={{xxl: 1}}
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
            </div>
        )
    }
}

export default OrderDetail;