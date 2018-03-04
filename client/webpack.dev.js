const webpack = require('webpack');
const path = require('path');

module.exports = {
    devtool: 'inline-source-map',

    devServer: {
        historyApiFallback: true,
        host: "0.0.0.0",
        port: 3000,
        proxy: {
            "**": "http://localhost:8081"
        }

    },
    plugins: [
        new webpack.NamedModulesPlugin()
    ],
    output: {
        path: path.resolve(__dirname, 'static/guppy/dist'),
        publicPath: '/guppy/dist',
        filename: '[name].js'
    },

};