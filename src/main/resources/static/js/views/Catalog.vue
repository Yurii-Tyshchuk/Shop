<template>
    <div class="containerCat">
        <!--    Категории     -->
        <div class="box1">
            <div v-if="TreeList != ''">
                <div v-for="TreeCateg in TreeList" class="cont">
                    <Category :category="TreeCateg"></Category>
                </div>
            </div>
            <div v-else>
                Нет категорий
            </div>
            <div>
                <div style="float: left;" v-if="add">
                    <input v-model="textNewCategory"/>
                </div>
                <button v-show="profileCat == 'active'" @click="addCat">{{btnNewCategory}}</button>
            </div>
        </div>
        <!--    Товар по 1 из каждой категории     -->
        <div class="box2" v-if="b && TreeList != ''">
            <div class="box3" v-for="TreeCateg2 in TreeList" v-if="TreeCateg2.subCategoryList != ''">
                <Product :prod-id="TreeCateg2.subCategoryList[0].products[0].id"></Product>
            </div>
        </div>
        <!--    Товар конкретной под категории     -->
        <div class="box2" v-if="!b && TreeList[0].subCategoryList != ''">
            <div class="box3">
                <div v-if="profileCat === 'active'">
                    <CreateCard :id-sub="TreeList[this.$idList.Cat].subCategoryList[this.$idList.SubCat].id"
                                :name-sub="TreeList[this.$idList.Cat].subCategoryList[this.$idList.SubCat].name"></CreateCard>
                </div>
                <div v-for="Product in TreeList[this.$idList.Cat].subCategoryList[this.$idList.SubCat].products">
                    <Product :prod-id="Product.id"></Product>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Product from "../components/Product.vue";
    import CreateCard from "../components/CreateCard.vue";
    import Category from "../components/Category.vue";
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
                            this.TreeList = res.body;
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
            // items() {
            //     this.TreeList.forEach(function (data) {
            //         data['children'] = data['subCategoryList']
            //
            //     })
            //     console.log(this.TreeList)
            //     return this.TreeList;
            // }
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

    .cont{
        display: flex;
        flex-direction: column;
    }
</style>
