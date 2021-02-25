<template>
    <div class="containerCat">
        <!--    Категории     -->
        <div class="box1">
            <div v-if="TreeList != ''">
                <div v-for="(TreeCateg,index) in TreeList" class="cont">
                    <Category :category="TreeCateg"
                              :index="index"
                              :id-cat="TreeCateg.id"
                    ></Category>
                </div>
            </div>
            <div v-else>
                Нет категорий
            </div>
            <div>
                <div style="float: left;" v-if="add">
                    <input v-model="textNewCategory"/>
                </div>
                <v-btn elevation="1"
                       outlined
                       small
                       v-show="profileCat == 'active'"
                       @click="addCat"
                >
                    {{btnNewCategory}}
                </v-btn>
            </div>
        </div>
        <!--    Товар по 1 из каждой категории     -->
        <div class="box2" v-if="!ProdFromSubCat && TreeList != ''">
            <div class="box3">
                <div v-for="TreeCateg2 in TreeList">
                    <div v-if="TreeCateg2.subCategoryList != ''">
                        <div v-if="TreeCateg2.subCategoryList[0].products != ''">
                            <Product :prod-id="TreeCateg2.subCategoryList[0].products[0].id"></Product>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--    Товар конкретной подкатегории     -->
        <div class="box2" v-if="ProdFromSubCat && TreeList != ''">
            <div class="box3">
                <div v-if="profileCat === 'active'">
                    <CreateCard :id-sub="TreeList[this.IDCat].subCategoryList[this.IDSubCat].id"
                                :name-sub="TreeList[this.IDCat].subCategoryList[this.IDSubCat].name"></CreateCard>
                </div>
                <div v-for="Product in TreeList[this.IDCat].subCategoryList[this.IDSubCat].products">
                    <Product :prod-id="Product.id" @updateCatalog="getTreeList"></Product>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Product from "../components/Product.vue";
    import CreateCard from "../components/CreateCard.vue";
    import Category from "../components/Category.vue";
    import {mapGetters} from 'vuex';

    // [
    //     {
    //         "id": 1,
    //         "name": "Яблоки",
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
            Product,
            CreateCard,
            Category
        },
        data() {
            return {
                profileCat: profile,
                TreeList: [],
                add: false,
                b: true, //<- Появляется 1 товар каждой категории, при true
                textNewCategory: '',
                btnNewCategory: 'Добавить кат.',
            }
        },
        methods: {
            getTreeList() {
                this.$http.get("/api/catalog")
                    .then(res => {
                        if (res.body) {
                            function compare(a,b) {
                                if (a.name > b.name) return 1;
                                if (a.name < b.name) return -1;
                                return 0;
                            }
                            this.TreeList = _.sortBy(res.body, function (o) {
                                return o.name;
                            })
                            for (let i = 0; i < this.TreeList.length; i++) {
                                this.TreeList[i].subCategoryList.sort(compare);

                                for (let j = 0; j < this.TreeList[i].subCategoryList.length; j++) {
                                    this.TreeList[i].subCategoryList[j].products.sort(compare);
                                }
                            }
                            // this.TreeList = res.body;
                            console.log(this.TreeList)
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
                        }, value => console.log(value)
                    )
                    this.getTreeList();
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
                "ProdFromSubCat"
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
</style>
