<template>
    <div>
        <nav class="navbar navbar-expand-md navbar-dark bg-primary">
            <div class="navbar-collapse collapse justify-content-center order-2" id="collapsingNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <button class="nav-link text-dark" @click="getMainText">Главная</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link text-dark" @click="getCatalog">Каталог</button>
                    </li>
                    <li class="nav-item">
                        <button id="cCat" class="nav-link text-dark" @click="getContacts">Контакты</button>
                    </li>
                </ul>
            </div>
        </nav>
        <component v-bind:is="mainComponent"></component>
    </div>
</template>

<script>
    import Catalog from "./catalog/Catalog.vue";
    import MainText from "./MainText.vue";
    import Cont from "./Сont.vue";

    export default {
        name: "Header",
        data() {
            return {
                mainComponent: MainText
            }
        },
        components: {
            MainText,
            Catalog,
            Cont
        },
        methods: {
            getMainText() {
                this.mainComponent = MainText
            },
            getCatalog() {
                this.mainComponent = Catalog
            },
            getContacts() {
                this.mainComponent = Cont
            }

        }
    }
</script>

<style scoped>

</style>
