import BoardTemplate from '../../template/board-template.html';
import BoardCollection from '../collection/BoardCollection';


'use strict';
import BoardModel from "../model/BoardModel";

export default Backbone.View.extend({
    el : '#guppyBoard',

    boardTpl: BoardTemplate,

    events: {
        'click #boardWrite': 'write',
        'click #boardMore': 'read'
    },

    // new View() 하면 자동 호출
    initialize: function() {
        this.boardTpl = BoardTemplate;
        this.collection = new BoardCollection();

        this.listenTo(BoardModel, 'sync', this.handleModelSuccess);
        this.listenTo(BoardModel, 'error', this.handleModelError);

        this.listenTo(this.collection, 'sync', this.handleCollectionSuccess);
        this.listenTo(this.collection, 'error', this.handleCollectionError);

        this.read(1, 5);
    },


    handleModelSuccess: function (options) {
        // options will be any options you passed when triggering the custom event
        console.log('success', options)
    },

    handleModelError: function (options) {
        // options will be any options you passed when triggering the custom event
        console.log('error', options);
    },

    handleCollectionSuccess: function (options) {
        // options will be any options you passed when triggering the custom event
        this.render()
    },

    handleCollectionError: function (options) {
        // options will be any options you passed when triggering the custom event
        console.log('error', options);
    },

    render () {
        let rendered = this.boardTpl({content: this.collection.toJSON()});
        this.$el.html(rendered);

        return this;
    },

    read (page, size) {
        this.collection.fetch({ data : {page: page, size: size} });
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
                // todo login modal
                console.log(model)
            }
        });
    }
});