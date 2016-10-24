import React from 'react';
import { Button,Glyphicon,Panel,Grid,Row,Col,Modal} from 'react-bootstrap'
export default class Template extends React.Component {
    static defaultProps = {
        customerid : null
    };

    state = {
        visible: false
    };

    componentWillMount() {
        $.post("ajax/sys/queryUserRoles",{},
            function(result) {

                this.setState({
                    roleOptions : result
                });
            }.bind(this));
    };

    render = ()=> {
        (<span>template
        </span>);
    }





}