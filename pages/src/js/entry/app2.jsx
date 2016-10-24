import ReactDOM from 'react-dom';
import React from 'react';
import MessageList from '../modules/MessageList.jsx'
import {   Row,Col } from 'react-bootstrap'

var div = document.getElementById('react-content');
var module = div.getAttribute("module");
if (module == 'home') {
    ReactDOM.render(<div style={{marginTop : 10}}  className="container-fluid" >
            <Row className="show-grid">
                <Col lg={12} >
                    <MessageList/>
                </Col>
            </Row>
        </div>
        ,div);
}