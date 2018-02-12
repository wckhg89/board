import LayoutTemplate from '../../template/layout-template.html';
import UserView from "./component/UserView";
import BoardView from "./component/BoardView";

'use strict';

export default Backbone.View.extend({
    el: '#guppyLayout',

    layoutTpl: LayoutTemplate,

    // new View() 하면 자동 호출
    initialize: function() {
        this.render();
        new UserView();
        new BoardView();
    },

    render () {
        let rendered = this.layoutTpl();
        this.$el.html(rendered);

        return this;
    },
});