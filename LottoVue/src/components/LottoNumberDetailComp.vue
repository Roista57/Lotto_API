<script setup>
import { onMounted, ref } from "vue";

const props = defineProps(["number"]);

const number = props.number;
const color = ref("");

// #FFE400 ( 골드 ) // 1 ~ 10
// #80F5FF ( 파랑 ) // 11 ~ 20
// #FF6C6C ( 레드 ) // 21 ~ 30
// #A6A6A6 ( 회색 ) // 31 ~ 40
// #BCE55C ( 녹색 ) // 41 ~ 45

onMounted(() => {
    if (number < 11) {
        color.value = "background-color: #FFE400";
    } else if (number < 21) {
        color.value = "background-color: #80F5FF";
    } else if (number < 31) {
        color.value = "background-color: #FF6C6C";
    } else if (number < 41) {
        color.value = "background-color: #A6A6A6";
    } else {
        color.value = "background-color: #BCE55C";
    }
});
</script>

<template>
    <div
        class="text-white rounded-circle align-content-center"
        style="min-width: 80px; width: 80px; height: 80px; min-height: 80px"
        :style="color"
    >
        <h1 class="text-center" style="font-size: 35px; font-weight: bold">{{ number }}</h1>
    </div>
</template>

<style scoped></style>
