
import React from 'react';
import {Tabs,Tab,Button,Glyphicon,ProgressBar,FormControl,ListGroup,ListGroupItem,Collapse,Well,Modal,ButtonToolbar,ButtonGroup,Checkbox} from 'react-bootstrap'
import Select from 'react-select';
import 'react-select/dist/react-select.css';




const XFileUploader = React.createClass({
    getDefaultProps() {
        return {
            title : "导入",
            pollUrl: 'ajax/customer/poll',
            baseUrl : 'ajax/customer/upload',
            cancelUrl : 'ajax/customer/cancel',
            javaClassName : '',
            handleClose : function() {
            },
            handleSuccess : function(bean) {
                console.log("xfileuploader:handlesuccess");
            },
            bean : null,
            configs : ['fast']
        };
    },
    getInitialState() {
        return {
            paras : {
                fastInsert : false
            },
            files :  [],
            logs : {},
            results : [],
            now : 0,
            showUploadButton : false,
            label : '',
            show : false,
            success : 0,
            fail : 0,
            group : null

        };
    },



    componentWillMount: function() {

    },




    handleClose() {
        this.setState({show : false});
        if (this.props.handleSuccess)
            this.props.handleSuccess();
    },
    handleShow() {
        this.setState({show : true});
    },
    polling : false,
    checkProcess() {
        if (!this.polling) return;

        $.ajax({
            type:"POST",
            url:this.props.pollUrl,
            //     dataType:"json",
            timeout:100000,
            data:{ajax:"1",time:"10000",group:this.state.group,key : this.props.key},
            success:function(data,textStatus){
                var _logs = this.state.logs;
                var _results = this.state.results;
                var events = data ? data.logs : null;
                if (data) {
                    if (events) {
                        for (var i = 0; i < events.length; i++) {
                            if (!events[i]) continue;
                            if (!events[i].serialId || events[i].serialId == '')
                                _results.unshift(events[i]);
                            else {
                                var serialId = events[i].serialId;
                                var level = events[i].level;
                                if (!_logs[serialId])  _logs[serialId] = {count: 0, success : {count : 0,data: []}, info : {count : 0,data: []}, warning : {count : 0,data: []}, danger : {count : 0,data: []}};
                                _logs[serialId][level].data.unshift(events[i]);
                                _logs[serialId][level].count++;
                                _logs[serialId].count++;
                                if (_logs[serialId][level].data.length > 200) _logs[serialId][level].data.pop();
                            }

                        }
                        console.log(`get _results size = `+_results.length);

                        //alert("ok!");
                        this.setState({
                            now : data.progress,
                            fail : data.fail,
                            success : data.success,
                            label : '正在处理文件:'+data.title+" 成功: "+data.success+",失败:"+data.fail,
                            logs : _logs,
                            results : _results
                        });

                    } else {
                        //alert("ok!");
                        this.setState({
                            now : data.progress > 100 ? 100 : data.progress,  //可能返回1000，标记全部结束，不再poll,其实可以直接判断100，但可能有bug
                            fail : data.fail,
                            success : data.success,
                            label : '正在处理文件:'+data.title+" 成功: "+data.success+",失败:"+data.fail
                        });
                    }

                }
                if (!data || data.progress <= 100)
                    setTimeout(this.checkProcess,100);
                else {
                    this.setState({
                        label : '导入结束'+" 成功: "+data.success+",失败:"+data.fail
                    });
                }
            }.bind(this)

        });



    },

    render(){
        var _this = this;

        var _fileOptions = [];
        var _fileValues = [];
        for (var i = 0; i < this.state.files.length; i++) {
            var file = this.state.files[i];
            _fileOptions.push({value : file.name ,label : file.name});
            _fileValues.push(file.name);
        }
        /*set properties*/
        return (
            <div style={{display : "inline"}}>
                <Button style={{marginLeft : '5px'}} bsStyle="primary" onClick={this.handleShow}>{this.props.title}</Button>
                <Modal

                    show={this.state.show}
                    onHide={this.handleClose}
                    dialogClassName="custom-modal">

                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-lg">{this.props.title}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Collapse in={this.state.files.length > 0}>
                            <div>
                                <Select
                                    multi={true}
                                    name="form-field-name"
                                    value={_fileValues}
                                    options={_fileOptions}
                                    onChange={function(e){
                                        var _files = this.state.files;
                                        var _nfiles = [];
                                        for (var i = 0; i < _files.length; i++) {
                                          var file = _files[i];
                                          if ((e+",").indexOf(file.name+",") > -1) {
                                            _nfiles.push(file);
                                          }
                                        }
                                        this.setState({files : _nfiles});
                                    }.bind(this)}
                                    placeholder={this.props.edit ? "请选择...":""}
                                />
                            </div>
                        </Collapse>



                        <form id="uploadForm" enctype="multipart/form-data">
                            {/*<input id="file" type="file" name="file" multiple/>*/}
                            <input id="file" type="file" name="file" multiple style={{display:"none"}}
                                   onInput={function(e){
                                       console.log('onInput:'+e);
                                   }}
                                   onChange={function(e){
                                       console.log('onChange:'+e.target.value);
                                   }}
                                   onSelectionchange={function(e){
                                       console.log('onSelectionchange:'+e);
                                   }}
                            />

                                    {/*<input id="photoCover" className="input-large" type="text" style={{height:"30px"}}/>*/}

                                    <Button  bsStyle="success"  onClick={function(){


                                        $('#file').replaceWith('<input id="file" type="file"  name="file" multiple style=\"display:none\" />');
                                        $('#file').on("change",function(e){
                                            console.log('onChange:'+e.target.value);
                                            var files = e.target.files;
                                            console.log("choose:"+files.length);
                                            var a = [];
                                            var category = {count : 0, success : { count : 0, data : []},warning : { count : 0, data : []},info : { count : 0, data : []},danger : { count : 0, data : []}};
                                            var logs = {};
                                            if (files.length > 0) {
                                                for(var i = 0; i < files.length; i++) {
                                                    a.push(files[i]);
                                                    logs[files[i].name] = category;
                                                }

                                            }
                                            this.setState({
                                                files : a,
                                                now : 0,
                                                fail : 0,
                                                success : 0,
                                                showUploadButton : true,
                                                logs : logs,
                                                results :[]
                                            });
                                        }.bind(this));

                                        $('input[id=file]').click();

                                    }.bind(this)}>浏览文件</Button>

                            <Button style={{ marginLeft : 10}} disabled={!this.state.showUploadButton} bsStyle="primary"  onClick={function(){
                                var _this = this;
                                this.setState({showUploadButton : false});
                                var p = "?";
                                for (var key in this.state.paras) {
                                    p += key +"="+this.state.paras[key]+"&";
                                }
                                p+="s=s"

                                if (this.state.paras.fastInsert) {
                                //    if (!confirm("警告！！！即将清除所有历史数据,是否继续？"))
                                 //       return;
                                }
                                var form = $('#uploadForm')[0];
                                var data = new FormData(form);
                                data.delete("file");
                                for (var i = 0; i < this.state.files.length; i++) {
                                  var file = this.state.files[i];
                                  data.append("file",file);
                                }

                                $.ajax({
                                    url: this.props.baseUrl+p,
                                    type: 'POST',
                                    cache: false,
                                    data: data,
                                    processData: false,
                                    contentType: false,
                                    xhr: function(){        //这是关键  获取原生的xhr对象  做以前做的所有事情
                                        var xhr = jQuery.ajaxSettings.xhr();
                                        var idx = -1;
                                        xhr.upload.onload = function (){
                                            console.log('=====finish uploading')

                                        }
                                        xhr.upload.onloadstart = function(evt1,evt2) {
                                            console.log(evt1);
                                            console.log(evt2);
                                            idx ++;
                                        }
                                        xhr.upload.onprogress = function (ev) {
                                         console.log('======onprogress')
                                            if(ev.lengthComputable) {
                                                var percent = 100 * ev.loaded/ev.total;
                                                console.log(percent,ev)

                                                this.setState({
                                                    now : parseInt(percent),
                                                    label : '正在上传文件:'+this.state.files[idx].name+" "+parseInt(ev.loaded / 1024)+"kb/"+parseInt(ev.total/1024)+"kb"
                                                 });
                                            }
                                        }.bind(this)
                                        return xhr;
                                    }.bind(this)
                                }).done(function(res) {
                                    console.log('done:'+res);
                                    _this.polling = true;
                                    _this.setState({group :  res.group});
                                 //   _this.group =;
                                    setTimeout(_this.checkProcess,100);
                                }).fail(function(res) {

                                });


                            }.bind(this)}>上传{this.state.files.length}个文件</Button>

                            {
                                this.state.group && this.props.cancelUrl && <Button  style={{ marginLeft : 10}}  bsStyle="danger"
                                                                                        onClick={
                                                                                            function(){
                                                                                                  $.ajax({
                                                                                                    type:"POST",
                                                                                                    url:this.props.cancelUrl,
                                                                                                    //     dataType:"json",
                                                                                                    //timeout:100000,
                                                                                                    data:{ajax:"1",group:this.state.group,key : this.props.key},
                                                                                                    success:function(data,textStatus){}
                                                                                                    }
                                                                                                  );
                                                                                            }.bind(this)}>取消</Button>
                            }

                            {
                                this.props.configs.indexOf('fast') > -1 ? <Checkbox style={{marginLeft : 10}} checked={this.state.paras.fastInsert} inline onChange={function(e){
                                    var b = e.target.checked;
                              //      if (confirm('全量快速导入模式，导入前会清空数据，可能造成数据丢失,是否确定使用？')) {
                                        var paras = this.state.paras;
                                        paras.fastInsert = b;
                                        this.setState({paras: paras});
                               //     }
                                }.bind(this)}>
                                    删除存量数据
                                </Checkbox> : null
                            }


                        </form>




                        <br/>

                        <div>
                            <a>{`  ${this.state.now}%     `+this.state.label}</a>
                            {/*<ProgressBar   now={this.state.now} label={`  ${this.state.now}%`} />*/}


                            <ProgressBar label={`  ${this.state.now}%`} >
                                <ProgressBar striped bsStyle="success" now={this.state.now * this.state.success / (this.state.success+ this.state.fail)} key={1} />
                                <ProgressBar active bsStyle="danger" now={this.state.now - (this.state.now * this.state.success / (this.state.success+ this.state.fail))} key={2} />
                            </ProgressBar>
                        </div>

                        <Tabs defaultActiveKey={-11} id="tabs">
                            <Tab eventKey={-11} title={"日志"+(this.state.results  ? "("+ this.state.results.length +")" : "" )}>
                                <div style={{border : 5  ,height : 300,overflow:"auto"}}>
                                    <ListGroup style={{fontSize : 10}}>
                                        {this.state.results.map(function(log,idx){
                                            var dt = new Date();
                                            dt.setTime(log.time);
                                            return <ListGroupItem  key={idx} bsStyle={log.level}>{"<"+dt.toLocaleTimeString()+">"+log.info}</ListGroupItem>
                                        })}
                                    </ListGroup>

                                </div>
                            </Tab>
                            {
                                this.state.files.map(function(file,idx){
                                    var category = this.state.logs[file.name];
                                    if (category.count == 0) return null;
                                    return (<Tab eventKey={idx} title={file.name+(category ? "("+ category.count +")" : "" )}>
                                        <div style={{border : 5  ,height : 300,overflow:"auto"}}>
                                            <Tabs defaultActiveKey={(category.danger.count > 0 ) ?'danger'
                                                : ((category.warning.count > 0 ? 'warning' : (
                                                category.info.count > 0 ? 'info' :(
                                                    category.success.count > 0 ? 'success' : ''
                                                )
                                            )) )
                                            }>


                                                { (category.danger.count > 0 ) ?
                                                <Tab eventKey={'danger'} title= {'danger'+(category.danger.count > 0 ? "("+category.danger.count+")" : "")}>
                                                    <ListGroup style={{fontSize : 10}}>
                                                        {
                                                            category ?
                                                                category.danger.data.map(function(log,idx){
                                                                    var dt = new Date();
                                                                    dt.setTime(log.time);
                                                                    return <ListGroupItem  key={idx} bsStyle={log.level}>{"<"+dt.toLocaleTimeString()+">"+log.info}</ListGroupItem>
                                                                }) : null
                                                        }
                                                    </ListGroup>
                                                </Tab> : null
                                                }

                                                { (category.warning.count > 0 ) ?
                                                    <Tab eventKey={'warning'} title= {'warning'+(category.warning.count > 0 ? "("+category.warning.count+")" : "")}>
                                                        <ListGroup style={{fontSize : 10}}>
                                                            {
                                                                category ?
                                                                    category.warning.data.map(function(log,idx){
                                                                        var dt = new Date();
                                                                        dt.setTime(log.time);
                                                                        return <ListGroupItem  key={idx} bsStyle={log.level}>{"<"+dt.toLocaleTimeString()+">"+log.info}</ListGroupItem>
                                                                    }) : null
                                                            }
                                                        </ListGroup>
                                                    </Tab> :null
                                                }

                                                { (category.info.count > 0 ) ?
                                                    <Tab eventKey={'info'} title= {'info'+(category.info.count > 0 ? "("+category.info.count+")" : "")}>
                                                        <ListGroup style={{fontSize : 10}}>
                                                            {
                                                                category ?
                                                                    category.info.data.map(function(log,idx){
                                                                        var dt = new Date();
                                                                        dt.setTime(log.time);
                                                                        return <ListGroupItem  key={idx} bsStyle={log.level}>{"<"+dt.toLocaleTimeString()+">"+log.info}</ListGroupItem>
                                                                    }) : null
                                                            }
                                                        </ListGroup>
                                                    </Tab> :null
                                                }


                                                { (category.success.count > 0 ) ?
                                                    <Tab eventKey={'success'}
                                                         title={'success' + (category.success.count > 0 ? "(" + category.success.count + ")" : "")}>
                                                        <ListGroup style={{fontSize: 10}}>
                                                            {
                                                                category ?
                                                                    category.success.data.map(function (log, idx) {
                                                                        var dt = new Date();
                                                                        dt.setTime(log.time);
                                                                        return <ListGroupItem key={idx}
                                                                                              bsStyle={log.level}>{"<" + dt.toLocaleTimeString() + ">" + log.info}</ListGroupItem>
                                                                    }) : null
                                                            }
                                                        </ListGroup>
                                                    </Tab> :null
                                                }

                                            </Tabs>


                                        </div>
                                    </Tab>);
                                }.bind(this))
                            }
                        </Tabs>








                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this.handleClose}>关闭</Button>
                    </Modal.Footer>

                </Modal>
            </div>
        )
    }
});

export default XFileUploader;