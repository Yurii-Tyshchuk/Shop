<template>
    <div class="card">
        <img :src="imgUrl"/>
        <div class="containerText">
            <span>{{prodBody.name}}</span><br>
            <span>{{prodBody.description}}</span><br>
            <span>Рейтинг: {{prodBody.rating}}</span><br>
            <span style="cursor: pointer;" @click="getContact">Узнать</span>
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
                prodBody: '',
                imgUrl: ''
            }
        },
        methods: {
            getProduct() {
                this.$resource("/api/product/{id}").get({id: this.ProdId}).then(value => {
                        this.imgUrl = `${window.location.origin}/Catalog/img/download/${this.ProdId}`;
                        console.log(this.imgUrl)
                        console.log(value.body);
                        this.prodBody = value.body;
                    }, value => console.log(value.body)
                )
            },
            getContact() {
                document.getElementById("cCat").click();
            }
        },
        created() {
            this.getProduct();
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
        height: 315px;
        width: 230px;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    .containerText {
        padding: 2px 8px;
    }
</style>
