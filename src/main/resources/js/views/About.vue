<template>
    <div class="containerContact">
        <div class="boxContact">
            <textarea v-if="text.edit" v-model="text.textBody.text"></textarea>
            <label style="white-space: pre-line;" v-else>{{ text.textBody.text }}</label>
            <div v-if="profile == 'active'">
                <v-btn
                        elevation="1"
                        outlined
                        small
                        @click="edit">{{button}}
                </v-btn>
                <label>{{ error }}</label>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Сontacts",
        data() {
            return {
                profile: profile,
                text: {'textBody': {}, edit: false},
                button: 'Редактировать описание',
                error: ''
            }
        },
        methods: {
            Text() {
                this.$http.get("/api/contact")
                    .then(res => {
                        this.text.textBody = res.body;
                    });
            },
            edit() {
                if (!this.text.edit) {
                    this.text.edit = true;
                    this.button = 'Сохранить';
                } else {
                    this.text.edit = false;
                    this.button = 'Редактировать';
                    this.$resource("/security/editConOrMainText/2").save({}, {
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
        }
    }
</script>

<style scoped>
    textarea {
        width: 650px;
        height: 600px;
    }

    .containerContact {
        display: flex;
        justify-content: center;
    }
</style>
