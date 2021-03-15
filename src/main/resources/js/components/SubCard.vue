<template>
    <div class="card">
        <img class="ImgStyle" :src="imgURL"/>
        <div class="containerText">
            <h2 class="TitleProd" @click="CallProd">{{CatName}} ({{Counts}})</h2>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SubCard",
        props: {
            ProdId: Number,
            CatName: String,
            Counts: Number,
            getFromSub: Boolean,
            indexCat: Number,
            indexSubCat: Number
        },
        data() {
            return {
                profile: profile,
                prodBody: '',
                imgUrl: '',
            }
        },
        methods: {
            CallProd(){
                //1 subcat



                //3 cat
            }
        },
        created() {
        },
        computed: {
            imgURL() {
                return `${window.location.origin}/api/download${this.getFromSub ? '' : 's'}/${this.ProdId}`;
            }
        }
    }
</script>

<style scoped>
    img {
        width: 100%;
        height: 100%;
    }

    .card {
        padding: 10px;
        height: auto;
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

    .ImgStyle {
        border-radius: 10px;
        /*margin: 0 15px;*/
    }
</style>
