import React from 'react';
import FileUpload from 'react-fileupload'
import {Well,Button,Glyphicon,ProgressBar,ListGroup,ListGroupItem,Modal,ButtonToolbar,ButtonGroup,Collapse} from 'react-bootstrap'

const XFileImporter = React.createClass({
    getDefaultProps() {
        return {
            title : "导入",
            pollUrl: 'ajax/customer/poll',
            baseUrl : 'ajax/customer/upload',
            javaClassName : '',
            handleClose : function() {
            },
            handleSuccess : function(bean) {

            },
            bean : null
        };
    },
    getInitialState() {
        return {
            files :  [],
            logs : [],
            now : 0,
            showUploadButton : false,
            label : '',
            show : false

        };
    },

    group : '-1' ,

    handleClose() {
       this.setState({show : false});
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
                data:{ajax:"1",time:"10000",group:this.group,key : this.props.key},
                success:function(data,textStatus){
                    var _logs = this.state.logs;
                    var d_logs = data.logs;
                    for(var i = 0; i < d_logs.length; i++)
                        _logs.unshift(d_logs[i]);
                    //alert("ok!");
                    this.setState({
                        now : data.progress,
                        label : data.title,
                        logs : _logs
                    });
                    console.log("success#"+data);
                    if (data.progress != 100)
                        setTimeout(this.checkProcess,1000);
                }.bind(this)
                //,
                //complete:function(XMLHttpRequest,textStatus){
                //    if(XMLHttpRequest.readyState=="4"){
                //        console.log(XMLHttpRequest.responseText);
                //    }
                //}
                //,
                //error: function(XMLHttpRequest,textStatus,errorThrown){
                //    //$("#ajaxMessage").text($(this).text()+" out!")
                //    console.log("error:"+textStatus);
                //    this.polling = false;
                //}
            });


        //var xhr = $.ajaxSettings.xhr();
        //xhr.multipart = true;
        //xhr.open('GET','ajax/customer/poll',true);
        //xhr.onreadystatechange = function() {
        //    if (xhr.readyState == 4) {
        //        console.log("echo:"+xhr.responseText);
        //    } else  {
        //        console.log(xhr.readyState+":"+xhr.responseText);
        //    }
        //}
        //xhr.send(null);
    },

    render(){
        var _this = this;
        /*set properties*/
        const options = {
            baseUrl: this.props.baseUrl,
            multiple : this.props.multiple ? this.props.multiple : false,
                param: {
                fid: 0,
                key : this.props.key
            },

            chooseFile(files) {
                console.log("choose:"+files.length);
                var a = [];
                if (files.length > 0) {
                    for(var i = 0; i < files.length; i++)
                        a.push(files[i]);
                }
                _this.setState({
                    files : a,
                    now : 0,
                    showUploadButton : true,
                    logs : []
                });
            },
            beforeUpload : function(files,mill){
                console.log("beforeUpload:"+files.length);
                 if(files[0].size<1024*1024*20){
                    files[0].mill = mill

                     _this.setState({
                         showUploadButton : false

                     });
                    return true
                }
                return false
            },
            doUpload : function(files,mill){
                console.log('you just uploaded',typeof files == 'string' ? files : files[0].name)
            },
            uploading : function(progress){
                console.log('loading...',progress.loaded/progress.total+'%')
            },
            uploadSuccess : function(resp){
                console.log('upload success..!'+resp)
                _this.polling = true;
                _this.group = resp.group;
             //   _this.checkProcess();

                setTimeout(_this.checkProcess,1000);
            },
            uploadError : function(err){
                alert(err.message)
            },
            uploadFail : function(resp){
                alert(resp)
            }

        }
        /*Use FileUpload with options*/
        /*Set two dom with ref*/
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
                                <Well>
                                    {this.state.files.map(function(file,idx){
                                        return file.name+","
                                    })}
                                </Well>
                            </div>
                        </Collapse>
                        <FileUpload options={options}>
                            <Button  ref="chooseBtn"><Glyphicon glyph="folder-open" />. 选择文件</Button>

                            &nbsp;&nbsp;
                            <Button style={!this.state.showUploadButton ? {display: 'none'} : {display:'inline-block'}} ref="uploadBtn"><Glyphicon glyph="upload"/>
                                {"  "+(this.state.files.length > 0 ? this.state.files.length : 0) +"个文件"} 点击上传</Button>
                            {
                                //this.state.files.map(
                                //    function(file,idx){
                                //        return <div  key={file.name}>
                                //            <a>{file.name}</a><ProgressBar  key={idx} now={this.state.now} label={`  ${this.state.now}%`} />
                                //        </div>;
                                //    }.bind(this)
                                //)
                            }
                            <div>
                                <a>{this.state.label}</a><ProgressBar   now={this.state.now} label={`  ${this.state.now}%`} />
                            </div>


                        </FileUpload>
                        <div style={{height:"400px" ,overflow:"auto"}}>
                            <ListGroup>
                                {this.state.logs.map(function(log,idx){
                                    return <ListGroupItem  key={idx} bsStyle={log.level}>{log.info}</ListGroupItem>
                                })}
                            </ListGroup>

                        </div>



                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this.handleClose}>关闭</Button>
                    </Modal.Footer>

                </Modal>
            </div>
        )
    }
});

export default XFileImporter;