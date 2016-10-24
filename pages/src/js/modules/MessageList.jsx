import React from 'react';
import MessageEditor from './MessageEditor.jsx'
//import SimpleMessageEditor from './SimpleMessageEditor.jsx'
import {  Button,Glyphicon,Panel,Grid,Row,Col,Modal} from 'react-bootstrap'
export default class MessageList extends React.Component {
    static defaultProps = {
        customerid : null
    };

    state = {
        messageList : [{content : 'asdfasdf',edit : false},{content : 'abcd',edit : false},
            {content : '12312213',edit : false},{content : 'asdfasdfd',edit : false}],

        currentMessage : null
    };

    componentWillMount() {
        this.refresh();
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
        return (<span>
             <MessageEditor onMessageSave={
                function(message) {
                    if (message) this.state.messageList.unshift(message);
                    this.setState({
                        messageList : this.state.messageList
                    });
                }.bind(this)
             } />
             <div>

                 {
                     this.state.messageList.map(function(row,idx){
                         return (row.edit ?


                             <span  style={{marginTop : 10}}  > <MessageEditor message={row}
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
                             /> </span>:

                             <div style={{marginTop : 10}}  bsStyle="info" onDoubleClick={function(){
                                   row.edit = true;
                                   if (this.state.currentMessage)
                                        this.state.currentMessage.edit = false;
                                   this.setState({messageList:this.state.messageList,currentMessage : row});
                             }.bind(this)}>
                                 {row.content}
                                 <div className="pull-right">
                                     <Glyphicon  style={{cursor : 'pointer', marginRight : '10px'}} glyph="trash"
                                                 onClick={(this.rowClickDelete).bind(this,row)}
                                     />
                                     {row.time && new Date(row.time).toLocaleString()}
                                 </div>
                             </div>)



                     }.bind(this))
                 }
             </div>
        </span>);
    }





}