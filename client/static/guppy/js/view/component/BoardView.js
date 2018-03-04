import BoardTemplate from '../../../template/component/board-template.html';
import BoardListTemplate from '../../../template/component/board-list-template.html';
import BoardCollection from '../../collection/BoardCollection';
import LoginModalView from './LoginModalView'

import BoardModel from "../../model/BoardModel";

'use strict';

export default Backbone.View.extend({
    el : '#guppyBoard',
    boardTpl: BoardTemplate,
    boardListTpl : BoardListTemplate,
    events: {
        'click #boardWrite': 'write',
        'click #boardMore': 'more',
        'click #boardRecent': 'recent',
        'click #boardOlder': 'older'
    },

    // new View() 하면 자동 호출
    initialize: function() {
        this.render();
        this.collection = new BoardCollection();
        this.collection.fetchPage("desc");

        this.listenTo(this.collection, 'renderList', this.renderList);
        this.listenTo(this.collection, 'appendList', this.appendList);

        this.listenTo(this.model, 'recent', this.recent);
        this.listenTo(this.model, 'renderModal', this.renderModal);


    },

    renderModal () {
        new LoginModalView();
    },

    render () {
        let rendered = this.boardTpl();
        this.$el.html(rendered);

        return this;
    },

    appendList () {
        let rendered = this.boardListTpl({content: this.collection.toJSON()});
        this.$el.find('#boardList').append(rendered);
    },

    renderList () {
        let rendered = this.boardListTpl({content: this.collection.toJSON()});
        this.$el.find('#boardList').html(rendered);
    },

    more () {
        this.collection.fetchMorePage();
    },

    recent () {
        let $boardRecent = this.$el.find('#boardRecent');
        let $boardOlder = this.$el.find('#boardOlder');

        $boardRecent.addClass("active");
        $boardOlder.hasClass("active") ? $boardOlder.removeClass("active") : null;

        this.collection.fetchPage("desc");
    },

    older () {

        let $boardRecent = this.$el.find('#boardRecent');
        let $boardOlder = this.$el.find('#boardOlder');

        $boardOlder.addClass("active");
        $boardRecent.hasClass("active") ? $boardRecent.removeClass("active") : null;

        this.collection.fetchPage("asc");
    },

    write () {
        let self = this;
        this.model = new BoardModel({
            title: $("#boardText").val(),
            contents: null,
            createdAt: moment.now()
        });

        this.model.saveBoard();

        // newBoard.save(null, {
        //     type: 'POST',
        //     success: function (model, resp) {
        //         alert("축하메시지를 작성해주셔서\n감사합니다:D");
        //         self.recent();
        //
        //     },
        //     error: function (model, resp) {
        //         if (resp.status === 401) {
        //             new LoginModalView();
        //         }
        //     }
        // });
    }
});