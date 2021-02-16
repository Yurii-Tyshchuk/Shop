import Vue from "vue";
import VueRouter from "vue-router";
import MainText from "../components/MainText.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "MainText",
        component: MainText
    },
    {
        path: "/Catalog",
        name: "Catalog",
        component: () =>
            import("../components/catalog/Catalog.vue")
    },
    {
        path: "/About",
        name: "About",
        component: () =>
            import("../components/About.vue")
    },
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
