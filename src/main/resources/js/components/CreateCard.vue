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
            <input placeholder="Введите имя продукта" maxlength="23" class="data" type="text" v-model="nameProd"/>
            <input placeholder="Описание продукта" maxlength="23" class="data" type="text" v-model="description"/>
            <input placeholder="Баллы если требуются" class="data" type="number" v-model="rating"/>
        </div>

        <v-btn small @click="saveProduct">Сохранить</v-btn>
    </div>
</template>

<script>
    import imageCompression from 'browser-image-compression';
    import {mapGetters} from 'vuex';

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
                this.file = '';
            },
            saveProduct() {
                let file = new FormData();
                let url = "/security/upload";
                file.append('file', this.file);
                file.append('name', this.nameProd);
                file.append('description', this.description === '' ? 'NaNiiii' : this.description);
                file.append('rating', this.rating == '' ? -1 : (this.rating * 10));
                file.append('imgName', this.file.name);

                if (this.Output == 3) {
                    url = "/security/uploads";
                    file.append('categoryy', this.idSub);
                } else file.append('subCategory', this.idSub);


                this.$http.post(url, file, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(value => {
                        console.log(value.body.message);
                        this.$emit('updateCatalog');
                        this.image = '';
                        this.file = '';
                        this.nameProd = '';
                        this.description = '';
                        this.rating = '';
                    }, value => console.log(value)
                );
            },
            Click() {
                this.$refs.imgFiles.click();
            }
        },
        computed: {
            ...mapGetters([
                "IDCat",
                "IDSubCat",
                "Output"
            ]),
        }
    }
</script>

<style scoped>
    .card {
        padding: 10px;
        height: 330px;
        width: 230px;
        outline: 2px solid black;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    .card img {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 200px;
        height: 200px;
        border-radius: 10px;
        object-fit: cover;
    }

    .btn {
        cursor: pointer;
        border: 1px solid #ddd;
    }

    .Text {
        padding: 2px 8px;
    }

    .Text area {

    }

    .hide {
        display: none;
    }

    .data {
        outline: none;

    }
</style>
