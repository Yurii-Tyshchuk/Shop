<template>
    <div>
        <div style="float: left;" v-if="subEdit">
            <input v-model="subCategory.name"/>
        </div>
        <div style="float: left;" v-else>
            <span @click="getSubCat(subCategory.id,subCategory.id)">{{subCategory.name}}</span>
        </div>
        <div v-if="profileCat ==='active'">
            <button @click="editSubCategory(subCategory.id,subCategory.name)"> {{subEditText}}</button>
            <button @click="">X</button>
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
        }
    }
</script>

<style scoped>

</style>
