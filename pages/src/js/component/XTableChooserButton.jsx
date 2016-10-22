import React from 'react';

import { Button,Glyphicon,Panel,Grid,Row,Col,Modal,Badge} from 'react-bootstrap'
import XTableChooserModal from './XTableChooserModal.jsx'
export default class XTableChooserButton extends React.Component {
    static defaultProps = {
       // value : null
        disabled : false,
        initValue : '',
        show : false,
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

    state = {
       value : '',
        showModal: false
    };

    componentWillMount() {
        this.setState({value : this.props.initValue});
        console.log("abcde");
    };
    close =()=> {
        this.setState({showModal : false});
    };

    render = ()=> {
        return (<span >
            <span


            >
                <Button   className="btn-info"
                        onClick={ function(e){
                            if (this.props.disabled) return;
                            this.setState({showModal : true});
                        }.bind(this)
                    }
                >{this.state.value}</Button>




            </span>
            <XTableChooserModal

                {...this.props}
                show = {this.state.showModal}
                onHide = {function(){
                        this.setState({showModal : false});
                    }.bind(this)}
                onConfirm = {function(selectedRows){

                        if (selectedRows.length > 0) {
                            this.setState({showModal : false,value : selectedRows[0].name});
                             this.props.onConfirm(selectedRows);
                        }


                    }.bind(this)
                }
            />
        </span>);
    }





}