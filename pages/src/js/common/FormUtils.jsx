import React from 'react';
import { Button,ButtonToolbar,Modal,Form,FormGroup,FormControl,ControlLabel } from 'react-bootstrap';
import { Table,Pagination,Grid,Row,Col,Checkbox,Label } from 'react-bootstrap';
import Datetime from 'react-datetime';
import 'react-datetime/css/react-datetime.css';

const FormUtils = {



    columnDescToComponent(_this,columnDesc,bean,editable,key,validate) {
        if (columnDesc.editType == 'NULLABLE' || columnDesc.editType == 'REQUIRED') {

            if (columnDesc.fieldType == 'DATE') {
                return (
                    <FormGroup key={key}>
                        <Col sm={3}>
                            {columnDesc.description}:
                        </Col>

                        <Col sm={7}>
                            {
                                editable ?
                                    <Datetime disabled = {!editable} value={bean ? new Date(bean[columnDesc.key]) : null} onChange={function(e){
                                                                     this.handleFormChange({target : { id : columnDesc.key,value: e.format('YYYYMMDDHHmmss')}})
                                                                }.bind(_this)} />
                                    :
                                    <Label>{bean ? new Date(bean[columnDesc.key]).toLocaleString() : null}</Label>
                            }

                        </Col>
                    </FormGroup>);
            }
            if (columnDesc.values && columnDesc.values.length > 0) {
                return (
                    <FormGroup  key={key} controlId={columnDesc.key}>
                        <Col sm={3}>
                            {columnDesc.description}:
                        </Col>
                        <Col sm={7}>
                            <FormControl disabled = {!editable}  componentClass="select" placeholder="select" defaultValue={(bean && bean[columnDesc.key]) ?  bean[columnDesc.key] : -1 }>
                                <option value="" key="-1"></option>
                                {
                                    columnDesc.values.map(function(pair,idx){
                                        return (<option  key={idx} value={pair.key}>{pair.label}</option>);

                                    }.bind(_this))
                                }

                            </FormControl>
                        </Col>
                    </FormGroup>

                );
            }

            if (columnDesc.key == 'passWD') {
                return (
                    <FormGroup  key={key} controlId={columnDesc.key} validationState={validate}>
                        <Col sm={3}>
                            {columnDesc.description}:
                        </Col>
                        <Col sm={7}>
                            <FormControl disabled ={!editable}  type="password" placeholder="" defaultValue={bean && bean[columnDesc.key] ? bean[columnDesc.key] : ""} />
                        </Col>
                    </FormGroup>
                );
            }
            return (
                <FormGroup  key={key} controlId={columnDesc.key} validationState={validate}>
                    <Col sm={3}>
                        {columnDesc.description}:
                    </Col>
                    <Col sm={7}>
                        <FormControl disabled ={!editable} type="text" placeholder="" defaultValue={bean && bean[columnDesc.key] ? bean[columnDesc.key] : ""} />
                    </Col>
                </FormGroup>
            );
        }

    }


}

export default FormUtils;