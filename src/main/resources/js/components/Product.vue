<template>
    <div class="card">
        <img :src="imgUrl"/>
        <div class="containerText">
            <span>{{prodBody.name}}</span><br>
            <span style="white-space: pre-line;">{{prodBody.description}}</span><br>
            <span>Рейтинг: {{prodBody.rating}}</span><br>
            <v-btn elevation="1"
                   small
                   @click="getContact"
                   color="green"
            >Узнать
            </v-btn>
            <v-btn v-if="profile === 'active'" elevation="1"
                   small
                   @click="deleteProduct"
                   color="red"
            >Удалить
            </v-btn>
        </div>
    </div>
</template>

<script>
    //{ "id": 1, "name": "Груша", "description": "Описание груши", "rating": 4 }
    export default {
        name: "Product",
        props: {
            ProdId: Number,
        },
        data() {
            return {
                profile: profile,
                prodBody: '',
                imgUrl: ''
            }
        },
        methods: {
            getProduct() {
                this.$resource("/api/product/{id}").get({id: this.ProdId}).then(value => {
                        this.imgUrl = `${window.location.origin}/api/download/${this.ProdId}`;

                        // this.imgUrl = URL.createObjectURL(new Blob([value.body.img], {type: "image/png"}));
                        // this.imgUrl = value.body.img;
                        // console.log(value.body.img);
                        this.prodBody = value.body;
                    }, value => console.log(value.body)
                )
            },
            getContact() {
                document.getElementById("cCat").click();
            },
            deleteProduct() {
                if (confirm("Вы уверены, что хотите удалить товара?")){
                    this.$resource("/security/delete/{id}").get({id: this.ProdId}).then(value => {
                        console.log(value.body)
                    },reason => {
                        console.log(reason.body)
                    })
                    this.$emit('updateCatalog')
                }
            },
        },
        created() {
            this.getProduct();
        },
        watch: {
            ProdId(old,newProps){
                this.getProduct();
            }
        }
    }
</script>

<style scoped>
    img {
        width: 200px;
        height: 200px;
    }

    .card {
        padding: 10px;
        height: auto;
        width: 230px;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    .containerText {
        padding: 2px 8px;
    }
</style>
