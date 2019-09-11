import React, { Component } from "react";
import { List, message, Avatar, Button, Spin, Pagination } from 'antd';
import reqwest from 'reqwest';
import InfiniteScroll from 'react-infinite-scroller';

const fakeDataUrl = 'https://randomuser.me/api/?results=5&inc=name,gender,email,nat&noinfo';

class OrderList extends Component {
  state = {
    loading: false,
    hasMore: true,
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
      url: fakeDataUrl,
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
    if (data.length > 6) {
      message.warning('Finished load all Orders');
      this.setState({
        hasMore: false,
        loading: false,
      });
      return;
    }
    this.fetchData(res => {
      data = data.concat(res.results);
      this.setState({
        data,
        loading: false,
      });
    });
  };

  OnClickDetail = e => {
    console.log('click by Detail', e);
  };

  render() {
    return (
      <div className="orderList">
        <InfiniteScroll
          initialLoad={false}
          pageStart={0}
          loadMore={this.handleInfiniteOnLoad}
          hasMore={!this.state.loading && this.state.hasMore}
          useWindow={false}
        >
          <List
              dataSource={this.state.data}
              renderItem={item => (
                <List.Item key={item.id}>
                  <List.Item.Meta
                    avatar={
                      <Avatar src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtljuuw8XUjeIUolQrLOqLmFLp9iiI9UpgUoXLakVzEZTJxVtmCA" />
                    }
                    title={item.name.title}
                    description={item.gender}
                  />
                  <div>
                    <Button type="primary" onClick={this.OnClickDetail}>Detail</Button>
                  </div>
                </List.Item>
              )}
            >
          </List>
        </InfiniteScroll>
      </div>
    );
  }
}

export default OrderList;