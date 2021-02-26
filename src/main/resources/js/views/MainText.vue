<template>
    <div class="containerMainText">
        <div class="boxMainText">
            <textarea v-if="text.edit" v-model="text.textBody.text"></textarea>
            <label v-else>{{ text.textBody.text }}</label>
            <div v-if="profile =='active'">
                <v-btn
                        elevation="1"
                        outlined
                        small
                        @click="edit">{{button}}</v-btn>
                <label>{{ error }}</label>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "MainText",
        data() {
            return {
                profile: profile,
                text: {'textBody': "", edit: false},
                button: 'Редактировать',
                error: ''
            }
        },
        methods: {
            Text() {
                this.$http.get("/api/general")
                    .then(res => {
                        this.text.textBody = res.body != null ? res.body : "Пусто";
                    });
            },
            edit() {
                if (this.text.edit == false) {
                    this.text.edit = true;
                    this.button = 'Сохранить';
                } else {
                    this.text.edit = false;
                    this.button = 'Редактировать';
                    this.$resource("/security/editGeneral").save({}, {
                        id: 0,
                        text: this.text.textBody.text
                    }).then(value => {
                            console.log(value)
                        }, value => this.error = value.body.message
                    )
                }
            }
        },
        created() {
            this.Text();
        },
    }
</script>

<style>
    textarea {
        width: 100%;
        height: 250px;
    }

    .containerMainText {
        display: grid;
        grid-template-columns: 1fr;
        margin-bottom: 10px;
    }

    .boxMainText {
        padding: 10px;
        border: 1px solid black;
        /*grid-column-start: 2;*/
        /*grid-column-end: 3;*/
    }
</style>
