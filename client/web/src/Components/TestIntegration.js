import React, {Component} from 'react';

class TestIntegration extends Component{
    state ={};
    async componentDidMount() {
        await fetch('/ec/price')
            .then(response => response.json())
            .then(data => {
                this.setState({price0: data[0].price});
                this.setState({type0: data[0].type});
                this.setState({price1: data[1].price});
                this.setState({type1: data[1].type});
            });

    }
    render() {
        return (
            <div>
                <div>This is my test page.</div>

                <div>{this.state.type0}</div>
                <div>{this.state.price0}</div>

                <div>{this.state.type1}</div>
                <div>{this.state.price1}</div>
            </div>
        );
    }
}

export default TestIntegration;