<template>
    <div class="subContainer">
        <div>
            <div class="left" v-if="edit">
                <v-input v-model="category.name"/>
            </div>
            <div class="left" v-else>
                <p @click="a = !a">{{category.name}}</p>
            </div>
            <div v-if="profileCat ==='active'">
                <v-btn elevation="1"
                       outlined
                       small
                       rounded
                       @click="editCategory(category.id,category.name)"
                >
                    {{ editText }}
                </v-btn>
                <v-btn
                        elevation="1"
                        outlined
                        small
                        rounded
                        @click="deleteCat(category.id)"
                >
                    X
                </v-btn>
            </div>
        </div>

        <div v-if="a && category.subCategoryList != ''" class="subContainer">
            <div v-for="subCategory in category.subCategoryList">
                <SubCategory :sub-category="subCategory"></SubCategory>
            </div>
        </div>

        <div>
            <div v-if="add">
                <input v-model="textNewSubCategory"/>
            </div>
            <v-btn elevation="1"
                   outlined
                   small
                   rounded
                   v-show="profileCat === 'active'" @click="addSubCat">{{btnNewSubCategory}}
            </v-btn>
        </div>
    </div>
</template>

<script>
    import SubCategory from "./SubCategory.vue";

    export default {
        name: "Category",
        props: {
            category: Object
        },
        components: {
            SubCategory
        },
        data() {
            return {
                profileCat: profile,
                a: false,
                add: false,
                edit: false,
                editText: 'Редакт.',
                btnNewSubCategory: 'Добавить подкат.',
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
                    )
                } else {
                    this.edit = true;
                    this.editText = 'Сохр.';
                }
            },
            addSubCat() {
                if (this.addSub) {
                    this.addSub = false;
                    this.btnNewSubCategory = 'Добавить кат.';
                    this.$resource("/security/createSubCategory").save({}, {
                        name: this.textNewSubCategory,
                        category: id
                    }).then(value => {
                            console.log(value.body.message);
                        }, value => console.log(value)
                    )
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
                }
            }
        }
    }
</script>

<style scoped>
    .left {
        float: left;
        cursor: pointer;
    }

    .subContainer {
        display: flex;
        flex-direction: column;
        float: left;
        /*padding-right: 10px;*/
        /*margin-left: 20px;*/
    }
</style>
