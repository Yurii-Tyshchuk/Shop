<template>
    <Header class="MAIN"></Header>
</template>

<script>
    import Header from "./components/Header.vue";

    export default {
        name: "App",
        components: {
            Header
        },
    }
</script>

<style scoped>
    .MAIN {
        max-width: 1200px;
        margin: auto;
        background: white;
        padding: 10px;
    }

    body {
        background: #555;
    }
</style>
