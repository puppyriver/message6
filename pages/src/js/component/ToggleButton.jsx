import React from 'react';
import { Button} from 'react-bootstrap'
export default class ToggleButton extends React.Component {
    static defaultProps = {
        onClick : function(e){},
        bol : false
    };

    toggle = ()=>{
        this.props.onClick();
    }

    componentWillMount() {
      //  this.state.bol = this.props.defaultBol;
    };

    render = ()=> {
        var btn = <Button>{this.props.bol}</Button>;
        let children = React.Children.map(this.props.children, function (child) {
            if (this.props.bol == child.props.bol)
               btn = child;
        }.bind(this));
        return React.cloneElement(btn,{onClick :this.toggle});



    }





}