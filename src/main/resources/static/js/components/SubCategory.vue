<template>
    <div class="TextSubCat">
        <div v-if="subEdit">
            <v-input v-model="subCategory.name"/>
        </div>
        <div v-else>
            <span @click="getSubCat(subCategory.id,subCategory.id)">{{subCategory.name}}</span>
        </div>
        <div v-if="profileCat ==='active'">
            <v-btn elevation="1"
                   outlined
                   small
                   rounded @click="editSubCategory(subCategory.id,subCategory.name)"
            >
                {{subEditText}}
            </v-btn>
            <v-btn elevation="1"
                   outlined
                   small
                   rounded @click="deleteSubCat(subCategory.id)"
            >
                X
            </v-btn>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SubCategory",
        props: {
            subCategory: Object
        },
        data() {
            return {
                profileCat: profile,
                subEdit: false,
                subEditText: 'Редакт.',
                addSub: false,
            }
        },
        methods: {
            editSubCategory(idFrom, nameFrom) {
                if (!this.subEdit) {
                    this.subEdit = true;
                    this.subEditText = 'Сохр.';
                } else {
                    this.subEdit = false;
                    this.subEditText = 'Редакт.';
                    this.$resource("/security/editSubCategory").save({}, {id: idFrom, name: nameFrom})
                        .then(value => {
                                console.log(value.body.message);
                            }, value => console.log(value)
                        )
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
            },
            getSubCat() {
                this.b = !this.b;
            },
            deleteSubCat(id) {
                if (confirm("Вы уверены что хотите удалить подкатегорию? Все её товары удалятся.")) {
                    this.$resource("/security/deleteSubCategory/{id}").get({id: id}).then(value => {
                            console.log(value.body);
                        }, value => console.log(value.body)
                    )
                }
            }
        }
    }
</script>

<style scoped>
    .TextSubCat {
        white-space:nowrap;
        float: left;
        padding-left: 10px;
        /*text-align: left;*/
        /*white-space: nowrap*/
    }
    .TextSubCat div{
        display:inline-block;
    }
</style>
