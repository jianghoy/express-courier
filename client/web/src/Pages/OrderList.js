/// <reference path="../_jsdoc/index.d.ts" />

import React, { Component } from "react";
import { List, Avatar, Button, Spin, Typography } from "antd";
import InfiniteScroll from "react-infinite-scroller";
import OrderDetail from "../Components/OrderDetail";
import { getOrdersByPagination } from "../API/API";

const { Title } = Typography;

class OrderList extends Component {
    state = {
        loading: false,
        hasMore: true,
        showOrderDetail: false,
        page: -1,
        size: 5,
        data: []
    };

    // init load
    componentDidMount() {
        this.fetchData();
    }

    fetchData = () => {
        if (this.state.hasMore) {
            getOrdersByPagination(
                this.state.page + 1,
                this.state.size,
                (orders, hasMore) => {
                    console.log(orders);
                    console.log(hasMore);
                    this.setState(prevState => {
                        return {
                            data: [...prevState.data, ...orders],
                            hasMore: hasMore,
                            loading: false,
                            page: prevState.page + 1
                        };
                    });
                }
            );
        }
    };

    handleInfiniteOnLoad = () => {
        this.setState({
            loading: true
        });
        this.fetchData();
    };

    _showOrderDetail = bool => {
        console.log("showOrderDetail");
        this.setState({
            showOrderDetail: bool
        });
    };

    /**
     * set avatar based on order type
     * @param {TOrder} order
     */
    setAvatar(order) {
        if (order.type) {
            return order.type === "car" ? "car" : "cloud-upload";
        }
        return "car";
    }

    render() {
        return (
            <div className="orderList">
                <div className="orderListStyle">
                    <Title> Orders </Title>
                    <InfiniteScroll
                        initialLoad={false}
                        pageStart={0}
                        loadMore={this.handleInfiniteOnLoad}
                        hasMore={!this.state.loading && this.state.hasMore}
                        useWindow={false}
                    >
                        <List
                            itemLayout="horizontal"
                            dataSource={this.state.data}
                            renderItem={order => (
                                <List.Item>
                                    <List.Item.Meta
                                        avatar={
                                            <Avatar
                                                style={{
                                                    backgroundColor: "#87d068"
                                                }}
                                                icon={this.setAvatar(order)}
                                            />
                                        }
                                        //TODO: fix format
                                        title={`Price: \$ ${parseFloat(order.price).toFixed(2)}`}
                                        description={`From: ${order.pickUpAddress.address} To: ${order.shippingAddress.address}`}
                                    />
                                    <div>
                                        <Button
                                            type="primary"
                                            onClick={this._showOrderDetail.bind(
                                                null,
                                                true
                                            )}
                                        >
                                            Detail
                                        </Button>
                                        {this.state.showOrderDetail ? (
                                            <OrderDetail
                                                handleClose={
                                                    this._showOrderDetail
                                                }
                                            />
                                        ) : null}
                                    </div>
                                </List.Item>
                            )}
                        >
                            {this.state.loading && this.state.hasMore && (
                                <div className="demo-loading-container">
                                    <Spin />
                                </div>
                            )}
                        </List>
                    </InfiniteScroll>
                </div>
            </div>
        );
    }
}

export default OrderList;
