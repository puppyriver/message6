import React from 'react';
import Commons from '../common/common.jsx'
import { Button,Glyphicon,Panel,Grid,Row,Col,Modal,Alert} from 'react-bootstrap'
export default class ConfirmButton extends React.Component {
    static defaultProps = {
        msg : "是否继续?"
    };

    state = {
        alertVisible: false
    };

    componentWillMount() {
        $.post("ajax/sys/queryUserRoles",{},
            function(result) {

                this.setState({
                    roleOptions : result
                });
            }.bind(this));
    };

    handleAlertDismiss = ()=> {
        this.setState({alertVisible: false});
    };

    handleAlertAccept = ()=> {
        this.setState({alertVisible: false});
        this.props.onClick();
    };

    handleAlertShow =()=> {
        if (this.props.msg)
            this.setState({alertVisible: true});
    }

    render = ()=> {
        if (this.props.msg && this.state.alertVisible) {
            return (



                <Modal  bsSize="small" show={this.state.alertVisible} onHide={this.handleAlertDismiss}>
        <Modal.Header closeButton>
            <Modal.Title>警告

        </Modal.Title>

        </Modal.Header>
            <Modal.Body style={{padding : 5 ,fontSize : 10}}  >
                <h4>{this.props.msg}</h4>
            </Modal.Body>
            <Modal.Footer>
                <Button  onClick={this.handleAlertAccept} bsStyle="danger">继续</Button>
                <Button onClick={this.handleAlertDismiss}>取消</Button>
            </Modal.Footer>
        </Modal>

            );
        }

        return (  <Button {...this.props} onClick={this.handleAlertShow}>{

            }</Button>
        );
    }





}