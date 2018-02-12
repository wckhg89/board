import UserModel from '../model/UserModel'
import UserTemplate from '../../template/user-template.html'

'use strict';

export default Backbone.View.extend({

    el: '#guppyUser',

    userTpl : UserTemplate,

    initialize: function() {
        let self = this;
        this.model = new UserModel();

        this.model.fetch({
            success: function (options) {
                self.render();
            }, error: function (options) {
                self.render();
            }
        });
    },

    render () {
        console.log(this.model.toJSON());
        let rendered = this.userTpl({data: this.model.toJSON()});
        this.$el.html(rendered);

        return this;
    },

});