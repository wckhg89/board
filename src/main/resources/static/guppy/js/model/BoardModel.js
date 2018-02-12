

'use strict';

export default Backbone.Model.extend({

    idAttribute: '_id',
    url: '/api/board/write',

    defaults() {
        return {
            _id: '',
            title: '',
            contents: '',
            createdAt: ''
        }
    },

    initialize() {

    }
});