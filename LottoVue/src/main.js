import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";

// bootstrap setting
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

// vue3-cookies setting
import VueCookies from "vue3-cookies";

const app = createApp(App);

app.use(createPinia());
app.use(router);
// app.use(VueCookies);

// Or to set default config:
app.use(VueCookies, {
    expireTimes: "7d",
    path: "/",
    domain: "",
    secure: true,
    sameSite: "None",
});

app.mount("#app");
