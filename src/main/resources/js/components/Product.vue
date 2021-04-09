<template>
    <div class="card">
        <img :src="imgUrl"/>
        <div class="containerText">
            <div v-if="edit">
                <input type="text" v-model="prodBody.name"/>
                <input type="text" v-model="prodBody.description"/>
                <input type="number" v-model="prodBody.rating"/>
            </div>
            <div v-else>
                <h2 class="TitleProd">{{prodBody.name}}</h2>
                <span style="white-space: pre-line;">{{prodBody.description}}</span><br>
                <span v-if="prodBody.rating != -1">Баллы: {{prodBody.rating /10}}</span><br>
            </div>
            <div class="buttons">
                <v-btn elevation="1"
                       small
                       @click="getContact"
                       color="success"
                       class="button_action"
                >
                    Узнать
                </v-btn>
                <v-btn v-if="profile === 'active'" elevation="1"
                       small
                       @click="deleteProduct"
                       color="error"
                       class="button_action"
                >
                    Удалить
                </v-btn>
                <v-btn v-if="profile === 'active'" elevation="1"
                       small
                       @click="editProduct"
                       color="primary"
                       class="button_action"
                >
                    Ред.
                </v-btn>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';
    //{ "id": 1, "name": "Груша", "description": "Описание груши", "rating": 4 }
    export default {
        name: "Product",
        props: {
            ProdId: Number,
            ProdName: String
        },
        data() {
            return {
                profile: profile,
                prodBody: '',
                imgUrl: '',
                edit: false
            }
        },
        methods: {
            getProduct() {
                let url = this.Output == 3 ? '/api/products/{id}' : '/api/product/{id}';
                this.$resource(url).get({id: this.ProdId}).then(value => {
                        this.imgUrl = `${window.location.origin}/api/download${this.Output == 3 ? 's' : ''}/${this.ProdId}`;
                        // this.imgUrl = URL.createObjectURL(new Blob([value.body.img], {type: "image/png"}));
                        // this.imgUrl = value.body.img;
                        // console.log(value.body.img);
                        if (value.body.description === 'NaNiiii') {
                            value.body.description = '';
                        }
                        this.prodBody = value.body;
                    }, value => console.log(value.body)
                )
            },
            getContact() {
                document.getElementById("cCat").click();
            },
            deleteProduct() {
                if (confirm("Вы уверены, что хотите удалить товара?")) {
                    let url = this.Output == 3 ? '/security/deletes/{id}' : '/security/delete/{id}';
                    this.$resource(url).get({id: this.ProdId}).then(value => {
                        console.log(value.body);
                        this.$emit('updateCatalog');
                    }, reason => {
                        console.log(reason.body)
                    })
                }
            },
            editProduct() {
                if (this.edit) {
                    let url = this.Output == 3 ? '/security/editt' : '/security/edit';
                    this.$resource(url).save({}, {
                        id: this.prodBody.id,
                        name: this.prodBody.name,
                        description: this.prodBody.description,
                        rating: this.prodBody.rating
                    }).then(value => {
                            console.log(value.body.message);
                            this.$emit('updateCatalog');
                        }, value => console.log(value)
                    )
                    this.edit = false;
                } else {
                    this.edit = true;
                }
            }
        },
        created() {
            this.getProduct();
        },
        watch: {
            ProdId(old, newProps) {
                this.getProduct();
            },
            ProdName(old, newProps) {
                this.getProduct();
            }
        },
        computed: {
            ...mapGetters([
                "Output"
            ]),
        }
    }
</script>

<style scoped>
    .card img {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 200px;
        height: 200px;
        border-radius: 10px;
        object-fit: cover;
    }

    .card {
        padding: 10px;
        height: 330px;
        width: 230px;
        outline: 1px solid black;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    .containerText {
        padding: 2px 8px;
    }

    .TitleProd {
        text-align: center;
        font-size: 12px;
        text-transform: uppercase;
        font-family: Circe, sans-serif;
        margin: 0 15px
    }

    .buttons {
        align-items: center;
        width: 200px;
        margin: 0 auto;
        display: inline;
    }

    .button_action {
        align-items: center;
        display: inline-block;
        width: calc(33% - 4px);
        margin: 0 auto;
    }
</style>
