<template>
    <div class="containerCat">
        <!--    Категории     -->
        <div class="box1">
            <div>
                <h2 class="HeadProd">Наши товары</h2>
            </div>
            <div class="CatCenter" v-if="CategoryList != ''">
                <div v-for="(CategObj,index) in CategoryList" class="cont">
                    <Category :category="CategObj"
                              :index="index"
                              :id-cat="CategObj.id"
                              @updateCatalog="getTreeList"
                    ></Category>
                </div>
            </div>
            <div v-else>
                Нет категорий
            </div>
            <div style="margin-left: 3em;">
                <div v-if="add">
                    <input placeholder="Введите имя категории" style="outline: none" v-model="textNewCategory"/>
                </div>
                <v-btn elevation="1"
                       color="primary"
                       small
                       v-show="profileCat == 'active'"
                       @click="addCat"
                >
                    {{btnNewCategory}}
                </v-btn>
            </div>
        </div>
        <div class="box2" v-if="CategoryList != ''">
            <div>
                <h2 class="HeadProd">{{HeaderText}}</h2>
            </div>
            <!--    При первом выводе каталога   -->
            <div v-if="Output === 0">
                <div class="box3">
                    <div v-for="(CatObj,index) in CategoryList">
                        <div v-if="CatObj.subCategoryList != ''">
                            <div v-if="CatObj.subCategoryList[0].products != ''">
                                <SubCard :prod-id="CatObj.subCategoryList[0].products[0].id"
                                         :cat-name="CatObj.name"
                                         :coun="CatObj.subCategoryList.length"
                                         :index-cat="index"
                                         :get-from-sub="true"
                                         :call-product="false"
                                ></SubCard>
                            </div>
                        </div>
                        <div v-if="CatObj.products !=''">
                            <SubCard :prod-id="CatObj.products[0].id"
                                     :cat-name="CatObj.name"
                                     :coun="CatObj.products.length"
                                     :index-cat="index"
                                     :get-from-sub="false"
                                     :call-product="true"
                            ></SubCard>
                        </div>
                    </div>
                </div>
            </div>
            <!--    Нажатие на подкатегорию выведит ее товары    -->
            <div v-if="Output === 1">
                <div class="box3">
                    <div v-if="profileCat === 'active'">
                        <CreateCard :id-sub="CategoryList[this.IDCat].subCategoryList[this.IDSubCat].id"
                                    :name-sub="CategoryList[this.IDCat].subCategoryList[this.IDSubCat].name"
                                    @updateCatalog="getTreeList"
                        ></CreateCard>
                    </div>
                    <div v-for="Product in CategoryList[this.IDCat].subCategoryList[this.IDSubCat].products"
                         v-if="Product">
                        <Product :prod-id="Product.id"
                                 :prod-name="Product.name"
                                 @updateCatalog="getTreeList"></Product>
                    </div>
                </div>
            </div>
            <!--    При нажатии на категорию, будет выводить подкат.    -->
            <div v-if="Output === 2">
                <div class="box3">
                    <div v-for="(subCategory,index) in CategoryList[this.IDCat].subCategoryList">
                        <div v-if="subCategory.products != ''">
                            <SubCard :prod-id="subCategory.products[0].id"
                                     :cat-name="subCategory.name"
                                     :index-sub-cat="index"
                                     :coun="subCategory.products.length"
                                     :get-from-sub="true"
                                     :call-product="true"
                            ></SubCard>
                        </div>
                    </div>
                </div>
            </div>
            <!--   Товар конкретной категории     -->
            <div v-if="Output === 3">
                <div class="box3">
                    <div v-if="profileCat === 'active'">
                        <CreateCard :id-sub="CategoryList[this.IDCat].id"
                                    :name-sub="CategoryList[this.IDCat].name"
                                    @updateCatalog="getTreeList"
                        ></CreateCard>
                    </div>
                    <div v-for="Product in CategoryList[this.IDCat].products">
                        <Product :prod-id="Product.id"
                                 :prod-name="Product.name"
                                 @updateCatalog="getTreeList"></Product>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Product from "../components/Product.vue";
    import CreateCard from "../components/CreateCard.vue";
    import Category from "../components/Category.vue";
    import SubCard from "../components/SubCard.vue";
    import {mapGetters} from 'vuex';
    // [
    //     {
    //         "id": 1,
    //         "name": "Яблоки",
    //         "products": [
    //             {
    //                 "id": 1,
    //                 "name": "Яблоко1",
    //                 "description": "Описание1",
    //                 "rating": 0
    //              }
    //         ]
    //         "subCategoryList": [
    //             {
    //                 "id": 1,
    //                 "name": "ПодКатегория",
    //                 "products": [
    //                     {
    //                         "id": 1,
    //                         "name": "Яблоко1",
    //                         "description": "Описание1",
    //                         "rating": 0
    //                     }
    //                 ]
    //             }
    //         ]
    //     }
    // ]
    export default {
        name: "Catalog",
        components: {
            SubCard,
            Product,
            CreateCard,
            Category
        },
        data() {
            return {
                profileCat: profile,
                CategoryList: [],
                add: false,
                textNewCategory: '',
                btnNewCategory: 'Добавить категорию',
            }
        },
        methods: {
            getTreeList() {
                this.$http.get("/api/catalog")
                    .then(res => {
                        if (res.body) {
                            function compare(a, b) {
                                if (a.name > b.name) return 1;
                                if (a.name < b.name) return -1;
                                return 0;
                            }

                            this.CategoryList = _.sortBy(res.body, function (o) {
                                return o.name;
                            })
                            for (let i = 0; i < this.CategoryList.length; i++) {
                                this.CategoryList[i].subCategoryList.sort(compare);

                                for (let j = 0; j < this.CategoryList[i].subCategoryList.length; j++) {
                                    this.CategoryList[i].subCategoryList[j].products.sort(compare);
                                }
                            }
                            console.log(this.CategoryList)
                        }
                    });
            },
            addCat() {
                if (this.add) {
                    this.add = false;
                    this.btnNewCategory = 'Добавить кат.';
                    this.$resource("/security/createCategory").save({}, {
                        name: this.textNewCategory
                    }).then(value => {
                            console.log(value.body.message);
                            this.getTreeList();
                            this.textNewCategory = '';
                        }, value => console.log(value)
                    )
                } else {
                    this.add = true;
                    this.btnNewCategory = 'Сохранить';
                }
            },
        },
        created() {
            this.getTreeList();
        },
        computed: {
            ...mapGetters([
                "IDCat",
                "IDSubCat",
                "Output",
                "HeaderText"
            ]),
        }
    }
</script>

<style scoped>
    span {
        cursor: pointer;
        padding-right: 10px;
    }

    .containerCat {
        padding-top: 25px;
        display: grid;
        grid-template-columns: 1fr 3fr;
    }

    .box1 {
        grid-column-start: 1;
        grid-column-end: 2;
        grid-row-start: 1;
        grid-row-end: 2;
    }

    .box2 {
        grid-column-start: 2;
        grid-column-end: 3;
        grid-row-start: 1;
        grid-row-end: 2;
    }

    .box3 {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-gap: 25px;
    }

    .cont {
        display: flex;
        flex-direction: column;
    }

    .HeadProd {
        font-family: "Open Sans", sans-serif;
        font-size: 18px;
        font-weight: 700;
        text-align: center;
        margin-bottom: 50px;
        width: 100%;
    }

    .CatCenter {
        margin-left: 1.5em;
    }
</style>
