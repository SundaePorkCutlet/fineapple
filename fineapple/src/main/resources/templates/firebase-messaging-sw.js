//// 캐시 이름
//const CACHE_NAME = "finecache";
//
//// 캐싱할 파일
//const FILES_TO_CACHE = [
//    "/index/index.html",
//    "/assets/img/logo2.png",
//    "/",
//    "/manifest.json"
//];
//
//// 상술한 파일 캐싱
//self.addEventListener("install", (event) => {
//    event.waitUntil(
//        caches.open(CACHE_NAME).then((cache) => cache.addAll(FILES_TO_CACHE))
//    );
//});
//
//// CACHE_NAME이 변경되면 오래된 캐시 삭제
//self.addEventListener("activate", (event) => {
//    event.waitUntil(
//        caches.keys().then((keyList) =>
//            Promise.all(
//                keyList.map((key) => {
//                    if (CACHE_NAME !== key) return caches.delete(key);
//                })
//            )
//        )
//    );
//});
//
//// 요청에 실패하면 오프라인 페이지 표시
//self.addEventListener("fetch", (event) => {
//    if ("navigate" !== event.request.mode) return;
//
//    event.respondWith(
//        fetch(event.request).catch(() =>
//            caches
//                .open(CACHE_NAME)
//                .then((cache) => cache.match("/offline.html"))
//        )
//    );
//});

importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

// Initialize Firebase
var config = {
    apiKey: "AIzaSyB-mLj16sn2IKg1NgpaYMayKicZqudslfE",
    authDomain: "fineapple-67f2b.firebaseapp.com",
    projectId: "fineapple-67f2b",
    storageBucket: "fineapple-67f2b.appspot.com",
    messagingSenderId: "950462114300",
    appId: "1:950462114300:web:a762042fe15befa82b6a79",
    measurementId: "G-KNDQ1YY7JY"

};
firebase.initializeApp(config);

const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function(payload){

    const title = "Hello World";
    const options = {
        body: payload.data.status
    };

    return self.registration.showNotification(title,options);
});