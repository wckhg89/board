import LoginModalView from "../view/component/LoginModalView";

'use strict';

export default Backbone.Model.extend({

    idAttribute: '_id',
    url: 'http://hwjswedding.com:8081/api/board/write',
    // url: 'http://localhost:8081/api/board/write',

    defaults() {
        return {
            _id: '',
            title: '',
            contents: '',
            createdAt: '',
            user_name: ''
        }
    },

    parse: function(data) {
        return data;
    },

    initialize() {
    },

    renderModal () {
        new LoginModalView();
    },

    saveBoard : function () {
        let self = this;
        this.save(null, {
            type: 'POST',
            success: function () {
                alert("축하메시지를 작성해주셔서\n감사합니다:D");
                self.trigger('recent');

            },
            error: function (model, resp) {
                if (resp.status === 401 || resp.status === 400) {
                    self.renderModal();
                }
            }
        });
    }
});