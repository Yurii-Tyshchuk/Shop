<template>
    <div class="Main_Bar">
        <div class="head">
                <div class="head-Address">
                    Адрес <br>
                    Какой-то адрес и еще текст
                </div>
                <div class="head-mainText">
                    Опытный сад<br>
                    Питомник на карельском перешейке<br>
                    Собственное производство
                </div>
                <div class="phone">
                    <div class="phone-Back">
                        <a href="tel:8999999999" style="margin-bottom: 8px;">+7(999)999-99-99</a>
                        <a href="tel:8999999999">+7(999)999-99-99</a>
                    </div>
                </div>
        </div>
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
        data() {
            return {}
        }
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

    .head {
        background-image: url("../../resources/static/Fruits.png");
        background-size: contain;
        max-width: 1200px;
        height: 279px;
        z-index: 1;

        margin-right: auto;
        margin-left: auto;
        padding-top: 100px;

        font-family: Circe, sans-serif;
        font-size: 21px;
        font-weight: 700;
        color: white;

        display: flex;
        align-items: center;
        align-content: center;
        justify-content: space-between;
    }

    .head-Address {
        width: 25%;
        padding-left: 2%;
    }

    .head-mainText {
        width: 50%;
        text-align: center;
    }

    .phone {
        width: 25%;
        text-align: center;
        padding-left: 7%;
    }

    .phone-Back {
        background: #2196f3;
        border-radius: 21px;
        padding: 15px;
        margin-right: 5%;
    }

    .phone-Back a {
        display: block;
        text-decoration: none;
        color: white;
        width: 100%;
        text-align: center;
        font-size: 21px;
    }
</style>
