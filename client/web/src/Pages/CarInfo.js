import React, { Component } from 'react';
import { Carousel, List, Avatar } from 'antd';

class CarInfo extends Component {
    render() {
        const data = [
            {
              title: 'Delivery bot1 demo',
              description: 'This bot is for short path travel'
            },
            {
              title: 'Delivery Bot2 demo',
              description: 'This bot is for long path travel'
            },
            {
              title: 'Delivery Bot3 demo',
              description: 'This bot is for median path travel'
            },
            {
              title: 'Delivery Bot4 demo',
              description: 'This bot is for small path travel'
            },
          ];
        return (
            <div className="carinfo">
                <Carousel effect="scrollx">
                    <div>
                        <h3>Our Delivery Bot</h3>
                        <img src="https://techcrunch.com/wp-content/uploads/2018/12/Nuro-departing-with-an-order-for-delivery-e1545153472696.jpg?w=730&crop=1" alt="Car" className="picProfile"></img>
                    </div>
                    <div>
                        <h3>About our Delivery Bot</h3>
                        <List
                            itemLayout="horizontal"
                            dataSource={data}
                            renderItem={item => (
                                <List.Item>
                                    <List.Item.Meta
                                        avatar={<Avatar icon="car" />}
                                        title={<a href="">{item.title}</a>}
                                        description={item.description}
                                    />
                            </List.Item>)}
                            />
                    </div>
                </Carousel>
            </div>
                );
            }
        }
export default CarInfo;