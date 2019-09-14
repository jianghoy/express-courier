import React, { Component } from "react";
import { List, Avatar, Button, Spin } from 'antd';
import reqwest from 'reqwest';
import InfiniteScroll from 'react-infinite-scroller';
import NavBar from './NavBar';
import { bool } from "prop-types";
import OrderDetail from './OrderDetail';

// fake data
const data = [
  {
    title: 'Product ID: 001',
    description: 'The product is babababa'
  },
  {
    title: 'Product ID: 002',
    description: 'The product is babababa'
  },
  {
    title: 'Product ID: 003',
    description: 'The product is babababa'
  },
  {
    title: 'Product ID: 004',
    description: 'The product is babababa'
  },
];

class OrderList extends Component {
  state = {
    loading: false,
    hasMore: true,
    showOrderDetail: false,
  };

  componentDidMount() {
    this.fetchData(res => {
      this.setState({
        data: res.results,
      });
    });
  }

  fetchData = callback => {
    reqwest({
      type: 'json',
      method: 'get',
      contentType: 'application/json',
      success: res => {
        callback(res);
      },
    });
  };

  handleInfiniteOnLoad = () => {
    let { data } = this.state;
    this.setState({
      loading: true,
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
        loading: false,
      });
    });
  };

  _showOrderDetail = (bool) => {
    console.log('showOrderDetail');
    this.setState({
      showOrderDetail: bool
    })
  }

  render() {
    return (
      <div className="orderList">
        <NavBar/>
        <div className="align-center">
          <div className="orderListStyle">
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
              renderItem={item => (
                <List.Item>
                  <List.Item.Meta
                    avatar={<Avatar style={{ backgroundColor: '#87d068' }} icon="car" />}
                    title={item.title}
                    description={item.description}
                  />
                  <div> 
                    <Button type="primary" onClick={this._showOrderDetail.bind(null, true)}>Detail</Button>
                    {this.state.showOrderDetail}
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
    </div>
    );
  }
}

export default OrderList;