import React, {Component} from 'react';
import {Radio} from "antd";

class DeliveryOption extends Component {
    state ={
        value: 1,
        type0: null,
        price0: 0,
        type1: null,
        price1: 0,
    };

    onRadioButtonChange = e => {
        console.log('radio checked', e.target.value);
        this.setState({
            value: e.target.value,
        });
    };

    componentWillReceiveProps = (nextProps) =>{
        let orig = nextProps.pickUpAddress;
        let dest = nextProps.destinationAddress;
        let fetchURL = '/ec/price?dest='+dest+'&orig='+ orig;
        if (this.checkAddressFilled(nextProps)===true){
            console.log(fetchURL);
            fetch(fetchURL)
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        price0: data[0].price,
                        type0: data[0].type,
                        price1: data[1].price,
                        type1: data[1].type
                    });
                });
        }

    };
    checkAddressFilled(nextProps) {
        const differentTitle = this.props.pickUpaddress !== nextProps.pickUpAddress;
        const differentDone = this.props.destinationAddress !== nextProps.destinationAddress;
        return differentTitle && differentDone;
    }



    render() {
        const radioStyle = {
            display: 'block',
            height: '30px',
            lineHeight: '30px',
        };
        return (
            <div className="radio_button">
                <Radio.Group onChange={this.onRadioButtonChange} value={this.state.value}>
                    <Radio style={radioStyle} value={1}>
                        Use Car
                    </Radio>
                    <div>{'price: '+this.state.price0}</div>
                    <div>car time</div>
                    <Radio style={radioStyle} value={2}>
                        Use Drone
                    </Radio>
                    <div>{'price: '+this.state.price1}</div>
                    <div>car time</div>
                </Radio.Group>
            </div>
        );
    }
}

export default DeliveryOption;