import React, { Component } from 'react';
import { Card } from 'antd';
import { Input } from 'antd';
import { Radio, Button } from 'antd';
import PlacesAutocomplete, {
    geocodeByAddress,
    getLatLng,
} from 'react-places-autocomplete';

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

    handleChange = address => {
        this.setState({ address });
    };

    handleSelect = address => {
        geocodeByAddress(address)
            .then(results => getLatLng(results[0]))
            .then(latLng => console.log('Success', latLng))
            .catch(error => console.error('Error', error));
    };

    render() {
        const radioStyle = {
            display: 'block',
            height: '30px',
            lineHeight: '30px',
        };
        return (
            <Card className="info-card" bordered={false} style={{ width: 300, height: 600 }}>
                <br />
                <br />
                <div className="autocomplete">
                    <PlacesAutocomplete
                        value={this.state.address}
                        onChange={this.handleChange}
                        onSelect={this.handleSelect}
                    >
                        {({ getInputProps, suggestions, getSuggestionItemProps, loading }) => (
                            <div>
                                <input
                                    {...getInputProps({
                                        placeholder: 'Search Places ...',
                                        className: 'location-search-input',
                                    })}
                                />
                                <div className="autocomplete-dropdown-container">
                                    {loading && <div>Loading...</div>}
                                    {suggestions.map(suggestion => {
                                        const className = suggestion.active
                                            ? 'suggestion-item--active'
                                            : 'suggestion-item';
                                        // inline style for demonstration purpose
                                        const style = suggestion.active
                                            ? { backgroundColor: '#fafafa', cursor: 'pointer' }
                                            : { backgroundColor: '#ffffff', cursor: 'pointer' };
                                        return (
                                            <div
                                                {...getSuggestionItemProps(suggestion, {
                                                    className,
                                                    style,
                                                })}
                                            >
                                                <span>{suggestion.description}</span>
                                            </div>
                                        );
                                    })}
                                </div>
                            </div>
                        )}
                    </PlacesAutocomplete>
                    <br />
                    <br />
                    <Input placeholder="Add destination" />
                </div>
                <br />
                <br />
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
                <br />
                <br />
                <div className="price">Price Will Show Here</div>
                <br />
                <br />
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