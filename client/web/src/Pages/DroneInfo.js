import React, { Component } from 'react';
import NavBar from '../Components/NavBar';
import { Carousel } from 'antd';
import { List, Avatar } from 'antd';
class DroneInfo extends Component{
    render() {
        const data = [
        {
            title: 'Ant Design Title 1',
        },
        {
            title: 'Ant Design Title 2',
        },
        {
            title: 'Ant Design Title 3',
        },
        {
            title: 'Ant Design Title 4',
        },
    ];
        return (
            <div className="droneinfo">
                {/* <NavBar/> */}
                <Carousel autoplay>
                    <div>
                        <h1>Drove</h1>
                        <img src={"data:image/jpeg;base64,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"} alt="drone pic" className="picprofile"/>

                    </div>
                    <div>
                        <h1>About Our Drone Bots</h1>

                        <List
                            itemLayout="horizontal"
                            dataSource={data}
                            renderItem={item => (
                                <List.Item>
                                    <List.Item.Meta
                                       avatar={<Avatar icon="car" />}
                                        title={<a href="https://ant.design">{item.title}</a>}
                                        description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                                    />
                                </List.Item>
                            )}
                        />,

                    </div>


                </Carousel>,
            </div>
            
        );
    }
}
export default DroneInfo;