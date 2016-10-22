import React from 'react';
import { Button,ButtonToolbar,Modal,Form,FormGroup,FormControl,ControlLabel } from 'react-bootstrap';
import { Table,Pagination,Grid,Row,Col,Checkbox,Label,Alert } from 'react-bootstrap';
import Datetime from 'react-datetime';
import Commons from '../common/common.jsx'
import 'react-datetime/css/react-datetime.css';
import FormUtils from '../common/FormUtils.jsx';
import XTableChooserButton from './XTableChooserButton.jsx'


  class XEditor extends React.Component {
      static defaultProps =   {
            columnDescs : [],
            ajaxMethod: 'ajax/bo',
            javaClassName : '',
            handleClose : function() {
            },
            handleSuccess : function(bean) {
            },
            defaultCondition : null,
            saveWithDefaultCondition : true,
            show : false,
            edit : true,
            bean : null
        };


    state = {
            visible: false,
            files:'',
            show : false,
         //   bean : null,
            validateStatus : {},
            form : {

            }



        };


    editBean = null
      columnDescs2 = []
    componentWillMount () {
        console.log("columnDesc = "+this.props.columnDescs.length);

    }

    componentDidMount () {
        this.updateDescRow(this.props.columnDescs);
    }

    updateDescRow = (columnDescs)=> {
        //columnDesc.editType == 'NULLABLE' || columnDesc.editType == 'REQUIRED'
        //
        //for (var i = 0; i < columnDescs.length; i=i+2) {
        //    var cd1 = columnDescs[i];
        //    var cd2 = columnDescs[i+1];
        //    if (cd1)
        //        this.columnDescs2.push([cd1,cd2]);
        //}
        this.columnDescs2 = [];
        var row = [];
        for (var i = 0; i < columnDescs.length; i++) {
            var columnDesc = columnDescs[i];
            if (columnDesc.editType == 'NULLABLE' || columnDesc.editType == 'REQUIRED') {
                row.push(columnDesc);
                if (row.length == 2) {
                    this.columnDescs2.push(row);
                    row = [];
                }

            }

        }
        if (row.length > 0) this.columnDescs2.push(row);
    }

    componentWillReceiveProps(nextProps) {
     //   console.log("componentWillReceiveProps:"+nextProps.bean);
        if (nextProps.columnDescs) {
             this.updateDescRow(nextProps.columnDescs);
        }
        if (nextProps.bean) {
            this.editBean = nextProps.bean;


        }

        var vs = {};
        for (var i = 0; i < this.props.columnDescs.length; i++) {
            var cd = this.props.columnDescs[i];
            if (cd.editType == 'REQUIRED') {
                if  (this.editBean && (!this.editBean[cd.key] || this.editBean[cd.key] == ''))
                     vs[cd.key] = 'error';
                else
                    vs[cd.key] = 'success';
            }

        }
        this.setState({validateStatus : vs});
    }





    invalidMsg =  ''

    getColumnDesc(key) {
        for (var i = 0; i < this.props.columnDescs.length; i++) {
            var cd = this.props.columnDescs[i];
            if (cd.key == key)
                return cd.description;
        }

    }

    validate() {
        if (this.state.validateStatus) {
            for (var key in this.state.validateStatus) {
                if (this.state.validateStatus[key] == 'error') {
                    console.log("validate : false");
                    this.invalidMsg = this.getColumnDesc(key)+" 字段为必填!";
                    return false;
                }
            }
        }
        return true;
    }
    save = ()=> {
       if (!this.validate()) return;
        if (this.editBean) {
            $.post(this.props.ajaxMethod+"/saveBObject",{
                javaClassName:this.props.javaClassName,
                bean : this.editBean,
                defaultCondition :  this.props.saveWithDefaultCondition ? this.props.defaultCondition : null
            },
                function(result) {
                    this.props.handleSuccess(result);
                }.bind(this));
        }
    }







    handleFormChange = (evt,evt2) =>{


        console.log("target="+evt.target.id+" :: "+evt.target.value+"::isselected:"+evt.target.checked);
        let id = evt.target.id;
        let value = evt.target.value;

        this.editBean[id] = value;

        var vs = this.state.validateStatus;
        if (vs[id]) {
            if (value && value.length > 0) {
                vs[id] = 'success';
            } else {
                vs[id] = 'error';
            }
        }
        this.setState({validateStatus : vs});

        console.log(id+" -> "+value);



    }

    toOverrideFormGroups(){

    }

      columnDescToComponent = (columnDesc,bean,editable,key,validate) => {
          if (columnDesc.editType == 'NULLABLE' || columnDesc.editType == 'REQUIRED') {

              if (columnDesc.dnReferenceEntityName) {
                  return (
                      <FormGroup key={key}>
                          <Col sm={4}>
                              <b>{columnDesc.description}:</b>
                          </Col>

                          <Col sm={6}>
                              {
                                  editable ?
                                <XTableChooserButton
                                    showColumns={[columnDesc.dnReferenceEntityField]}
                                    disabled = {!editable}
                                    initValue = {bean ? bean[columnDesc.dnReferenceTransietField] :''}
                                    javaClassName={columnDesc.dnReferenceEntityName}
                                    selectType = 'check'
                                    onConfirm = {function(selectedRows){
                                        console.log("confirm selectedRows length = "+selectedRows.length);
                                        if (selectedRows.length > 0) {
                                            var row = selectedRows[0];
                                            var v = row.dn;
                                            var name = row[columnDesc.dnReferenceEntityField];
                                            this.handleFormChange({target : { id : columnDesc.key,value: v}});
                                            this.handleFormChange({target : { id : columnDesc.dnReferenceTransietField,value: name}});
                                        }
                                    }.bind(this)}
                                />
                                      :  bean ? <b>{bean[columnDesc.dnReferenceTransietField]}</b> : ''
                              }
                          </Col>
                      </FormGroup>);
              }

              if (columnDesc.fieldType == 'DATE') {
                  return (
                      <FormGroup key={key}>
                          <Col sm={4}>
                              <b>{columnDesc.description}:</b>
                          </Col>

                          <Col sm={6}>
                              {
                                  editable ?
                                      <Datetime  defaultValue={bean ? new Date(bean[columnDesc.key]) : null} onChange={function(e){
                                                                     this.handleFormChange({target : { id : columnDesc.key,value: e.format('YYYYMMDDHHmmss')}})
                                                                }.bind(this)} />
                                      :  bean && bean[columnDesc.key] && new Date(bean[columnDesc.key]).toLocaleString()
                              }

                          </Col>
                      </FormGroup>);
              }
              if (columnDesc.values && columnDesc.values.length > 0) {
                  var _label = "";
                  if (bean) {
                      var key = bean[columnDesc.key]
                      columnDesc.values.map(function(pair,idx){
                         if (pair.key == key)
                             _label = pair.label;
                      });
                  }

                  return (
                      <FormGroup  key={key} controlId={columnDesc.key}>
                          <Col sm={4}>
                              <b>{columnDesc.description}:</b>
                          </Col>
                          <Col sm={6}>

                              {
                                  editable ?
                              <FormControl disabled = {!editable}  componentClass="select" placeholder="select" defaultValue={(bean) ?  bean[columnDesc.key] : -1 }>
                                  <option value="-1" key="-1"></option>
                                  {
                                      columnDesc.values.map(function(pair,idx){
                                          return (<option  key={idx} value={pair.key}>{pair.label}</option>);

                                      }.bind(this))
                                  }

                              </FormControl> : _label

                              }
                          </Col>
                      </FormGroup>

                  );
              }

              if (columnDesc.key == 'passWD') {
                  return (
                      <FormGroup  key={key} controlId={columnDesc.key} validationState={validate}>
                          <Col sm={4}>
                              <b>{columnDesc.description}:</b>
                          </Col>
                          <Col sm={6}>
                              <FormControl disabled ={!editable}  type="password" placeholder="" defaultValue={bean && bean[columnDesc.key] ? bean[columnDesc.key] : ""} />
                          </Col>
                      </FormGroup>
                  );
              }
              return (
                  <FormGroup  key={key} controlId={columnDesc.key} validationState={validate}>
                      <Col sm={4}>
                          <b>{columnDesc.description}:</b>
                      </Col>
                      <Col sm={6}>
                          {
                              editable ?<FormControl disabled ={!editable} type="text" placeholder="" defaultValue={bean && bean[columnDesc.key] ? bean[columnDesc.key] : ""} />
                                  :
                                  bean && bean[columnDesc.key] ? bean[columnDesc.key] : ""

                          }


                      </Col>
                  </FormGroup>
              );
          }

      }


      renderErrorMsg = ()=>{
          if (!this.validate()) {
              return  <Alert bsStyle="danger">
                  <strong>错误 : </strong> {this.invalidMsg}.
              </Alert>
          }
      }


    render() {


        return (
            <Modal bsSize="large"

                show={this.props.show}
                onHide={this.props.handleClose}
                dialogClassName="custom-modal">
                <Form horizontal  onChange={this.handleFormChange}>
                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-lg">{this.props.bean && this.props.bean.id ? "修改":"新增"}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>

                        {


                            (()=>{

                                var rows = this.columnDescs2.map(function(cdr,idx){
                                    return (
                                        <Row className="show-grid" key={"ed_row_"+idx}>
                                            <Col md={6} >{this.columnDescToComponent(cdr[0],this.props.bean,this.props.edit,idx,this.state.validateStatus[cdr[0].key])}</Col>
                                            <Col md={6} >{cdr[1]?this.columnDescToComponent(cdr[1],this.props.bean,this.props.edit,idx,this.state.validateStatus[cdr[1].key]) : null}</Col>
                                        </Row>
                                    )
                                }.bind(this));

                                return rows;

                            })()


                        }

                        {
                            (()=>{

                                if (this.toOverrideFormGroups && typeof this.toOverrideFormGroups == 'function')
                                    return this.toOverrideFormGroups()
                            })()

                        }

                        {
                           this.renderErrorMsg()
                        }



                    </Modal.Body>
                    <Modal.Footer>
                        <Button {...Commons.Styles.Button.Confirm} disabled={!this.props.edit} onClick={this.save}>保存</Button>
                        <Button {...Commons.Styles.Button.Cancel} onClick={this.props.handleClose}>关闭</Button>
                    </Modal.Footer>
                </Form>
            </Modal>

        );
    }
}


export default XEditor;
