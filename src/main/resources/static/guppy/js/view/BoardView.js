'use strict';

export default Backbone.View.extend({
    el : '#guppyBoard',

    events: {
        "click #boardWrite": "write"
    },

    initialize: function() {
        this.template = $('#board-template').html();

        this.render()
    },

    render () {

        var promise = $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "/api/board/list?page=1&size=2",
            type: "GET",
            dataType: 'json'
        });

        var that = this;

        promise.done(function (data) {
            console.log(data);

            Mustache.parse(that.template);
            var rendered  = Mustache.render(that.template, data);
            that.$el.html(rendered);
        });

        return this;
    },


    write () {
        var boardJson = {
            "title": "게시글1",
            "content": "게시글 첫번째 내용"
        };

        var promise = $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "/api/board/write",
            type: "POST",
            data: JSON.stringify(boardJson),
            dataType: 'json'
        });



        promise.complete(function (data) {
            if (data.status === 200) {
                // todo : floating modal view
                return;
            }

            if (data.status === 201) {
                render();
            }
        })
    }
});