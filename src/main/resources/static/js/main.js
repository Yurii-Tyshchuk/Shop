import Vue from 'vue'
import VueResource from 'vue-resource'
// import axios from "axios"
// import VueAxios from "vue-axios";

import App from "./App.vue"
import router from "./router/router";
import store from "./store/store";

import vuetify from './plugins/vuetify'

Vue.use(VueResource);

// Vue.use(VueAxios, axios);

Vue.prototype.$idList = {Cat: 0, SubCat: 0}

new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
}).$mount("#app");
