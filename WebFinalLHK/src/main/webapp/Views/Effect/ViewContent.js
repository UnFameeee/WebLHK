const viewContent = document.querySelector(".view-content");
const loading = document.querySelector("#loading");

const btnPrevious = document.querySelector("#previous");
const btnNext = document.querySelector("#next");

let flag = false;

function btnCLicked(){
    flag = true;
}

btnPrevious.addEventListener('click', function () {
    console.log("previous clicked");
    btnCLicked();
})

btnNext.addEventListener('click', function () {
    console.log("previous clicked");
    btnCLicked();
})

window.addEventListener('load', function(){
    if(flag === false) {
        console.log("Load complete");
        showPopup();
    }
    flag = false;
})

// function ()


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