let answerElement = document.getElementById("answer");
let bodyElement = document.getElementsByTagName("body")[0];
let finished = false;

let currentQuestions;

let currentQuestion;
let questionIdx = -1;

let shown = 0;

let timeStart;
let lastEnter;

function startSession() {
    answerElement.focus();
    timeStart = new Date().getTime();
    questionIdx = -1;
    currentQuestion = null;
    toggleFinished(false);

    let clone = {...questions};
    clone.length = questions.length;

    if (!inOrder)
        shuffleArray(clone);

    currentQuestions = clone;
    getNextQuestion(true);
}

function getNextQuestion(start) {
    questionIdx++;

    if (questionIdx !== currentQuestions.length) {
        currentQuestion = currentQuestions[questionIdx];
        setQuestionText(currentQuestion["term"]);

        if (!start) {
            bodyElement.style.backgroundColor = "#0f8836";
            setTimeout(function () {
                bodyElement.style.backgroundColor = "#104E8B";
            }, 100);
        }
    } else {
        toggleFinished(true);
    }
}

// TODO learn some fancy framework to do this shitty DOM for me :D
function toggleFinished(value) {
    finished = value;
    if (value) {
        document.getElementById("time").innerText = convertTime(new Date().getTime() - timeStart);
        document.getElementById("answerShown").innerText = shown.toString();
        document.getElementById("finished").classList.remove("hidden");
        document.getElementById("answer-input").classList.add("hidden");
    } else {
        document.getElementById("finished").classList.add("hidden");
        document.getElementById("answer-input").classList.remove("hidden");
    }
}

function onKeyDown(e) {
    if (e.keyCode === 13) {
        if(!finished) {
            let time = new Date().getTime();
            if (time - lastEnter < 1000) {
                answerElement.readOnly = true;
                answerElement.value = currentQuestion["definition"];
                shown++;
                setTimeout(function () {
                    answerElement.value = "";
                    answerElement.readOnly = false;
                }, 1000);
            }

            lastEnter = time;
        } else {
            startSession();
        }
    }
}

function convertTime(s) {
    const ms = s % 1000;
    s = (s - ms) / 1000;
    const secs = s % 60;
    s = (s - secs) / 60;
    const mins = s % 60;

    return (mins > 9 ? mins : "0" + mins) + ':' + (secs > 9 ? secs : "0" + secs);
}

function setQuestionText(text) {
    document.getElementById("question").innerText = text;
}

function onAnswerInput(){
    if(answerElement.readOnly)
        return;

    let definition = currentQuestion["definition"].trim();

    if(!strict) {
        definition = definition.toLowerCase();
        answerElement.value = answerElement.value.toLowerCase();
    }

    if (definition === answerElement.value) {
        getNextQuestion();
        answerElement.value = "";
    }
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function setupQuiz() {
    document.addEventListener("keydown", onKeyDown);
    answerElement.addEventListener("input", onAnswerInput);
    document.getElementById("start-over")
        .addEventListener("click", startSession);

    startSession();
}