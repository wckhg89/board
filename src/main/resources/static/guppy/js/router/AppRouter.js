import UserView from "../view/UserView";
import BoardView from '../view/BoardView'

'use strict';

export default Backbone.Router.extend({
    routes: {
        "": "init",
        "/": "init",
    },

    init : function () {
        new UserView();
        new BoardView();
    }
});