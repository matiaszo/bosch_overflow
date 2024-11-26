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
    
    let answerText = document.getElementById("answerText").value
    let questionId = localStorage.getItem("questionId")

    let json = JSON.stringify ({
        "answer" : answerText,
        "questionId" : questionId
    })

    console.log(json)

    const response = await fetch (
        `${baseUrl}/answer`, 
        {
            method: "POST",
            headers: {
                "Authorization" : localStorage.getItem("token"),
                "Content-Type": "application/json"
            },
            body: json
        }
    )

    let result = await response.text()

    console.log(result)
    alert(result)
}

window.postAnswer = postAnswer

async function renderQuestion() {
    const question = await getQuestion()

    console.log(question)

    let spaceTitleDiv = document.getElementById("spaceTitle")
    let questionUserDiv = document.getElementById("questionUser")
    let questionTextDiv = document.getElementById("questionText")

    spaceTitleDiv.innerText = question.spaceName
    questionUserDiv.innerText = question.username
    questionTextDiv.innerText = question.description
}

document.addEventListener("DOMContentLoaded", renderQuestion)