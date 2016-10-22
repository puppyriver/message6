import ReactDOM from 'react-dom';
import React from 'react';
import { Tooltip ,OverlayTrigger} from 'react-bootstrap';
const Commons = {
    AlarmColors : [
        {value : 0 ,color : 'red'},
        {value : 1 ,color : 'yellow'},
        {value : 2 ,color : 'green'}
    ],

    Styles : {
        Button : {
            Add : {
                bsStyle : "success"
            },
            Search : {
                bsStyle : "info"
            },
            Edit : {
                bsStyle : "warning"
            },
            Delete : {
                bsStyle : "danger"
            },
            Confirm : {
                bsStyle : "primary"
            },

            Cancel : {

            }
        }
    },

    Alert(msg) {
        alert(msg);
    },

    ToolTipComponent : function(component,tooltip) {
        return (<OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">{tooltip}</Tooltip>}>
            <span>{component}</span>
        </OverlayTrigger>)
    }
}



export default Commons;