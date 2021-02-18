import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ProdFromSubCat: false
    },
    mutations: {},
    actions: {},
    getters:{
        ProdFromSubCat: state => state.ProdFromSubCat
    },
    modules: {}
});
