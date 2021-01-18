import Vue from 'vue'
import VueResource from 'vue-resource'
import App from "./App.vue"

Vue.use(VueResource)
Vue.prototype.$idList = {Cat: 0, SubCat: 0}

new Vue({
    el: '#app',
    render: a => a(App)
})
