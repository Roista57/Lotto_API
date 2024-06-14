<script setup>
import { RouterLink } from "vue-router";
import router from "@/router";

const login = () => {};
</script>

<template>
    <nav class="navbar navbar-expand-sm p-3">
        <div class="container-fluid">
            <a class="navbar-brand" style="font-size: 20px; font-weight: bold" href="#">Random Lotto</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto"></ul>
                <!-- <ul class="navbar-nav me-auto">
                    <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="#" class="nav-link px-2 text-black">Features</a></li>
                    <li><a href="#" class="nav-link px-2 text-black">Pricing</a></li>
                    <li><a href="#" class="nav-link px-2 text-black">FAQs</a></li>
                    <li><a href="#" class="nav-link px-2 text-black">About</a></li>
                </ul> -->
                <div>
                    <router-link class="btn btn-outline-success me-2" :to="{ name: 'login' }">Login</router-link>
                    <button type="button" class="btn btn-outline-warning me-2">Sign-up</button>
                </div>
            </div>
        </div>
    </nav>
    <div class="p-5 bg-primary text-white">
        <h1>Jumbotron Example</h1>
    </div>
</template>

<style scoped></style>
