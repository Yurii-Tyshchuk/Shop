import Vue from "vue";
import VueRouter from "vue-router";
import MainText from "../views/MainText.vue";
import Catalog from "../views/Catalog.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        components: {
            default: MainText,
            two: () =>
                import("../views/Catalog.vue")
        }
    },
    {
        path: "/Catalog",
        name: "Catalog",
        component: () =>
            import("../views/Catalog.vue")
    },
    {
        path: "/About",
        name: "About",
        component: () =>
            import("../views/About.vue")
    },
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
