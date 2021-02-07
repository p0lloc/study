const items = document.getElementById("items");
const creationForm = document.getElementById("creationForm");
const creationInfo = document.getElementById("creationInfo");

const setNameEl = document.getElementById("creation_setName");
const setEditUrlEl = document.getElementById("creation_editUrl");
const setStudyUrlEl = document.getElementById("creation_studyUrl");

creationForm.addEventListener("submit", onSubmit);

function onSubmit(event){
    let params = getEditorData();
    if(params == null)
        return false;

    fetch('api/studysets/create', {
        method: 'POST',
        body: new URLSearchParams(params),
    }).then(response => response.json())
        .then(data => {
            let set = data["data"];

            creationForm.remove();
            creationInfo.classList.remove("hidden");

            setNameEl.innerText = set["name"];

            setEditUrlEl.innerText = data["editUrl"];
            setEditUrlEl.href = data["editUrl"];

            setStudyUrlEl.innerText = data["studyUrl"];
            setStudyUrlEl.href = data["studyUrl"];
        })
        .catch(() => { alert("An error occurred")
        });

    event.preventDefault();
}