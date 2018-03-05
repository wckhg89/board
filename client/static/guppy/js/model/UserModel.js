
'use strict';

export default Backbone.Model.extend({
    url: 'http://13.125.34.108:8081/api/user/info',

    defaults() {
        return {
            userName: undefined,
            userProfileUrl: undefined
        }
    },

    sync : function(method, collection, options) {
        options.dataType = "jsonp";
        options.contentType = "application/javascript";
        return Backbone.sync(method, collection, options);
    },


    parse: function(data) {

        return data;
    },

    initialize() {

    },

    fetchModel: function () {
        let self = this;

        this.fetch({
            success: function (options) {
                self.trigger('renderUserInfo');
            }, error: function (options) {
                self.trigger('renderUserInfo');
            }
        });
    }
});