import 'bootstrap/dist/css/bootstrap.css';
import '../css/btn-social.css';
import '../css/font-social.css';

import 'expose-loader?moment!moment';

import AppRouter from "./router/AppRouter";

new AppRouter();
Backbone.history.start();

