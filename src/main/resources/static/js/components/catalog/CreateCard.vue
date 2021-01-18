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
            <label for="imageUpload" class="btn" style="width: auto">Выбрать фото</label>
            <label @click="deleteImg" class="btn">X</label>
        </div>

        <div class="Text">
            <input type="text" v-model="nameProd">
            <input type="text" v-model="description">
            <input type="number" v-model="rating">
        </div>
        <label @click="saveProduct">Сохранить</label>
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
                // const reader = new FileReader();
                // reader.onload = ev => this.image = ev.target.result;
                // reader.readAsDataURL(file);
                let options = {
                    maxSizeMB: 1,
                    maxWidthOrHeight: 400,
                    useWebWorker: true,
                }
                imageCompression(file, options).then(value => {
                    this.file = value;
                    this.image = URL.createObjectURL(value)
                    console.log(value);
                    console.log(this.idSub);

                })
            },
            deleteImg() {
                this.image = '';
            },
            saveProduct() {
                //     description: this.description,
                //         subCategory
                // :
                //     this.nameSub,
                //         rating
                // :
                //     this.rating,
                //         imgName
                // :
                this.file.name
                let data = new FormData();
                data.append('name', this.nameProd);
                data.append('description', this.description);
                data.append('subCategory', this.nameSub);
                data.append('rating', this.rating);
                data.append('imgName', this.file.name);
                let object = {
                    name: this.nameProd,
                    description: this.description,
                    subCategory: this.nameSub,
                    rating: this.rating,
                    imgName: this.file.name
                };

                let blob = new Blob()

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
            }
        }
    }
</script>

<style scoped>
    .card {
        padding: 10px;
        height: auto;
        width: 230px;
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
</style>
