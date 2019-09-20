import React, { Component } from "react";
import { Menu, Icon, Button } from "antd";
import { NavLink } from "react-router-dom";

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
        if (this.props.currentUser) {
            var userMenu = (
                <SubMenu
                    className="login"
                    key="orderList"
                    title={
                        <span className="submenu-title-wrapper">
                            <NavLink to="/orderList">Order Status</NavLink>
                        </span>
                    }
                >
                    <Menu.Item key="logOut">
                        <NavLink to="/logout">Logout</NavLink>
                    </Menu.Item>
                </SubMenu>
            );
        } else {
            var userMenu = (
                <Menu.Item key="login">
                    <NavLink to="/login">Login</NavLink>
                </Menu.Item>
            );
        }
        return (
            <Menu
                className="nav-bar"
                onClick={this.handleClick}
                selectedKeys={[this.state.current]}
                mode="horizontal"
            >
                <Button href="/">
                    <Icon type="github"></Icon>
                </Button>
                <Menu.Item key="carinfo">
                    <NavLink to="/carinfo">Car Info</NavLink>
                </Menu.Item>
                <Menu.Item key="droneinfo">
                    <NavLink to="/droneinfo">Drone Info</NavLink>
                </Menu.Item>
                <div className="space"></div>
                <Menu.Item className="help" key="help">
                    <NavLink to="/help">Help</NavLink>
                </Menu.Item>
                {userMenu}
                {/* <SubMenu
                    className="login"
                    key="login"
                    title={
                        <span className="submenu-title-wrapper">
                            <NavLink to="/login">Login</NavLink>
                        </span>
                    }
                >
                    <Menu.Item key="orderList">
                        <NavLink to="/orderList">Order Status</NavLink>
                    </Menu.Item>
                    <Menu.Item key="logOut">
                        <NavLink to="/logout">Logout</NavLink>
                    </Menu.Item>
                </SubMenu> */}
            </Menu>
        );
    }
}
