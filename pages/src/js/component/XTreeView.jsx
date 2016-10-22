import React from 'react';
import { Button,Glyphicon,Panel,Grid,Row,Col} from 'react-bootstrap'
import '../../lib/js/bootstrap-treeview.min';
import '../../lib/css/bootstrap-treeview.min.css'
const XTreeView = React.createClass({
    getDefaultProps() {

    },

    tree :  [
        {
            text: "Parent 1",
            nodes: [
                {
                    text: "Child 1",
                    nodes: [
                        {
                            text: "Grandchild 1"
                        },
                        {
                            text: "Grandchild 2"
                        }
                    ]
                },
                {
                    text: "Child 2"
                }
            ]
        },
        {
            text: "Parent 2"
        },
        {
            text: "Parent 3"
        },
        {
            text: "Parent 4"
        },
        {
            text: "Parent 5"
        }
    ],

    componentDidMount: function() {
        $('#tree').treeview({data: this.tree});
    },

    getInitialState() {
        return {
            selectedRole: null,
            permissions: [],
            showAdd: false
        };
    },

    render(){
        return <div id="tree"></div>
    }
});

export default XTreeView;