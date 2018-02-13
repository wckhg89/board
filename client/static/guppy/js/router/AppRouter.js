import LayoutView from "../view/LayoutView";

'use strict';

export default Backbone.Router.extend({
    routes: {
        "": "init",
        "/": "init",
    },

    init : function () {
        new LayoutView();
    }
});