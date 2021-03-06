import React from 'react';
import { Button,Glyphicon,Panel,Grid,Row,Col,Modal} from 'react-bootstrap'
import {Editor, EditorState,ContentState,RichUtils} from 'draft-js';
//import 'draft-js/dist/Draft.css'
//import '../../css/RichEditor.css'


export default class MessageEditor extends React.Component {

    constructor(props) {
        super(props);
        if (!props.message)
            this.state = {
                editorState: EditorState.createEmpty()
            };
        else
            this.state = {
                message : props.message ,editorState: EditorState.createWithContent(ContentState.createFromText(props.message.content))
            };
    //    this.focus = () => this.refs.editor.focus();
        this.onChange = (editorState) => {
            console.log(editorState);
            var plainText = editorState.getCurrentContent().getPlainText();
            //console.log(plainText);
            //var e = plainText.charAt(plainText.length-1);
            //
            //if (e == '\n')
            //   this.save();
            //console.log(editorState.getCurrentContent().getBlockMap());
           this.setState({editorState});
        }

        this.handleKeyCommand = (command) => this._handleKeyCommand(command);
        this.onTab = (e) => this._onTab(e);
        this.toggleBlockType = (type) => this._toggleBlockType(type);
        this.toggleInlineStyle = (style) => this._toggleInlineStyle(style);
    }

    save = ()=>{
        console.log("save");

        $.post("ajax/list/save",
            {id : (this.state.message ? this.state.message.id : null),
            content : this.state.editorState.getCurrentContent().getPlainText()},
            function(result) {
                if (result && this.props.onMessageSave)
                    this.props.onMessageSave(result);

                this.setState({
                    editorState: EditorState.createEmpty()
                });
            }.bind(this));
    }
    cancel = ()=>{
        this.state.message.edit = false;
        this.props.onMessageSave(this.state.message);
    }

    _handleKeyCommand(command) {
        const {editorState} = this.state;
        const newState = RichUtils.handleKeyCommand(editorState, command);
        if (newState) {
            this.onChange(newState);
            return true;
        }
        return false;
    }

    _onTab(e) {
        const maxDepth = 4;
        this.onChange(RichUtils.onTab(e, this.state.editorState, maxDepth));
    }

    _toggleBlockType(blockType) {
        this.onChange(
            RichUtils.toggleBlockType(
                this.state.editorState,
                blockType
            )
        );
    }

    _toggleInlineStyle(inlineStyle) {
        this.onChange(
            RichUtils.toggleInlineStyle(
                this.state.editorState,
                inlineStyle
            )
        );
    }

    focus = ()=> {
        if (this.state.editor)
            this.state.editor.focus();
    }

    render() {

        console.log("render");
        const {editorState} = this.state;

        // If the user changes block type before entering any text, we can
        // either style the placeholder or hide it. Let's just hide it now.
        let className = 'RichEditor-editor';
        var contentState = editorState.getCurrentContent();
        if (!contentState.hasText()) {
            if (contentState.getBlockMap().first().getType() !== 'unstyled') {
                className += ' RichEditor-hidePlaceholder';
            }
        }

        return (
            <div className="RichEditor-root">
                <BlockStyleControls
                    editorState={editorState}
                    onToggle={this.toggleBlockType}
                />
                <InlineStyleControls
                    editorState={editorState}
                    onToggle={this.toggleInlineStyle}
                />
                <div className={className} onClick={this.focus}>
                    <Editor
                        blockStyleFn={getBlockStyle}
                        customStyleMap={styleMap}
                        editorState={editorState}
                        handleKeyCommand={this.handleKeyCommand}
                        onChange={this.onChange}
                        onTab={this.onTab}
                        placeholder="随便写点什么..."
                        ref={function(editor){
                            this.state.editor = editor;
                      //      if (editor) editor.focus();
                        }.bind(this)}
                        spellCheck={true}
                    />
                </div>
                <Button onClick={this.save}>保存</Button><Button style={{marginLeft : 10}} onClick={this.cancel}>取消</Button>
            </div>
        );
    }



    //const styleMap = {
    //    CODE: {
    //        backgroundColor: 'rgba(0, 0, 0, 0.05)',
    //        fontFamily: '"Inconsolata", "Menlo", "Consolas", monospace',
    //        fontSize: 16,
    //        padding: 2,
    //    },
    //};


}

function getBlockStyle(block) {
    switch (block.getType()) {
        case 'blockquote': return 'RichEditor-blockquote';
        default: return null;
    }
}

class StyleButton extends React.Component {
    constructor() {
        super();
        this.onToggle = (e) => {
            e.preventDefault();
            this.props.onToggle(this.props.style);
        };
    }

    render() {
        let className = 'RichEditor-styleButton';
        if (this.props.active) {
            className += ' RichEditor-activeButton';
        }

        return (
            <span className={className} onMouseDown={this.onToggle}>
        {this.props.label}
      </span>
        );
    }
}

const styleMap = {
    CODE: {
        backgroundColor: 'rgba(0, 0, 0, 0.05)',
        fontFamily: '"Inconsolata", "Menlo", "Consolas", monospace',
        fontSize: 16,
        padding: 2,
    },
};
const BLOCK_TYPES = [
    {label: 'H1', style: 'header-one'},
    {label: 'H2', style: 'header-two'},
    {label: 'H3', style: 'header-three'},
    {label: 'H4', style: 'header-four'},
    {label: 'H5', style: 'header-five'},
    {label: 'H6', style: 'header-six'},
    {label: 'Blockquote', style: 'blockquote'},
    {label: 'UL', style: 'unordered-list-item'},
    {label: 'OL', style: 'ordered-list-item'},
    {label: 'Code Block', style: 'code-block'},
];

const BlockStyleControls = (props) => {
    const {editorState} = props;
    const selection = editorState.getSelection();
    const blockType = editorState
        .getCurrentContent()
        .getBlockForKey(selection.getStartKey())
        .getType();

    return (
        <div className="RichEditor-controls">
            {BLOCK_TYPES.map((type) =>
                <StyleButton
                    key={type.label}
                    active={type.style === blockType}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
    );
};
var INLINE_STYLES = [
    {label: 'Bold', style: 'BOLD'},
    {label: 'Italic', style: 'ITALIC'},
    {label: 'Underline', style: 'UNDERLINE'},
    {label: 'Monospace', style: 'CODE'},
];


const InlineStyleControls = (props) => {
    var currentStyle = props.editorState.getCurrentInlineStyle();
    return (
        <div className="RichEditor-controls">
            {INLINE_STYLES.map(type =>
                <StyleButton
                    key={type.label}
                    active={currentStyle.has(type.style)}
                    label={type.label}
                    onToggle={props.onToggle}
                    style={type.style}
                />
            )}
        </div>
    );
};