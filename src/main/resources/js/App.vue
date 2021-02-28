<template>
    <div class="Main_Bar">
        <v-img max-width="1200"
               max-height="300"
               src="https://www.supersadovnik.ru/binfiles/images/20171127/bf06e795.jpg"
               style="z-index: 1"
        ></v-img>

        <v-app>
            <v-toolbar color="blue"
                       flat
                       min-height="80px"
                       max-height="80px"
                       max-width="1200px"
                       style=" border-radius: 25px; top: -20px; z-index: 3;">
                <v-spacer></v-spacer>
                <v-toolbar-items style="padding-top: 10px">
                    <v-btn dark color="blue" to="/">Главная</v-btn>
                    <v-btn dark color="blue" to="/Catalog">Каталог</v-btn>
                    <v-btn dark color="blue" id="cCat" to="/About">Контакты</v-btn>
                </v-toolbar-items>
                <v-spacer></v-spacer>
            </v-toolbar>

            <div class="MAIN">
                <router-view></router-view>
                <router-view name="two"></router-view>
            </div>

        </v-app>
    </div>
</template>

<script>
    export default {
        name: "App",
    }
</script>

<style scoped>
    .MAIN {
        max-width: 1200px;
        z-index: 1;
    }

    .Main_Bar {
        max-width: 1200px;
        margin: auto;
    }

    body {
        background: #555;
    }
</style>
