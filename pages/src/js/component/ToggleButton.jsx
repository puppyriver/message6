import React from 'react';
import { Button} from 'react-bootstrap'
export default class ToggleButton extends React.Component {
    static defaultProps = {
        onChange : function(e){},
        defaultBol : false
    };

    state = {
        bol : false
    };

    toggle = ()=>{
        this.setState({
            bol  : !this.state.bol
        });

        this.props.onChange(this.state.bol);

    }

    componentWillMount() {
        this.state.bol = this.props.defaultBol;
    };

    render = ()=> {
        var btn = <Button>{this.state.bol}</Button>;
        let children = React.Children.map(this.props.children, function (child) {
            if (this.state.bol == child.props.bol)
               btn = child;
        }.bind(this));
        return React.cloneElement(btn,{onClick :this.toggle});



    }





}