import ReactDOM from 'react-dom';
import React from 'react';
import XTable from '../component/XTable.jsx';
import Commons from '../common/common.jsx'
import { Button,ButtonToolbar,ButtonGroup,Glyphicon,Modal,Form,FormGroup,FormControl } from 'react-bootstrap';
let XTableChooserModal = React.createClass({
    getDefaultProps() {
        return {
            show : false,
            selectType : 'check',
            title : '选择',
            javaClassName : '',
            ajaxMethod: 'ajax/bo',
            defaultCondition : null,
            showColumns:null, //[]
            onHide : function(){},
            onConfirm : function(selectedRows){
                console.log("confirm selectedRows length = "+selectedRows.length);
            }
        };
    },

    getInitialState() {
        return {
            selectedRows : []
        }
    },

    render(){
       // console.log(`${this.props.title}:${this.props.defaultCondition}`);
        return (
            <Modal

                show={this.props.show}
                onHide={this.props.onHide}
                dialogClassName="custom-modal">

                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-lg">{this.props.title}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <XTable
                            ajaxMethod={this.props.ajaxMethod}
                            defaultCondition = {this.props.defaultCondition}
                            javaClassName={this.props.javaClassName}
                            selectType = {this.props.selectType}
                            buttons =  {[]}
                            toolBars = {['search','filter']}
                            rowButtons = {[]}
                            showColumns = {this.props.showColumns}
                            onSelectRowChange={function(selectedRows) {
                                this.setState({selectedRows:selectedRows});
                            }.bind(this)}
                        >

                        </XTable>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button {...Commons.Styles.Button.Confirm}  onClick={function(e){
                            this.props.onConfirm(this.state.selectedRows);
                        }.bind(this)}>确认</Button>
                        <Button  {...Commons.Styles.Button.Cancel} onClick={this.props.onHide}>关闭</Button>
                    </Modal.Footer>
                </Modal>
        )
    }
})

export default XTableChooserModal;

