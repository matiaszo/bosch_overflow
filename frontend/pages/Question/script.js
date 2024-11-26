const baseUrl = "http://localhost:8080"

async function getQuestion() {

    let questionId = localStorage.getItem("questionId")

    const response = await fetch(
        `${baseUrl}/question/${questionId}`,
        {
            method: "GET",
            headers: {
                "Authorization" : localStorage.getItem("token"),
                "Content-Type": "application/json",
                'Accept': 'application/json'
            }
        }
    )

    return await response.json();
}

async function postAnswer() {
    
    let answerText = document.getElementById("answerText")
    let questionId = localStorage.getItem("questionId")
}

async function renderQuestion() {
    const question = await getQuestion();

    console.log(question)

    let spaceTitleDiv = document.getElementById("spaceTitle")
    let questionUserDiv = document.getElementById("questionUser")
    let questionTextDiv = document.getElementById("questionText")

    spaceTitleDiv.innerText = question.spaceName
    questionUserDiv.innerText = question.username
    questionTextDiv.innerText = question.description
}

document.addEventListener("DOMContentLoaded", renderQuestion)