const imagenes = [
    "red",
    "blue",
    "green"
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
    sleep(1000)
    document.body.style.backgroundColor = imagenes.next().values();
}

