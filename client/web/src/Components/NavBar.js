import React, { Component } from "react";
import { Menu, Icon } from "antd";

const { SubMenu } = Menu;

export default class NavBar extends Component {
    state = {
        current: null
    };

    handleClick = e => {
        console.log("click ", e);
        this.setState({
            current: e.key
        });
    };

    render() {
        return (
            <Menu
                className="nav-bar"
                onClick={this.handleClick}
                selectedKeys={[this.state.current]}
                mode="horizontal"
            >
                <Icon className="logo" type="github" />
                <Menu.Item key="cars">Cars Info</Menu.Item>
                <Menu.Item key="drones">Drones Info</Menu.Item>
                <div className="space"></div>
                <Menu.Item className="help" key="help">
                    Help
                </Menu.Item>
                <SubMenu
                    className="login"
                    title={<span className="submenu-title-wrapper">Login</span>}
                >
                    <Menu.Item key="setting:1">Option 1</Menu.Item>
                    <Menu.Item key="setting:2">Option 2</Menu.Item>
                </SubMenu>
            </Menu>
        );
    }
}
