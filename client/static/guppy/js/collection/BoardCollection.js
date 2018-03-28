import BoardModel from '../model/BoardModel'

'use strict';

export default Backbone.Collection.extend({
    model : BoardModel,
    url: 'http://hwjswedding.com:8081/api/board/list',
    // url: 'http://localhost:8081/api/board/list',

    initialize:function () {
        this.totalCount = 0;
        this.totalPages = 0;
        this.currentPage = 0;
        this.last = false;
        this.page = 0;
        this.size = 5;
        this.sort = "created_at";
        this.dir = "desc";
    },



    parse: function(data) {
        this.totalCount = data.totalElements;
        this.totalPages = data.totalPages;
        this.last = data.last;
        this.currentPage = data.last ? data.number : data.number + 1;

        return data.content;
    },

    fetchPage:function (dir) {
        let self = this;
        let data = {
            page: 0,
            size: 5,
            sort:this.sort + "," + dir
        };

        this.fetch({
            reset: true,
            data: data,
            success: function () {
                self.trigger('renderList');
            },
            error : function () {
                console.log("@");
            }
        });
    },

    fetchMorePage:function () {
        let self = this;

        if (this.last) {
            return
        }

        let data = {
            page:this.currentPage,
            size:this.size,
            sort:this.sort + "," + this.dir
        };

        this.fetch({
            change: true,
            data: data,
            success: function (collection, response, options) {
                self.trigger('appendList');
            }
        });
    }
});