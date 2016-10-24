/* global __dirname */

var path = require('path');

var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');

var dir_js = path.resolve(__dirname, 'src/js/entry');
var dir_html = path.resolve(__dirname, '../server/webapp');
var dir_build = path.resolve(__dirname, '../server/webapp');

module.exports = {

    //entry: path.resolve(dir_js, 'test.jsx'),
    entry : {
        "app" :  [path.resolve(dir_js, 'app.jsx')]
        //"customer" : [path.resolve(dir_js, 'customer.jsx')],
        // "users" :[path.resolve(dir_js, 'users.jsx')]
    },

    //entry: [
    //    'webpack-dev-server/client?http://localhost:9090',
    //    'webpack/hot/only-dev-server'
    //    ,path.resolve(dir_js, 'test.jsx')],
    output: {
        path: dir_build,
        filename: '[name].js'
    },
    devServer: {
        contentBase: dir_build,
        hot: true,
        inline: true,
        proxy: {
            '/ajax/*': 'http://127.0.0.1:8080'
        }
    },
    module: {
        loaders: [
            {
                loader: 'react-hot',
                test: dir_js,
            },
            {
                loader: 'babel-loader',
                test: /\.jsx?$/,
                query: {
                    presets: ['es2015', 'react', 'stage-0'],
                },
            },
            {
                loader : 'style-loader!css-loader',
                test : /\.css$/,
            }
        ]
    },
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            output: {
                comments: false,
            },
            compress: {
                //supresses warnings, usually from module minification
                warnings: false
            }
        }),
        // Simply copies the files over
        new CopyWebpackPlugin([
            { from: dir_html } // to: output.path
        ]),
        // Avoid publishing files when compilation fails
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify("production")
            }
        })

    ],
    stats: {
        // Nice colored output
        colors: true
    },
    cache: true,

    externals: {
        'react': 'React',
        'react-dom': 'ReactDOM',
        'draft-js': 'Draft',
        'react-bootstrap': 'ReactBootstrap'
    },
// Create Sourcemaps for the bundle
//devtool : ['eval']
//  devtool : ['cheap-module-source-map']
 devtool: 'source-map',
};


