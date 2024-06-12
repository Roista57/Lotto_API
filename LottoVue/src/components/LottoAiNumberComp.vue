<script setup>
import { ref } from "vue";
import axios from "axios";
import LottoNumberDetailComp from "./LottoNumberDetailComp.vue";

const size = ref([1, 2, 3, 4, 5, 6]);
const loading = ref(true);

const clicked = async () => {
    loading.value = !loading.value;
    setTimeout(async () => {
        const result = await getLottoNumber();
        loading.value = !loading.value;
    }, 100);
};

const getLottoNumber = async () => {
    await axios({
        url: "http://localhost/lotto/lstm/random",
        method: "GET",
    })
        .then((resp) => {
            size.value = resp.data;
            // console.log(size.value);
        })
        .catch((error) => {
            // console.log(error);
        });
};
</script>

<template>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div v-if="loading">
                <div class="d-flex justify-content-between m-2">
                    <div class="align-content-center">
                        <h5 class="text-center pt-2">AI 랜덤한 수 뽑기</h5>
                    </div>
                    <button type="button" class="btn btn-outline-success" @click="clicked">추첨하기</button>
                </div>
            </div>
            <div v-if="!loading">
                <div class="d-flex justify-content-between m-2">
                    <div class="align-content-center">
                        <h5 class="text-center pt-2">AI 랜덤한 수 뽑기</h5>
                    </div>
                    <button class="btn btn-outline-success" disabled>
                        <span class="spinner-border spinner-border-sm"></span>
                        뽑는 중...
                    </button>
                </div>
            </div>

            <div class="d-flex justify-content-center m-2">
                <LottoNumberDetailComp class="m-2" v-for="index in size" :key="index" :number="index" />
            </div>
        </div>
        <div class="col-3"></div>
    </div>
</template>

<style scoped></style>
