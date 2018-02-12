import BoardTemplate from '../../template/board-template.html';
import BoardCollection from '../collection/BoardCollection';

import LoginModalView from './LoginModalView'
import BoardModel from "../model/BoardModel";


'use strict';

export default Backbone.View.extend({
    el : '#guppyBoard',

    boardTpl: BoardTemplate,

    events: {
        'click #boardWrite': 'write',
        'click #boardMore': 'read'
    },

    // new View() 하면 자동 호출
    initialize: function() {
        this.collection = new BoardCollection();
        this.read(1, 5);
    },

    render () {
        let rendered = this.boardTpl({content: this.collection.toJSON()});
        this.$el.html(rendered);

        return this;
    },

    read (page, size) {
        let self = this;

        this.collection.fetch({
            data: {page: page, size: size},
            success: function (options) {
                self.render()
            }, error: function (options) {

            }
        });
    },


    write () {
        let newBoard = new BoardModel({
            title: 'Guppy',
            contents: 'Hung-Guppy',
            createdAt: moment.now()
        });

        newBoard.save(null, {
            type: 'POST',
            success: function (model, resp) {
                console.log(model)
            },
            error: function (model, resp) {
                if (resp.status === 401) {
                    new LoginModalView();
                }
            }
        });
    }
});