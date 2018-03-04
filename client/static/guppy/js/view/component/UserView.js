import UserModel from '../../model/UserModel'
import UserTemplate from '../../../template/component/user-template.html'

'use strict';

export default Backbone.View.extend({

    el: '#guppyUser',

    userTpl : UserTemplate,

    initialize: function() {
        let self = this;
        this.model = new UserModel();

        this.listenTo(this.model, 'renderUserInfo', this.renderUserInfo);

        this.model.fetchModel();

    },

    renderUserInfo () {
        let rendered = this.userTpl({data: this.model.toJSON()});
        this.$el.html(rendered);

        return this;
    },

});