import React from 'react';
import { FormControl} from 'react-bootstrap'
export default class SimpleMessageEditor extends React.Component {
    static defaultProps = {
        customerid : null
    };

    state = {
        visible: false
    };

    componentWillMount() {
        // $.post("ajax/sys/queryUserRoles",{},
        //     function(result) {
        //
        //         this.setState({
        //             roleOptions : result
        //         });
        //     }.bind(this));
    };

    render = ()=> {
        (<FormControl componentClass="textarea" placeholder="textarea" />);
    }





}