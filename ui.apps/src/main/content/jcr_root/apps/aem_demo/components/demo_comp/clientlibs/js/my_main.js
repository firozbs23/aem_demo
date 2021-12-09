getData();

function getData() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            const data = xhr.responseText;
            alert(data);
        }
    }
    xhr.open('GET', '/bin/public/aem_demo/demo', true);
    xhr.send(null);
}
