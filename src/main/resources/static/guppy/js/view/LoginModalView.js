import LoginModalTemplate from '../../template/login-modal-template.html';

'use strict';

export default Backbone.View.extend({

    el: '#guppyLoginModal',
    loginModalTpl: LoginModalTemplate,

    initialize: function() {
        let view = this.render();

        view.$el.modal();


    },

    render () {
        let rendered = this.loginModalTpl();
        this.$el.html(rendered);

        return this;
    }

});