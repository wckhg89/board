'use strict';

export default Backbone.Model.extend({

    defaults() {
        return {
            title: '',
            completed: false
        }
    },

    initialize() {
        if (!this.get('title')) {
            this.set({ 'title': this.defaults().title });
        }
    },

    toggle() {
        this.save({ completed: !this.get('completed') });
    },

    clear() {
        this.destroy();
    }

})