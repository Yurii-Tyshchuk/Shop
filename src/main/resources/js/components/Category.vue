<template>
    <div class="subContainer">
        <div>
            <div class="left" v-if="edit">
                <input v-model="category.name"/>
            </div>
            <div class="left" v-else>
                <p style="cursor: pointer;" @click="checkProdOrSub">{{category.name}}</p>
            </div>
            <div v-if="profileCat ==='active'">
                <v-btn elevation="1"
                       outlined
                       small
                       @click="editCategory(category.id,category.name)"
                >
                    {{ editText }}
                </v-btn>
                <v-btn
                        color="error"
                        elevation="1"
                        small
                        @click="deleteCat(category.id)"
                >
                    X
                </v-btn>
            </div>
        </div>

        <div v-if="isVisibleSubCat" class="subContainer">
            <div v-for="(subCategory,indexSub) in category.subCategoryList">
                <SubCategory
                        :sub-category="subCategory"
                        :index-cat="index"
                        :index-sub-cat="indexSub"
                        @updateCatalog="throwingAnEvent"
                ></SubCategory>
            </div>
        </div>

        <div v-if="profileCat === 'active' && isVisibleSubCat">
            <div class="left" v-if="addSub">
                <input placeholder="Введите имя подкатегории" style="outline: none" v-model="textNewSubCategory"/>
            </div>
            <v-btn elevation="1"
                   small
                   color="primary"
                   @click="addSubCat"
            >
                {{btnNewSubCategory}}
            </v-btn>
        </div>
    </div>
</template>

<script>
    import SubCategory from "./SubCategory.vue";
    import {mapMutations} from 'vuex';

    export default {
        name: "Category",
        props: {
            category: Object,
            index: Number,
            idCat: Number
        },
        components: {
            SubCategory
        },
        data() {
            return {
                profileCat: profile,
                addSub: false,
                edit: false,
                editText: 'Редакт.',
                btnNewSubCategory: 'Добавить подкатегорию',
                btnNewProd: 'Добавить товар',
                textNewSubCategory: ''
            }
        },
        methods: {
            editCategory(idFrom, nameFrom) {
                if (this.edit) {
                    this.edit = false;
                    this.editText = 'Редакт.';
                    this.$resource("/security/editCategory").save({}, {
                        id: idFrom,
                        name: nameFrom
                    }).then(value => {
                            console.log(value.body.message);
                        }, value => console.log(value)
                    );
                    this.$emit('updateCatalog');
                } else {
                    this.edit = true;
                    this.editText = 'Сохр.';
                }
            },
            addSubCat() {
                if (this.addSub) {
                    this.btnNewSubCategory = 'Добавить подкат.';
                    this.$http.get("/security/createSubCategory", {
                        params: {
                            id: this.idCat,
                            name: this.textNewSubCategory
                        }
                    }).then(value => console.log(value.body.message), value => console.log(value))
                    this.addSub = false;
                    this.$emit('updateCatalog');
                } else {
                    this.addSub = true;
                    this.btnNewSubCategory = 'Сохранить';
                }
            },
            deleteCat(id) {
                if (confirm("Вы уверены что хотите удалить категорию? Все её подкатегории и товары удалятся.")) {
                    this.$resource("/security/deleteCategory/{id}").get({id: id}).then(value => {
                            console.log(value.body);
                        }, value => console.log(value.body)
                    )
                    this.$emit('updateCatalog');
                }
            },
            checkProdOrSub() {
                if (this.category.subCategoryList != '') { //Если товары из подкатегории
                    this.setOutput({Output: 2});
                    this.setIDCat({indexCat: this.index});
                } else if (this.category.products != '') { //Если товары из категории
                    this.setOutput({Output: 3});
                    this.setIDCat({indexCat: this.index});
                }
                if (this.category.products == '' && this.category.subCategoryList == '') {
                    this.setOutput({Output: 3});
                    this.setIDCat({indexCat: this.index});
                }
            },
            throwingAnEvent() {
                this.$emit('updateCatalog');
            },
            ...mapMutations([
                "setIDCatAndSubCat",
                "setOutput",
                "setIDCat"
            ])
        },
        computed: {
            isVisibleSubCat() {
                return (this.category.products == ''
                    && this.category.subCategoryList == '')
                    || this.category.products == '';
            }
        }
    }
</script>

<style scoped>
    .left {
        float: left;
    }

    .subContainer {
        display: flex;
        flex-direction: column;
        float: left;
        padding-bottom: 10px;
        /*padding-right: 10px;*/
        /*margin-left: 20px;*/
    }
</style>
