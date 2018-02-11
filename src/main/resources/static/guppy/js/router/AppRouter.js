import BoardView from '../view/BoardView'

export default Backbone.Router.extend({
    routes: {
        "": "init",
    },

    init : function () {
        new BoardView().initialize();
    }
});