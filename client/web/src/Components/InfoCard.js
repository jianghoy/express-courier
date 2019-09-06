import React, {Component} from 'react';
import { Card } from 'antd';
import { Input } from 'antd';
import { Radio, Button } from 'antd';


class InfoCard extends Component {
    state = {
        value: 1,
    };
    onChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };
    render() {
        const radioStyle = {
            display: 'block',
            height: '30px',
            lineHeight: '30px',
        };
        return (
            <Card bordered={false} style={{ width: 300, height: 400 }}>
                <div className="autocomplete">
                    <Input placeholder="Add pick up location" />
                    <br />
                    <br />
                    <Input placeholder="Add destination" />
                </div>
                <div className="radio_button">
                    <Radio.Group onChange={this.onChange} value={this.state.value}>
                        <Radio style={radioStyle} value={1}>
                            Use Car
                        </Radio>
                        <Radio style={radioStyle} value={2}>
                            Use Drone
                        </Radio>
                    </Radio.Group>
                </div>
                <div className="price">Price Will Show Here</div>
                <div className="checkout">
                    <Button type="primary" block>
                        Checkout
                    </Button>
                </div>
            </Card>
        );
    }
}

export default InfoCard;