import BoardModel from '../model/BoardModel'

'use strict';

export default Backbone.Collection.extend({
    model : BoardModel,
    url: '/api/board/list',

    parse: function(response) {
        return response.content;
    }
});