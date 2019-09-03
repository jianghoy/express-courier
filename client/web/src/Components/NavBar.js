import { Menu, Icon } from 'antd';
import React, {Component} from 'react';

const { SubMenu } = Menu;

export default class NavBar extends Component {
    state = {
        current: 'mail',
    };

    handleClick = e => {
        console.log('click ', e);
        this.setState({
            current: e.key,
        });
    };

    render() {
        return (
            <Menu onClick={this.handleClick} selectedKeys={[this.state.current]} mode="horizontal">
                <Menu.Item key="mail">
                    Logo
                </Menu.Item>
                <Menu.Item key="app">
                    Car Info
                </Menu.Item>
                <Menu.Item>
                    Drone Info
                </Menu.Item>
                <Menu.Item>
                    Help
                </Menu.Item>
                <SubMenu
                    title={
                        <span className="submenu-title-wrapper">Login</span>
                    }
                >
                    <Menu.ItemGroup title="Item 1">
                        <Menu.Item key="setting:1">Option 1</Menu.Item>
                        <Menu.Item key="setting:2">Option 2</Menu.Item>
                    </Menu.ItemGroup>
                </SubMenu>
            </Menu>
        );
    }
}