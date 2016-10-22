import React from 'react';
import { Button,ButtonToolbar,ButtonGroup,Glyphicon,Modal,Form,FormGroup,FormControl } from 'react-bootstrap';
import { Table,Pagination,Grid,Row,Col,Checkbox ,Tooltip,Dropdown,OverlayTrigger,MenuItem,Label,InputGroup,DropdownButton} from 'react-bootstrap';
import Datetime from 'react-datetime';
import 'react-datetime/css/react-datetime.css';
import Utils from '../common/Utils.jsx';
import Commons from '../common/common.jsx'
import ConfirmButton from './ConfirmButton.jsx'
import XEditor from './XEditor.jsx';


const XTable = React.createClass({
    propTypes: {
        ajaxMethod: React.PropTypes.string
    },
    columnDescMap : {},
    columnDescs : [],
    viewColumnDescs : [],
    columnKeys : [],
    viewColumnKeys : [],


    searchForm : null,

    selectedRows : [],

    getDefaultProps() {
        return {
            ajaxMethod: 'ajax/bo',
            javaClassName : '',
            defaultCondition :null, // {name : { value : "abc"}}
            saveWithDefaultCondition : true,
            form : {},
            buttons : ['add','delete'],
            extraButtons : [],
            toolBars : ['search','filter','refresh','export'],
            rowButtons :['view','edit','delete'],
            extraRowButtons : [],  // { component : <Button></Button> }
            showColumns :null, //Array []
            showNo : true,
            selectType : 'check', //'check','radio','none'

            onClickRow : null,
            onDoubleClickRow : null,

            onSelectRowChange:function(selectedRows) {
            },
            gridRender : function(bean,_columnKey,_value) {
                if (_value && _value.length > 20) {
                        return (<OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">{_value}</Tooltip>}>
                            <span>{_value.substr(0,20)+"..."}</span>
                        </OverlayTrigger>)

                }


                return _value;
            }
        };
    },

    getInitialState() {
        return {
            data : {
                columns : [],
                rows : []
            },
            page : 1,
            pageSize : 10,
            total : 0,

            selectedRowId : null,
            editShow : false,
            viewShow : false,
            editRow : null,
       //     editMode : true,
            searchShow : false,

            searchTxt : '',
            sortColumnKey : null,
            sortType : 'asc', //'desc'
            extendAttributes : {}

        };
    },

    getOption(value,values) {
        for (var p in values) {
            if (value == values[p].key)
                return  values[p];
        }
    },

    dataChange : function() {
        this.setState({
            data : this.state.data
        })
    },

    beanToRow : function(bean) {
        return this.viewColumnKeys.map(function(_columnKey,idx){
            var columnDesc = this.columnDescMap[_columnKey];
            var value = bean[_columnKey];
            var bgColor = "#000000";
            if (columnDesc.values && columnDesc.values.length > 0) {
                 let op =  this.getOption(value,columnDesc.values);
                if (op) {
                    value = op.label;
                    if (op.color)
                        bgColor = op.color;
                }

            }

            if (columnDesc.fieldType == 'DATE') {
                var dt = new Date();
                dt.setTime(value);
                value = dt.toLocaleString();
            }
            return (<td  key={_columnKey} style={{"verticalAlign":"middle","color" : bgColor}}>{this.props.gridRender.call(this,bean,_columnKey,value)}</td>);
            //return (<td>{bean[_columnKey]}</td>);
        }.bind(this));

    },


    componentWillMount: function() {
        this.searchForm = {};
        this.selectedRows = [];
        this.fetch();


    },

    export : function() {

        var filter = this.searchForm;



        if (this.props.defaultCondition) {
            for (var obj in this.props.defaultCondition) {
                filter[obj] = (this.props.defaultCondition[obj]);
            }
        }

        var form = $("<form>");  //==>jQuery创建隐藏表单,实现ajax下载
        form.attr('style','display:none');
        form.attr('target',"");
        form.attr('method','post');
        form.attr('action',this.props.ajaxMethod+"/export");



        var input1=$("<input>");
        input1.attr("type","hidden");
        input1.attr("name","javaClassName");
        input1.attr("value",this.props.javaClassName);

        form.append(input1);

        for (var name in this.searchForm) {
            var input2=$("<input>");
            input2.attr("type","hidden");
            if (this.searchForm[name].hasOwnProperty("value")) {
                input2.attr("name","filter["+name+"][value]");
                input2.attr("value",encodeURIComponent(this.searchForm[name].value));
            }

            form.append(input2);

            if (this.searchForm[name].hasOwnProperty("checked")) {
                var input3=$("<input>");
                input3.attr("type","hidden");
                if (this.searchForm[name].hasOwnProperty("value")) {
                    input3.attr("name","filter["+name+"][checked]");
                    input3.attr("value",this.searchForm[name].checked);
                }
                form.append(input3);

            }

        }

        var inputS=$("<input>");
        inputS.attr("type","hidden");
        inputS.attr("name","extend[searchTxt]");
        inputS.attr("value",this.state.searchTxt);

        form.append(inputS);

        for (var key in this.state.extendAttributes) {
            var v = this.state.extendAttributes[key];
            var input4=$("<input>");
            input4.attr("type","hidden");
            input4.attr("name","extend["+key+"]");
            input4.attr("value",v);

            form.append(input4);

        }






        $('body').append(form);

        form.submit();
        form.remove();

    },


    fetch(page=this.state.page) {

        $.get(this.props.ajaxMethod+"/getBObjectFieldDescs",{javaClassName:this.props.javaClassName }, function(result) {
            var cols = [];
            var _columnKeys = [];
            var _columnDescs = [];
            var _viewColumnKeys = [];
            var _viewColumnDescs = [];

            for (var p in result) {
                result[p].key = p;
                _columnDescs.push(result[p]);
            }
            _columnDescs.sort(function(a,b){return a.sequence - b.sequence});

            _columnDescs.map(function(p) {
                _columnKeys.push(p.key);
                if (p.viewType=="SHOW") {
                    if (!this.props.showColumns || this.props.showColumns.length == 0 || this.props.showColumns.indexOf(p.key) > -1) {
                        _viewColumnKeys.push(p.key);
                        _viewColumnDescs.push(p);
                        cols.push(p.description);
                    }
                }
            }.bind(this));







            this.columnKeys = _columnKeys;
            this.columnDescs = _columnDescs;
            this.columnDescMap = result;
            this.viewColumnDescs = _viewColumnDescs;
            this.viewColumnKeys = _viewColumnKeys;


            console.log("defaultCondition="+this.props.defaultCondition);
            console.log("searchForm="+Utils.objToString(this.searchForm));

            var filter = this.searchForm;

            if (this.props.defaultCondition) {
                for (var obj in this.props.defaultCondition) {
                    filter[obj] = (this.props.defaultCondition[obj]);
                }
            }

            if (cols.length > 0) {


                $.get(this.props.ajaxMethod+"/queryObjectsCount",{javaClassName:this.props.javaClassName,filter : filter,extend :{searchTxt : this.state.searchTxt,...this.state.extendAttributes}},
                    function(result) {

                        this.setState({
                            total : result,
                            page : page
                        });



                    }.bind(this));

                $.get(this.props.ajaxMethod+"/queryObjects",{javaClassName:this.props.javaClassName, filter : filter,extend :{searchTxt : this.state.searchTxt,...this.state.extendAttributes},
                                            start:(page-1) * this.state.pageSize,limit:this.state.pageSize, sort : this.state.sortColumnKey, sortType : this.state.sortType },
                    function(rowData) {

                        if (rowData.length == 0) rowData = [];
                        this.setState({
                            data : {
                                rows :    rowData,
                                columns : cols

                            }
                        });



                    }.bind(this));


            }


        }.bind(this));
    },



    handleSelect2(eventKey) {

        this.fetch(eventKey);
    },

    onClickSearch(event) {
        console.log("onSearchShow")
        this.searchForm = {};

        this.setState({
            searchShow : true
        });
    },

    hideSearch() {
        this.setState({searchShow: false});
    },



    doSearch() {
        console.log("form="+this.searchForm);
        this.setState({searchShow: false});

        this.fetch(1);
    },

    handleSubmit() {
       console.log(this.props.form);
    },

    printObj : function(obj) {
        for(var att in obj) {

            if(typeof(obj[att])!="function") {
                console.log("-----------------------------")
                console.log("att=" + att + " value = " + obj[att]);
               // console.log("-----------------------------")
            }
        }
    },

    handleFormChange(evt,evt2) {
        console.log("target="+evt.target.id+" :: "+evt.target.value+"::isselected:"+evt.target.checked);
        let id = evt.target.id;
        let value = evt.target.value;
        this.searchForm[id] = { value : value}
        if (evt.target.checked)
            this.searchForm[id].checked = evt.target.checked;
        //


      //  this.printObj(evt.target);
       // console.log("currentTarget="+evt.currentTarget);
       // this.printObj(evt.currentTarget);
    },



    rowClickEdit(row) {
        console.log("edit:"+row.id);
        this.setState({
            editShow : true,
            viewShow : false,
            editRow : row
        });
    },

    rowClickView(row) {
        console.log("view:"+row.id);
        this.setState({
            editShow : false,
            viewShow : true,
            editRow : row

        });
    },

    rowClickDelete(row) {
        if (confirm('确认删除?')) {
            $.get(this.props.ajaxMethod+"/deleteBObject",{javaClassName:this.props.javaClassName,BOjectId : row.id},
                function(result) {
                    console.log("result="+result);
                    this.fetch();



                }.bind(this));
        }


    },

    onClickRow(row) {
        this.setState({
            selectedRowId : row.currentTarget.id
        });

        if (this.props.onClickRow)
            this.props.onClickRow(this.state.data.rows[row.currentTarget.id])

    },
    onDoubleClickRow(tr) {
        var row = this.state.data.rows[tr.currentTarget.id];

        if (this.props.onDoubleClickRow) {
            this.props.onDoubleClickRow(row);
        }
        else
            this.rowClickView(row);
    },
    onRowCheckAll(row) {
        console.log("onRowCheckAll:todo");
        var checked = row.currentTarget.checked;
        for (var cb in this.refs) {
            if (cb.indexOf("check-") == 0) {
                this.refs[cb].checked = (checked);
                var rowData = this.state.data.rows[cb.slice(6,cb.length)];
                if (checked) {
                    if (this.selectedRows.indexOf(rowData) == -1)
                        this.selectedRows.push(rowData);
                }
                else
                    this.removeSelectedRow(rowData);

            }
        }
        console.log("seleted row length = "+this.selectedRows.length);
        if (this.props.onSelectRowChange)
            this.props.onSelectRowChange(this.selectedRows);
    },
    onRowCheckChange(row) {
        var rowData = this.state.data.rows[row.currentTarget.id];
        console.log("onRowCheckChange:"+rowData.id);
        if (row.currentTarget.checked) {
            this.selectedRows.push(rowData);
        } else {
            this.removeSelectedRow(rowData);
        }
        console.log("selected length = "+this.selectedRows.length);
        if (this.props.onSelectRowChange)
            this.props.onSelectRowChange(this.selectedRows);
    },
    onRowRadioChange(row) {
        console.log("onRowRadioChange:"+row.currentTarget.id+":"+row.currentTarget.checked);
    },

    removeSelectedRow(rowData) {
        var idx = -1;
        for (var i = 0; i < this.selectedRows.length; i++) {
            if (this.selectedRows[i].id == rowData.id) {
                idx = i;
                break;
            }
        }
        if (idx > -1)
            this.selectedRows.splice(idx,1);
    },

    cancelCU(){
        this.setState({editShow: false,viewShow : false});
        this.fetch(); //不保存的话，修改还是会改变bean对象，如果不刷新，则界面上会反应临时变化
    },
    closeCU(){
        this.setState({editShow: false,viewShow : false});
        this.fetch();
    },


    render() {
        //function TopToolTip({button,tooltip}) {
        //    return  <OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">{tooltip}</Tooltip>}>
        //        {button}
        //        </OverlayTrigger>
        //}
        return (

                 <div   style={{minHeight:Utils.tableHeight(),position:'relative'}}>
                     <ButtonToolbar>
                         {/* Standard button */}


                         {/* Provides extra visual weight and identifies the primary action in a set of buttons */}

                         {
                             (this.props.buttons.indexOf('add') > -1) ?
                                 ( <Button title="新增数据" {...Commons.Styles.Button.Add} onClick=
                                     {
                                        function(e){
                                            console.log("onclick:new");
                                            this.setState({
                                                editShow : true,
                                                editRow : {}
                                            });
                                        }.bind(this)
                                     }
                                 >
                                     新增
                                 </Button>) : null
                         }

                         {
                             (this.props.buttons.indexOf('delete') > -1) ?
                                 ( <ConfirmButton {...Commons.Styles.Button.Delete} onClick=
                                     {
                                        function(e){
                                            var ids = ""
                                            if (this.selectedRows.length == 0) return;
                                            for (var i = 0; i < this.selectedRows.length; i++) {
                                                var row = this.selectedRows[i];
                                                ids += row.id;
                                                if (i < this.selectedRows.length -1)
                                                    ids += ",";
                                            }

                                            $.post(this.props.ajaxMethod+"/deleteBObjects",{BOjectIds : ids,javaClassName:this.props.javaClassName},
                                                function(result) {
                                                    this.fetch();
                                                }.bind(this));

                                            console.log("onclick:delete:@todo!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                            //  alert("not implemented !")
                                            //this.setState({
                                            //    editShow : true,
                                            //    editRow : {}
                                            //});

                                            this.fetch();
                                        }.bind(this)
                                     }
                                                  msg={this.selectedRows.length == 0 ? null : "是否确认删除"+(this.selectedRows.length)+"条记录?"}
                                 >
                                     删除
                                 </ConfirmButton>) : null
                         }


                         &nbsp;&nbsp;&nbsp;
                         {this.props.extraButtons.map(function(button,idx){
                             button.props.xtable = this;
                             button.props.handleSuccess = function() {
                                 console.log("fetch");
                                 this.fetch();
                             }.bind(this);
                            return button;
                         }.bind(this))}

                         <div className="columns columns-right btn-group pull-right">
                             {
                                 (this.props.toolBars.indexOf('search') > -1) &&


                                         <Button onClick={
                                             function(e) {
                                                 if (this.state.searchTxt)
                                                     this.fetch(1);
                                             }.bind(this)
                                         }><Glyphicon glyph="search" /></Button>



                             }
                             {
                                 (this.props.toolBars.indexOf('filter') > -1) &&
                                     <OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">过滤</Tooltip>}>
                                         <Button title="过滤"><Glyphicon glyph="filter" onClick={this.onClickSearch}/></Button>
                                     </OverlayTrigger>

                             }
                             {
                                 (this.props.toolBars.indexOf('refresh') > -1) &&
                                     <OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">双击清除过滤条件</Tooltip>}>
                                         <Button title="刷新"><Glyphicon glyph="refresh"
                                            onClick={
                                             function(e) {
                                                this.fetch();
                                             }.bind(this)}
                                            onDoubleClick={
                                                 function(e) {
                                                    this.searchForm = {};
                                                    this.setState({searchTxt : ''});
                                                    this.fetch(1);
                                                 }.bind(this)
                                             }
                                         /></Button>
                                 </OverlayTrigger>
                             }
                             {

                                 (this.props.toolBars.indexOf('export') > -1) &&
                                 <OverlayTrigger   placement="top" overlay={<Tooltip id="tooltip">导出</Tooltip>}>
                                     <Button><Glyphicon glyph="export" onClick={this.export}/></Button>
                                     </OverlayTrigger>
                             }
                         </div>

                         {
                             (this.props.toolBars.indexOf('search') > -1) ?
                                 (<div className="pull-right search">
                                 <input className="form-control" type="text"
                                        onKeyPress={
                                            function(e){
                                                if (e.which == 13)
                                                    console.log(`onKeyPressEnter`);
                                                    this.fetch(1);
                                                }.bind(this)
                                         }
                                        onChange={
                                            function(e){
                                                this.setState({searchTxt :e.target.value });
                                                //this.state.searchTxt = e.target.value;
                                             }.bind(this)
                                         }
                                        value = {this.state.searchTxt}
                                        placeholder="请输入关键字"/>
                             </div>) : null
                         }

                     </ButtonToolbar>
                     <br/>
                     <Table responsive striped bordered condensed hover>
                        <thead>
                        <tr>
                            {this.props.selectType == 'none' ? null :
                                this.props.selectType == 'check' ?
                                    <td style={{"verticalAlign":"middle"}}><input  id='check-all' type="checkbox" onChange={this.onRowCheckAll}/></td>
                                    :
                                    (<th>#</th>)
                            }

                            {this.props.showNo ? <th>序号</th> : null}

                        {
                            this.state.data.columns.map(
                                function(column,idx){
                                    return <th style={{cursor : 'pointer'}}
                                        onClick={
                                            function(){
                                                if (this.state.sortColumnKey == this.viewColumnKeys[idx])
                                                    this.setState({sortType : this.state.sortType == 'asc' ? 'desc' : 'asc'});
                                                else
                                                    this.setState({sortColumnKey : this.viewColumnKeys[idx]});
                                                this.fetch(1);
                                            }.bind(this)
                                        } key={idx}>{column}   {this.state.sortColumnKey == this.viewColumnKeys[idx] && <Glyphicon style={{top : 2}} glyph={this.state.sortType == 'asc' ? "sort-by-attributes":"sort-by-attributes-alt"}/>}</th>;
                                 }.bind(this)
                            )
                        }
                        <th>操作</th>
                        </tr>
                        </thead>
                         <tbody>
                            {
                                this.state.data.rows.map(
                                    function(row,idx){
                                        if (!row.id) console.warn(`row object not have "id" property ,use row idx for row key`)
                                        return (
                                            <tr  key={row.id ? row.id : idx} onClick={this.onClickRow} onDoubleClick={this.onDoubleClickRow} id={idx} className = {idx == this.state.selectedRowId ? "success"  : null}>
                                                {this.props.selectType == 'check' ?
                                                    <td style={{"verticalAlign":"middle"}}><input  id={idx} type="checkbox"  ref={"check-"+idx} onChange={this.onRowCheckChange}/></td> :
                                                    (this.props.selectType == 'radio' ?
                                                        <td style={{"verticalAlign":"middle"}}><input  id={idx} type="radio"  onChange={this.onRowRadioChange}/></td> :
                                                            null)
                                                }


                                                {this.props.showNo ? <td style={{"verticalAlign":"middle"}}>{(this.state.page-1)*this.state.pageSize+ idx + 1}</td> : null}
                                                {
                                                    this.beanToRow(row)
                                                }
                                                <td style={{ verticalAlign:'middle'}} >
                                                    <ButtonToolbar   >
                                                        <ButtonGroup >
                                                            {
                                                                this.props.rowButtons.indexOf('view') > -1 ?
                                                                    <Glyphicon style={{cursor : 'pointer', marginLeft : '10px'}}  glyph="zoom-in"  onClick={(this.rowClickView).bind(this,row)}/> : null
                                                            }
                                                            {
                                                                this.props.rowButtons.indexOf('edit') > -1 ?
                                                                    <Glyphicon style={{cursor : 'pointer', marginLeft : '10px'}}  glyph="edit" onClick={(this.rowClickEdit).bind(this,row)}/>
                                                                    : null
                                                            }
                                                            {
                                                                this.props.rowButtons.indexOf('delete') > -1 ?
                                                                    <Glyphicon  style={{cursor : 'pointer', marginLeft : '10px'}} glyph="trash"  onClick={(this.rowClickDelete).bind(this,row)}/> : null
                                                            }


                                                            {

                                                                this.props.extraRowButtons.map(function (btn, idx) {
                                                                    btn.props.style = {cursor : 'pointer', marginLeft : '10px'};
                                                                    btn.props.row = row;
                                                                    btn.props.xtable = this;
                                                                    return <span  style={{cursor : 'pointer', marginLeft : '10px'}} > {btn}</span>;
                                                                }.bind(this))
                                                            }

                                                        </ButtonGroup>
                                                    </ButtonToolbar>
                                                </td>
                                            </tr>);
                                    }.bind(this)
                                )
                            }

                     </tbody></Table>

                     <div  style={{minHeight : "50px"  ,         lineHeight :  "50px"                    }} className="pagination-detail">
                          <span style={{position : "absolute", bottom : "10px",left : "10px","font-size": "14px"}}>

                                  当前&nbsp;{(this.state.page -1) * this.state.pageSize + 1}-
                              {this.state.page * this.state.pageSize > this.state.total ? this.state.total : this.state.page * this.state.pageSize }
                              ,&nbsp;&nbsp;总共&nbsp;{this.state.total}&nbsp;条记录

                        </span>
                         <span className="pull-right" style={{position : "absolute", bottom : "-25px",   right : "10px"}}>

                             <Pagination
                                 prev
                                 next
                                 first
                                 last
                                 ellipsis
                                 boundaryLinks
                                 items={ Math.ceil(this.state.total / this.state.pageSize)}
                                 maxButtons={3}
                                 activePage={this.state.page}
                                 onSelect={this.handleSelect2} >

                             </Pagination>
                             </span>
                         </div>








                     <Modal

                         show={this.state.searchShow}
                         onHide={this.hideSearch}
                         dialogClassName="custom-modal">
                         <Form horizontal onChange={this.handleFormChange}>
                         <Modal.Header closeButton>
                             <Modal.Title id="contained-modal-title-lg">查询</Modal.Title>
                         </Modal.Header>
                         <Modal.Body>


                                 {
                                     this.columnDescs.map
                                     (

                                         function(columnDesc,idx){
                                             if (columnDesc.searchType == 'NULLABLE' || columnDesc.searchType == 'REQUIRED') {

                                                 if (columnDesc.fieldType == 'DATE') {
                                                     return (
                                                     <FormGroup  key={idx} controlId={"control-"+columnDesc.key}>
                                                         <Col sm={2}>
                                                             {columnDesc.description}:
                                                         </Col>
                                                         <Col sm={4}>

                                                                <Datetime  onChange={function(e){
                                                                     this.handleFormChange({target : { id : columnDesc.key+"%from",value:e.format('YYYYMMDDHHmmss')}})
                                                                }.bind(this)} />

                                                         </Col>
                                                         <Col sm={1}>
                                                             -
                                                         </Col>
                                                         <Col sm={4}>
                                                             <Datetime  onChange={function(e){
                                                                     this.handleFormChange({target : { id : columnDesc.key+"%to",value: e.format('YYYYMMDDHHmmss')}})
                                                                }.bind(this)} />
                                                         </Col>
                                                     </FormGroup>);
                                                 }

                                                 if (columnDesc.values && columnDesc.values.length > 0) {
                                                        return (

                                                            <FormGroup  key={idx} controlId={"dics_"+columnDesc.key}>
                                                                <Col sm={2}>
                                                                    {columnDesc.description}:
                                                                </Col>
                                                                <Col sm={10}>
                                                                    {columnDesc.values.map(function(pair,idx){
                                                                        return (<Checkbox  key={idx} id={columnDesc.key+"*"+pair.key} inline value={pair.key}>
                                                                            {pair.label}
                                                                        </Checkbox>);

                                                                    })}
                                                                </Col>
                                                            </FormGroup>
                                                        );
                                                 }


                                                return (
                                                    <FormGroup  key={idx} controlId={columnDesc.key}>
                                                        <Col sm={2}>
                                                            {columnDesc.description}:
                                                        </Col>
                                                        <Col sm={10}>
                                                            <FormControl type="text" placeholder="" />
                                                        </Col>
                                                    </FormGroup>
                                                );
                                             }

                                         }.bind(this)
                                     )
                                 }





                         </Modal.Body>
                         <Modal.Footer>
                             <Button onClick={this.doSearch}>Search</Button>
                             <Button onClick={this.hideSearch}>Close</Button>
                         </Modal.Footer>
                         </Form>
                     </Modal>



                     {
                         (()=>{
                             var v = [];
                             let children = React.Children.map(this.props.children, function (child) {

                                 if (child.props.xtype =='editor') {
                                     v.push ('editor');
                                     return  React.cloneElement(child,
                                         {
                                             defaultCondition : this.props.defaultCondition,
                                             saveWithDefaultCondition : this.props.saveWithDefaultCondition,
                                             show :this.state.editShow,
                                             bean :this.state.editRow,
                                             handleClose : this.cancelCU,
                                             handleSuccess:this.closeCU
                                         });
                                 }
                                 else if (child.props.xtype =='editor/viewer') {
                                     v.push ('editor');
                                     v.push ('viewer');
                                     console.log("add child : "+child+" show="+child.props.show);
                                //     return child;
                                     return  React.cloneElement(child,
                                         {
                                             defaultCondition : this.props.defaultCondition,
                                             saveWithDefaultCondition : this.props.saveWithDefaultCondition,
                                             show : this.state.editShow||this.state.viewShow,
                                             edit : this.state.editShow,
                                             bean :this.state.editRow,
                                             handleClose : this.cancelCU,
                                             handleSuccess:this.closeCU
                                         });

                                 }
                                 else if (child.props.xtype =='viewer') {
                                     v.push = ('viewer');
                                     return  React.cloneElement(child,{show :this.state.viewShow,handleClose : this.cancelCU, handleSuccess:this.closeCU });

                                 } else {
                                     return  React.cloneElement(child,{xtable :this});
                                 }
                             }.bind(this));


                             if (v.length == 0) {
                            //     console.log("no child plugins ,show XEditor");
                                 return   <XEditor show={this.state.editShow || this.state.viewShow} edit={this.state.editShow}  javaClassName={this.props.javaClassName}
                                                   bean = {this.state.editRow}
                                                   handleClose={this.cancelCU}

                                                   handleSuccess={this.closeCU}
                                                   columnDescs={this.columnDescs}
                                                   saveWithDefaultCondition = {this.props.saveWithDefaultCondition}
                                                   defaultCondition = {this.props.defaultCondition}
                                 />;
                             } else {

                                 return children;
                             }

                             //if (this.props.showNo) {return (<th>??</th>)}
                         })()
                     }














            </div>
        );
    }
});

export default XTable;