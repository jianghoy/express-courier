import React, { Component } from "react";
import { Form, Icon, Input, Button, Checkbox } from "antd";
import NavBar from "./NavBar";
import Title from "antd/lib/typography/Title";

class LoginForm extends Component {
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log("Received values of form: ", values);
            }
        });
    };

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <NavBar />
                {/* <div className="header">
                    <Title>User Login</Title>
                </div> */}
                <div className="login-form">
                    <div className="header">
                        <h1>User Login</h1>
                    </div>
                    <Form onSubmit={this.handleSubmit} className="login-form">
                        <Form.Item>
                            {getFieldDecorator("username", {
                                rules: [
                                    {
                                        required: true,
                                        message: "Please input your username!"
                                    }
                                ]
                            })(
                                <Input
                                    prefix={
                                        <Icon
                                            type="user"
                                            style={{ color: "rgba(0,0,0,.25)" }}
                                        />
                                    }
                                    placeholder="Username"
                                />
                            )}
                        </Form.Item>
                        <Form.Item>
                            {getFieldDecorator("password", {
                                rules: [
                                    {
                                        required: true,
                                        message: "Please input your Password!"
                                    }
                                ]
                            })(
                                <Input
                                    prefix={
                                        <Icon
                                            type="lock"
                                            style={{ color: "rgba(0,0,0,.25)" }}
                                        />
                                    }
                                    type="password"
                                    placeholder="Password"
                                />
                            )}
                        </Form.Item>
                        <Form.Item>
                            {getFieldDecorator("remember", {
                                valuePropName: "checked",
                                initialValue: true
                            })(<Checkbox>Remember me</Checkbox>)}
                            <a
                                className="login-form-forgot"
                                href="www.google.com"
                            >
                                Forgot password
                            </a>
                            <Button
                                type="primary"
                                htmlType="submit"
                                className="login-form-button"
                            >
                                Log in
                            </Button>
                            Or <a href="www.google.com">register now!</a>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        );
    }
}
const NormalLoginForm = Form.create({ name: "login" })(LoginForm);

export default NormalLoginForm;
