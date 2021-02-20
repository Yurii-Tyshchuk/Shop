import Vue from "vue";
import VueRouter from "vue-router";
import MainText from "../views/MainText.vue";
import Catalog from "../views/Catalog.vue";
import About from "../views/About.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        components: {
            default: MainText,
            two: Catalog
        }
    },
    {
        path: "/Catalog",
        name: "Catalog",
        component: Catalog
    },
    {
        path: "/About",
        name: "About",
        component: About
    },
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
