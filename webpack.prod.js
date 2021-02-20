const path = require('path');
const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'production',
    output: {
        filename: 'main.js',
        // src\main\resources\static\dist
        path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'dist')
    }
});
