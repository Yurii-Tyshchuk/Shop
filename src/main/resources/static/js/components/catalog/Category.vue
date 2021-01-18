<template>
    <div>
        <div v-if="Object.keys(category).length != 0">
            <div class="left" v-if="edit">
                <input v-model="category.name"/>
            </div>
            <div class="left" v-else>
                <span @click="a = !a">{{category.name}}</span>
            </div>
            <div v-if="profileCat ==='active'">
                <button @click="editCategory(category.id,category.name)"> {{editText}}</button>
                <button @click="">X</button>
            </div>

            <div v-if="a && category.subCategoryList != ''">
                <div class="subContainer" v-for="subCategory in category.subCategoryList">
                    <SubCategory :sub-category="subCategory"></SubCategory>
                </div>

                <div style="float: left;" v-if="add">
                    <input v-model="textNewSubCategory"/>
                </div>
                <button v-show="profileCat === 'active'" @click="addSubCat(category.id)">{{btnNewSubCategory}}</button>
            </div>
        </div>

        <div>
            <div style="float: left;" v-if="add">
                <input v-model="textNewCategory"/>
            </div>
            <button v-show="profileCat == 'active'" @click="addCat">{{btnNewCategory}}</button>
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
                btnNewCategory: 'Добавить кат.',
                btnNewSubCategory: 'Добавить подкат.',
                textNewCategory: '',
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
            addSubCat(id) {
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
            }
        }
    }
</script>

<style scoped>
    .left {
        float: left;
    }

    .subContainer {
        padding-right: 10px;
        margin-left: 20px;
    }
</style>
