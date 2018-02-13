'use strict';

export default Backbone.Model.extend({
    url: '/api/user/info',

    defaults() {
        return {
            userName: undefined,
            userProfileUrl: undefined
        }
    },

    initialize() {

    }
});