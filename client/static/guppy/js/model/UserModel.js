
'use strict';

export default Backbone.Model.extend({
    // url: 'http://hwjswedding.com:8081/api/user/info',
    url: 'http://localhost:8081/api/user/info',

    defaults() {
        return {
            userName: undefined,
            userProfileUrl: undefined
        }
    },

    // sync : function(method, model, options) {
    //     options.dataType = "jsonp";
    //     options.contentType = "application/javascript";
    //     return Backbone.sync(method, model, options);
    // },


    parse: function(data) {

        return data;
    },

    initialize() {

    },

    fetchModel: function () {
        let self = this;

        this.fetch({
            type: 'GET',
            success: function () {
                self.trigger('renderUserInfo');
            }, error: function () {
                self.trigger('renderUserInfo');
            }
        });
    }
});