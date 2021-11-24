//helper functions ** unfinished for getting image height automatically
async function getMeta(url) {
    var img = new Image();
    img.onload = await function () {
        alert(this.width + ' ' + this.height);
    };
    img.src = url;
}

// page functions to change
var allPrototypeButtons = [];

function addBtn(btntext, element_to_append, btnEvent, style) {

    var button = document.createElement("button");
    button.innerHTML = btntext;
    button.style = style;
    Object.assign(button.style, style);
    button.addEventListener("click", btnEvent);
    element_to_append.appendChild(button);
    allPrototypeButtons.push(button);
}

function gatedArea() {
    document.body.style.background = "url(mock_page_images/landing_page.jpg)";
    document.body.style.height = '3135px';
}

function nongatedArea() {
    document.body.style.background = "url(mock_page_images/registration_uuid_page.png)";
    document.body.style.height = '1250px';
    addBtn("UUID show", document.body, event => {
            document.getElementsByClassName('webchat-toggle-button cognigy-webchat-rsvrmf')[0].click();
        },
        {
            position: "relative",
            left: "1150px",
            top: "525px",
            width: "100px",
            height: "50px",
            background: "transparent",
            border: "none",
            color: "transparent",
            cursor: "pointer"
        })

}

function prototype() {
    document.body.onclick = event => {
        allPrototypeButtons.forEach(x => {
            x.style.background = "orange";
            x.style.opacity = 0.5;
            x.style.color = "black";
        });
        setTimeout(() => {
            allPrototypeButtons.forEach(x => {
                x.style.background = "transparent";
                x.style.color = "transparent";;
            });
        }, 1000);
    };
}


initWebchat('https://endpoint-demo.cognigy.ai/804780ebae2184ae75d7e4abe1dc6e3275a5d8246f2b9c3959e6e6050010b7be', {
    settings: {
        colorScheme: "#f68701",
        userId: "example@gmail.com"
    }
})
    .then(cognigyWebchat => {
        cognigyWebchat.sendMessage('', {
            'flag': 'true'
        });
    });

// enable prototype with press [shift + p]
$(document).on('keypress',function(e) {
    if(e.which == 80) { //p
        prototype();
    }
});

