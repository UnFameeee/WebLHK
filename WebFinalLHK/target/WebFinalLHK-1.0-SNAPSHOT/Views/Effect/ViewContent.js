const viewContent = document.querySelector(".view-content");

window.addEventListener('load', function(){
    console.log("Load complete");
    showPopup();
})

function showPopup(){
    const timeLimit = 5;
    let i = 0;
    const timer = setInterval(function(){
        i++;
        console.log(i);
        if(i === timeLimit){
            clearInterval(timer);
        }
        viewContent.classList.add("show");
    }, 1000);
}