<template>
    <div class="TextSubCat">
        <div v-if="subEdit">
            <input v-model="subCategory.name"/>
        </div>
        <div v-else>
            <p class="TextStl" style="cursor: pointer;" @click="getProdFromSubCat(indexCat,indexSubCat)">
                {{subCategory.name}}</p>
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
                bufferSubCategoryName: ''
            }
        },
        methods: {
            editSubCategory(idFrom, nameFrom) {
                if (!this.subEdit) {
                    this.bufferSubCategoryName = nameFrom;
                    this.subEdit = true;
                    this.subEditText = 'Сохр.';
                } else {
                    this.subEdit = false;
                    this.subEditText = 'Редакт.';
                    this.$resource("/security/editSubCategory").save({}, {id: idFrom, name: nameFrom})
                        .then(value => {
                                console.log(value.body.message);
                                this.$emit('updateCatalog');
                            }, value => {
                                this.subCategory.name = _.clone(this.bufferSubCategoryName);
                                alert(value.body.message);
                            }
                        )
                }
            },
            getProdFromSubCat(indexCat, indexSubCat) {
                this.setIDCatAndSubCat({
                    indexCat,
                    indexSubCat
                })
                this.setHeaderText({HeaderText: this.subCategory.name});
            },
            deleteSubCat(id) {
                if (confirm("Вы уверены что хотите удалить подкатегорию? Все её товары удалятся.")) {
                    this.$resource("/security/deleteSubCategory/{id}").get({id: id}).then(value => {
                            console.log(value.body);
                            this.$emit('updateCatalog');
                        }, value => console.log(value.body)
                    );
                }
            },
            ...mapMutations([
                "setIDCatAndSubCat",
                "setHeaderText"
            ])
        }
    }
</script>

<style scoped>
    .TextSubCat {
        white-space: nowrap;
        float: left;
        /*padding-left: 10px;*/
        /*text-align: left;*/
        /*white-space: nowrap*/
    }

    .TextSubCat div {
        display: inline-block;
    }

    .TextStl {
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
        font-weight: 700;
        text-align: center;
        width: 100%;
        font-family: Circe, sans-serif;
    }
</style>
