const editForm = document.getElementById("editForm");
editForm.addEventListener("submit", onSubmit);

function onSubmit(event){
    let data = getEditorData();
    if(data == null)
        return false;

    fetch('/api/studysets/update?editToken=' + document.querySelector("#editToken").value, {
        method: 'POST',
        body: new URLSearchParams(data),
    }).then(() => {
        // TODO Use a modal or some other sort of notification
        alert("Successfully updated set.");
        location.reload();
    }).catch(() => { alert("An error occurred."); });

    event.preventDefault();
}