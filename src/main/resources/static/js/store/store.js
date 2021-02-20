import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ProdFromSubCat: false,
        IndexCat: 0,
        IndexSubCat: 0
    },
    mutations: {
        setIDCatAndSubCat(state, payload) {
            state.IndexSubCat = payload.indexSubCat;
            state.IndexCat = payload.indexCat;
            state.ProdFromSubCat = true;
        },
        isVisibleProd(state) {
            state.ProdFromSubCat = !state.ProdFromSubCat;
        }
    },
    actions: {},
    getters: {
        ProdFromSubCat: state => state.ProdFromSubCat,
        IDCat: state => state.IndexCat,
        IDSubCat: state => state.IndexSubCat,
    },
    modules: {}
});
