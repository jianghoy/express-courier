/// <reference path="../_jsdoc/index.d.ts" />

import React, { Component } from "react";
import { List, Avatar, Button, Spin, Typography } from "antd";
import reqwest from "reqwest";
import InfiniteScroll from "react-infinite-scroller";
import OrderDetail from "../Components/OrderDetail";
import moduleNgetOrdersByPaginationame from '../API/API';

const { Title } = Typography;

// fake data
const data = [
    {
        title: "Product ID: 001",
        description: "The product is babababa"
    },
    {
        title: "Product ID: 002",
        description: "The product is babababa"
    },
    {
        title: "Product ID: 003",
        description: "The product is babababa"
    },
    {
        title: "Product ID: 004",
        description: "The product is babababa"
    }
];

class OrderList extends Component {
    state = {
        loading: false,
        hasMore: true,
        showOrderDetail: false
    };

    // init load
    componentDidMount() {
        this.fetchData(res => {
            this.setState({
                data: res.results
            });
        });
    }

    //
    fetchData = callback => {
        reqwest({
            type: "json",
            method: "get",
            contentType: "application/json",
            success: res => {
                callback(res);
            }
        });
    };

    handleInfiniteOnLoad = () => {
        let { data } = this.state;
        this.setState({
            loading: true
        });
        // uncomment this when fetch real data
        // if (data.length > 6) {
        //   message.warning('Finished load all Orders');
        //   this.setState({
        //     hasMore: false,
        //     loading: false,
        //   });
        //   return;
        // }
        this.fetchData(res => {
            data = data.concat(res.results);
            this.setState({
                data,
                loading: false
            });
        });
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
                            dataSource={data}
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
                                        title={order.title}
                                        description={order.description}
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
