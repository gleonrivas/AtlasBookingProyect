const imagenes = [
    "/templates/atlasWEB/img/nyk-wallpaper.jpeg",
    "/templates/atlasWEB/img/venecia.jpg",
    "/templates/atlasWEB/img/paris.jpg"
    ];

function sleep(milliseconds) {
    let start = new Date().getTime();
    for (let i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}


while (1>0){
    sleep(1000);
    document.body.style.backgroundImage = imagenes.next().values();
}

//document.body.style.backgroundImage = "url('https://sebhastian.com/img/default.png')";
