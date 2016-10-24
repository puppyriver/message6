import React from 'react';
import MessageEditor from './MessageEditor.jsx'
//import SimpleMessageEditor from './SimpleMessageEditor.jsx'
import {  Button,Glyphicon,Panel,Grid,Row,Col,Pager,Modal,ListGroup,FormControl,ListGroupItem,ButtonToolbar,Pagination} from 'react-bootstrap'
import ToggleButton from '../component/ToggleButton.jsx'
export default class MessageList extends React.Component {
    static defaultProps = {
        customerid : null
    };

    state = {
        messageList : [{content : 'asdfasdf',edit : false,expand : false},{content : 'abcd',edit : false},
            {content : '12312213',edit : false},{content : 'asdfasdfd',edit : false}],


        currentMessage : null
    };

    componentWillMount() {
        this.refresh();

        $(window).scroll(function(){
            var scrollTop = $(this).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(this).height();
            if(scrollTop + windowHeight == scrollHeight){
                console.log("you are in the bottom");
            }
        });
    };

    refresh =()=>{
        $.post("ajax/list/all",{},
            function(result) {

                this.setState({
                    messageList : result
                });
            }.bind(this));
   }

    rowClickDelete = (row)=> {
        $.post("ajax/list/delete",{id : row.id},
            function(result) {
               this.refresh();
            }.bind(this));
    };

    render = ()=> {
        function getTitle(txt) {
            if (txt.indexOf('\n') > 0) {
                txt = txt.substr(0,txt.indexOf('\n'));
            }


            return txt.substr(0,txt.length > 50 ? 50 : txt.length);
        }
        return (<span>



             <ListGroup style={{marginTop : 10}}>
                 <MessageEditor onMessageSave={
                function(message) {
                    if (message) this.state.messageList.unshift(message);
                    this.setState({
                        messageList : this.state.messageList
                    });
                }.bind(this)
             } />

                 <ButtonToolbar  style={{marginTop : 10}}>
                     {/*<Pagination style={{display : 'inline'}}*/}
                                 {/*prev*/}
                         {/*next*/}
                         {/*first*/}
                         {/*last*/}
                         {/*ellipsis*/}
                         {/*boundaryLinks*/}
                         {/*items={ Math.ceil(this.state.total / this.state.pageSize)}*/}
                         {/*maxButtons={3}*/}
                         {/*activePage={this.state.page}*/}
                         {/*onSelect={this.handleSelect2} >*/}

                     {/*</Pagination>*/}

                     {/*<span style={{"font-size": "14px"}}>*/}
                     {/*Total : 1000*/}
                         {/*</span>*/}

                      <Pager  style={{display : 'inline-block', marginTop : 0}}>
                        <Pager.Item previous href="#">&larr; Previous Page</Pager.Item>
                          <FormControl  style={{display : 'inline-block', marginTop : 0,width : 40}} type="input" value={1}></FormControl>
                        <Pager.Item next href="#">Next Page &rarr;</Pager.Item>
                      </Pager>
                     <div className="columns columns-right btn-group pull-right">

                         <Button onClick={
                                             function(e) {

                                             }.bind(this)
                                         }><Glyphicon glyph="search" /></Button>

                         {
                             this.state.messageList.length > 0 &&
                             <ToggleButton bol={!this.state.messageList[0].expand} onClick={
                         function(e) {

                            this.state.messageList.map(function(row,idx){
                                row.expand = !row.expand;
                            });


                             console.log("expand:false");
                             this.setState({messageList : this.state.messageList});
                         }.bind(this)}>
                                 <Button bol={false}> <Glyphicon glyph="minus"/></Button>
                                 <Button bol={true}> <Glyphicon glyph="plus"/></Button>
                             </ToggleButton>
                         }
                     </div>
                     <div className="pull-right search">
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
                     </div>
                 </ButtonToolbar>

                 {
                     this.state.messageList.map(function(row,idx){
                         return (row.edit ?


                             <div  style={{marginTop : 10}}  >

                                 <MessageEditor message={row}
                                ref={function(messageEditor){
                                    if (messageEditor)
                                        messageEditor.focus();
                                }}

                               onMessageSave={
                                    function(message) {
                                        this.state.messageList[idx] = message;
                                        this.setState({
                                            messageList : this.state.messageList
                                        });
                                    }.bind(this)
                               }
                             /> </div>:

                             <ListGroupItem style={{marginTop : 10,wordBreak:"break-all",wordWrap:"break-word"}}  bsStyle="info">

                                 <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}}  glyph="pencil"
                                     onClick={function(){
                                       row.edit = true;
                                       if (this.state.currentMessage)
                                            this.state.currentMessage.edit = false;
                                       this.setState({messageList:this.state.messageList,currentMessage : row});
                                    }.bind(this)}
                                 />

                                 {
                                     row.expand ?
                                         <pre style={{display : 'inline-block'}}>
                                            {row.content}
                                        </pre> :

                                         getTitle(row.content)
                                 }

                                 <div className="pull-right">
                                     <span style={{marginRight:'10px'}}>{row.time && new Date(row.time).toLocaleString()}</span>
                                     <ToggleButton bol={!row.expand} onClick= {
                                         function() {
                                                row.expand = !row.expand;
                                             this.setState({messageList : this.state.messageList});
                                         }.bind(this)}>
                                           <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}}  bol={false} glyph="minus" />
                                           <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}}  bol={true} glyph="plus" />
                                     </ToggleButton>
                                     <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}} glyph="trash"
                                                 onClick={(this.rowClickDelete).bind(this,row)}
                                     />

                                     <ToggleButton bol={!(row.status == 1)} onClick= {
                                         function() {
                                                        var ns = row.status == 1 ? 0 : 1;
                                                        $.post("ajax/list/save",
                                                        {
                                                            id : row.id,status : ns
                                                        },
                                                        function(result) {
                                                            row.status = ns;
                                                            this.setState({messageList : this.state.messageList})
                                                            //this.setState({
                                                            //    roleOptions : result
                                                            //});
                                                        }.bind(this));

                                         }.bind(this)}>
                                         <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}}  bol={false} glyph="star" />
                                         <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}}  bol={true} glyph="star-empty" />
                                     </ToggleButton>

                                 </div>
                             </ListGroupItem>)



                     }.bind(this))
                 }
             </ListGroup>
        </span>);
    }





}