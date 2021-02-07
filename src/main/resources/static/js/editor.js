let lastField;
let lastFieldEventListener;

listenToLastFieldInput();

function listenToLastFieldInput(){
    let elements = document.getElementsByClassName("lastField");
    if(elements.length > 0)
        lastField = elements[0];

    lastFieldEventListener = lastField.addEventListener('input', handleInput);
}

function stopListenToLastFieldInput(){
    lastField.removeEventListener('input', handleInput);
}

function getEditorData(){
    const form = document.querySelector('form');
    const data = new URLSearchParams(new FormData(form).entries()); // Create a map from the form elements

    let questions = [];
    let term = null;

    let questionMap = new Map(); // Map of query params
    let params = new Map(); // Map of query params

    data.forEach(function(value, key) {
        questionMap.set(key, value);
        if(key.startsWith("question_term")){ // If element holds a term
            term = value;
        } else if(key.startsWith("question_definition")){ // If element holds a definition
            if(term.trim() !== "" && value.trim() !== "") {
                questions.push({
                    term: term,
                    definition: value
                });
            }
            term = null;
        } else {
            params.set(key, value);
        }
    });

    if(questions.length === 0){
        alert("You must add at least one question!");
        return null;
    }

    params.set("questions", JSON.stringify(questions));
    return params;
}

/**
 * Handles input
 */
function handleInput(){
    let lastElement = items.children.item(items.children.length-1);
    if(lastElement.children.item(1).value !== " "){
        // TODO there is surely a better way to do this..
        stopListenToLastFieldInput();
        let cloned = lastElement.cloneNode(true); // clone the last element in the form
        lastElement.childNodes.item(lastElement.childNodes.length - 2).classList.remove("lastField");

        for (let i = 0; i < cloned.childNodes.length; i++) { // add appropriate attributes/values to the child nodes of the cloned field
            let item = cloned.childNodes.item(i);
            if(item.classList != null) {
                item.value = " ";
                item.name = "question_" + (item.classList.contains("lastField")
                    ? "definition" : "term") + items.children.length; // if it's the last field, it will hold a definition
            }
        }

        items.appendChild(cloned);
        listenToLastFieldInput();
    }
}