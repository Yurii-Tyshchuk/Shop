import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ProdFromSubCat: false,
        ProdFromCat: false,
        IndexCat: 0,
        IndexSubCat: 0,
    },
    mutations: {
        setIDCatAndSubCat(state, payload) {
            state.IndexSubCat = payload.indexSubCat;
            state.IndexCat = payload.indexCat;
            state.ProdFromSubCat = true;
            state.ProdFromCat = false
        },
        isVisibleProd(state) {
            state.ProdFromSubCat = !state.ProdFromSubCat;
        },
        setIDCat(state,payload){
            state.IndexCat = payload.indexCat;
            state.ProdFromSubCat = false;
            state.ProdFromCat = true
        }
    },
    actions: {},
    getters: {
        ProdFromSubCat: state => state.ProdFromSubCat,
        ProdFromCat: state => state.ProdFromCat,
        IDCat: state => state.IndexCat,
        IDSubCat: state => state.IndexSubCat,
    },
    modules: {}
});
