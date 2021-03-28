<template>
    <div class="card" @click="CallProd">
        <img :src="imgURL"/>
        <div class="containerText">
            <h2 class="TitleProd">{{CatName}} ({{count}})</h2>
        </div>
    </div>
</template>

<script>
    import {mapMutations} from "vuex";

    export default {
        name: "SubCard",
        props: {
            ProdId: Number,
            CatName: String,
            coun: Number,
            getFromSub: Boolean,
            indexCat: Number,
            indexSubCat: Number,
            callProduct: Boolean
        },
        data() {
            return {
                profile: profile,
                prodBody: '',
                imgUrl: '',
            }
        },
        methods: {
            CallProd() {
                if (this.callProduct && !this.getFromSub) {
                    this.setOutput({Output: 3});
                    this.setIDCat({indexCat: this.indexCat});
                }
                if (!this.callProduct && this.getFromSub) {
                    this.setOutput({Output: 2});
                    this.setIDCat({indexCat: this.indexCat});
                }
                if (this.callProduct && this.getFromSub) {
                    this.setOutput({Output: 1});
                    this.setIDSubCat({indexSubCat: this.indexSubCat});
                }
            },
            ...mapMutations([
                "setIDCat",
                "setOutput",
                "setIDSubCat"
            ])
        },
        created() {
        },
        computed: {
            imgURL() {
                return `${window.location.origin}/api/download${this.getFromSub ? '' : 's'}/${this.ProdId}`;
            },
            count(){
                return this.coun;
            }
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
        height: 250px;
        width: 230px;
        outline: 1px solid black;
        cursor: pointer;
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
</style>
