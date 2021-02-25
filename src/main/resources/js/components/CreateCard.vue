<template>
    <div class="card">
        <div v-if="image !=''">
            <img :src="image" alt="123">
        </div>
        <input type="file"
               id="imageUpload"
               ref="imgFiles"
               class="hide"
               multiple
               accept="image/jpeg, image/png"
               @change="onUploadFiles">

        <div class="form-inline">
            <v-btn
                    small
                    @click="Click"
                    >Выбрать фото
            </v-btn>
            <v-btn small color="red" @click="deleteImg" class="btn">X</v-btn>
        </div>

        <div class="Text">
            <input placeholder="Введите имя продукта" class="data" type="text" v-model="nameProd"/>
            <textarea style="outline: none;" placeholder="Описание продукта" class="data" type="text" v-model="description"/>
            <input placeholder="Баллы если требуются" class="data" type="number" v-model="rating"/>
        </div>
        <v-btn small @click="saveProduct">Сохранить</v-btn>
    </div>
</template>

<script>
    import imageCompression from 'browser-image-compression';

    export default {
        name: "CreateCard",
        props: {
            idSub: Number,
            nameSub: String
        },
        data() {
            return {
                image: '',
                file: '',
                nameProd: '',
                description: '',
                rating: ''
            }
        },
        methods: {
            onUploadFiles(event) {
                let file = event.target.files[0];
                console.log(file);
                let options = {
                    maxSizeMB: 1,
                    maxWidthOrHeight: 400,
                    useWebWorker: true,
                }
                imageCompression(file, options).then(value => {
                    this.file = value;
                    this.image = URL.createObjectURL(value)
                    console.log(value);
                })
            },
            deleteImg() {
                this.image = '';
            },
            saveProduct() {
                let file = new FormData();
                file.append('file', this.file);
                // file.append('data', object)
                file.append('name', this.nameProd);
                file.append('description', this.description);
                file.append('subCategory', this.idSub);
                file.append('rating', this.rating);
                file.append('imgName', this.file.name);

                this.$http.post("/security/upload", file, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(value => {
                        console.log(value.body.message);
                    }, value => console.log(value)
                )
            },
            Click(){
                this.$refs.imgFiles.click();
            }

        }
    }
</script>

<style scoped>
    .card {
        padding: 10px;
        height: auto;
        width: 230px;
        outline: 2px solid black;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    img {
        width: 200px;
        height: 200px;
    }

    .btn {
        cursor: pointer;
        border: 1px solid #ddd;
    }

    .Text {
        padding: 2px 8px;
    }

    .hide {
        display: none;
    }

    .data {

    }
</style>
