<script setup>
import axios from "axios";
import { useCookies } from "vue3-cookies";
import { onMounted, ref } from "vue";

const { cookies } = useCookies();
const userId = ref("");
const userPw = ref("");
const rememberId = ref(false);

// 로그인 정보를 확인
const login = () => {
    checkRemberId();
    const URL = import.meta.env.VITE_API_URL + "/user/login";
    console.log(URL);
    axios({
        url: URL,
        method: "POST",
        data: { id: userId.value, pw: userPw.value },
    })
        .then((resp) => {
            console.log(resp);
        })
        .catch((error) => {
            console.log(error);
        });
};

// Remember me를 누른 경우 쿠키에 아이디가 저장됨 >> 새로 고침해도 아이디 정보가 남아 있음
const checkRemberId = () => {
    if (rememberId.value) {
        cookies.set("rememberId", userId.value);
    } else {
        cookies.set("rememberId", "");
    }
    console.log(userId.value);
    console.log(rememberId.value);
};

onMounted(() => {
    if (cookies.isKey("rememberId") && cookies.get("rememberId")) {
        console.log(cookies.get("rememberId"));
        console.log("있음");
        userId.value = cookies.get("rememberId");
        rememberId.value = true;
    } else {
        console.log("없음");
        cookies.set("rememberId", ""); //return this
        userId.value = "";
        rememberId.value = false;
    }
});
</script>

<template>
    <div class="container vh-100 d-flex align-items-center justify-content-center">
        <div class="py-4" style="min-width: 400px; min-height: 400px">
            <div class="form-signin w-100 m-auto">
                <h1 class="h3 mb-3 fw-normal">로그인</h1>

                <div class="form-floating">
                    <input type="text" class="form-control" placeholder="아이디" v-model="userId" />
                    <label for="floatingInput">아이디</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" placeholder="비밀번호" v-model="userPw" />
                    <label for="floatingPassword">비밀번호</label>
                </div>

                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" v-model="rememberId" />
                    <label class="form-check-label" for="flexCheckDefault"> Remember me </label>
                </div>
                <button class="btn btn-primary w-100 py-2" @click="login">Log in</button>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
