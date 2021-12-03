const viewContent = document.querySelector(".view-content");
const loading = document.querySelector("#loading");

window.addEventListener('load', function(){
        console.log("Load complete");
        showPopup();
})

function showPopup(){
    const timeLimit = 5;
    let i = 0;
    const timer = setInterval(function(){
        i++;
        if(i === timeLimit){
            clearInterval(timer);
            viewContent.classList.add("show");
            loading.classList.add("hide");
        }
    }, 1000);
}