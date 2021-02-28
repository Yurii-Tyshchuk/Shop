<template>
    <div class="TextSubCat">
        <div v-if="subEdit">
            <input v-model="subCategory.name"/>
        </div>
        <div v-else>
            <span style="cursor: pointer;" @click="getProdFromSubCat(indexCat,indexSubCat)">{{subCategory.name}}</span>
        </div>
        <div v-if="profileCat ==='active'">
            <v-btn elevation="1"
                   outlined
                   small
                   @click="editSubCategory(subCategory.id,subCategory.name)"
            >
                {{subEditText}}
            </v-btn>
            <v-btn elevation="1"
                   color="error"
                   small
                   @click="deleteSubCat(subCategory.id)"
            >
                X
            </v-btn>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex';

    export default {
        name: "SubCategory",
        props: {
            subCategory: Object,
            indexCat: Number,
            indexSubCat: Number
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
                this.$emit('updateCatalog');
            },
            getProdFromSubCat(indexCat, indexSubCat) {
                this.setIDCatAndSubCat({
                    indexCat,
                    indexSubCat
                })
            },
            deleteSubCat(id) {
                if (confirm("Вы уверены что хотите удалить подкатегорию? Все её товары удалятся.")) {
                    this.$resource("/security/deleteSubCategory/{id}").get({id: id}).then(value => {
                            console.log(value.body);
                        }, value => console.log(value.body)
                    );
                    this.$emit('updateCatalog');
                }
            },
            ...mapMutations([
                "setIDCatAndSubCat"
            ])
        }
    }
</script>

<style scoped>
    .TextSubCat {
        white-space: nowrap;
        float: left;
        padding-left: 10px;
        /*text-align: left;*/
        /*white-space: nowrap*/
    }

    .TextSubCat div {
        display: inline-block;
    }
</style>
